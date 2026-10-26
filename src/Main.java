import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static Inventory inventory = new Inventory();

    public static void main(String[] args) {

        FileManager.initializeFiles();

        FileManager.loadProducts(inventory);

        FileManager.loadTransactions(inventory);

        
        while (true) {

            System.out.println("\n======================================");
            System.out.println("       INVENTORY CONTROL SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Add Stock");
            System.out.println("7. Remove Stock");
            System.out.println("8. Low Stock Products");
            System.out.println("9. Inventory Report");
            System.out.println("10. Transaction History");
            System.out.println("11. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = InputValidator.getInt(scanner);

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    inventory.displayAllProducts();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    updateProduct();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    addStock();
                    break;

                case 7:
                    removeStock();
                    break;

                case 8:
                    inventory.displayLowStockProducts();
                    break;

                case 9:
                    displayInventoryReport();
                    break;

                case 10:
                    inventory.displayTransactionHistory();
                    break;

                case 11:
                    System.out.println(
                            "\nThank you for using Inventory Control System!"
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // Add Product

    public static void addProduct() {

        System.out.println("\n========== ADD PRODUCT ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        if (inventory.findProduct(productId) != null) {

            System.out.println("Product ID already exists.");
            return;
        }

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price =
                InputValidator.getNonNegativeDouble(scanner);

        System.out.print("Enter Quantity: ");
        int quantity =
                InputValidator.getNonNegativeInt(scanner);

        System.out.print("Enter Reorder Level: ");
        int reorderLevel =
                InputValidator.getNonNegativeInt(scanner);

        if (price < 0 || quantity < 0 || reorderLevel < 0) {

            System.out.println(
                    "Price, quantity and reorder level cannot be negative."
            );

            return;
        }

        Product product = new Product(
                productId,
                productName,
                category,
                price,
                quantity,
                reorderLevel
        );

        inventory.addProduct(product);

        Transaction transaction = new Transaction(
        product.getProductId(),
        product.getProductName(),
        "INITIAL_STOCK",
        product.getQuantity(),
        product.getQuantity()
        );

        inventory.addTransaction(transaction);
        FileManager.saveTransaction(transaction);

        FileManager.saveAllProducts(inventory);

        System.out.println("\nProduct added successfully!");
    }

    // Search Product

    public static void searchProduct() {

        System.out.println("\n========== SEARCH PRODUCT ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        inventory.searchProduct(productId);
    }

    // Delete Product

    public static void deleteProduct() {

        System.out.println("\n========== DELETE PRODUCT ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        boolean deleted = inventory.deleteProduct(productId);

        if (deleted) {

            FileManager.saveAllProducts(inventory);

            System.out.println("Product deleted successfully.");

        } else {

            System.out.println("Product not found.");
        }
    }

    // Inventory Report

    public static void displayInventoryReport() {

        System.out.println("\n========== INVENTORY REPORT ==========");

        int totalProducts = inventory.getProducts().size();

        double totalValue =
                inventory.getTotalInventoryValue();

        int totalQuantity = 0;

        for (Product product : inventory.getProducts()) {

            totalQuantity += product.getQuantity();
        }

        System.out.println("Total Products : " + totalProducts);
        System.out.println("Total Quantity : " + totalQuantity);
        System.out.println("Inventory Value: ₹" + totalValue);
    }

    // Update Product

    public static void updateProduct() {

        System.out.println("\n========== UPDATE PRODUCT ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        Product product = inventory.findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("\nCurrent Product Details:");
        System.out.println("Name     : " + product.getProductName());
        System.out.println("Category : " + product.getCategory());
        System.out.println("Price    : ₹" + product.getPrice());
        System.out.println("Reorder Level : " + product.getReorderLevel());

        System.out.print("\nEnter New Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter New Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter New Price: ");
        double price =
                InputValidator.getNonNegativeDouble(scanner);

        System.out.print("Enter New Reorder Level: ");
        int reorderLevel =
                InputValidator.getNonNegativeInt(scanner);

        if (price < 0 || reorderLevel < 0) {

            System.out.println(
                    "Price and reorder level cannot be negative."
            );

            return;
        }

        product.updateProduct(
        productName,
        category,
        price,
        reorderLevel
        );

        FileManager.saveAllProducts(inventory);

        System.out.println("\nProduct updated successfully!");

    }

    // Add Stock

    public static void addStock() {

        System.out.println("\n========== ADD STOCK ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        Product product = inventory.findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity to add: ");
        int amount =
                InputValidator.getPositiveInt(scanner);

        if (amount <= 0) {

            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        product.addStock(amount);

        Transaction transaction = new Transaction(
                product.getProductId(),
                product.getProductName(),
                "ADD_STOCK",
                amount,
                product.getQuantity()
        );

        inventory.addTransaction(transaction);

        FileManager.saveTransaction(transaction);

        FileManager.saveAllProducts(inventory);

        System.out.println(
                "\nStock added successfully!"
        );

        System.out.println(
                "New Quantity: " + product.getQuantity()
        );
    }

    // Remove Stock

    public static void removeStock() {

        System.out.println("\n========== REMOVE STOCK ==========");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();

        Product product = inventory.findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println(
                "Available Quantity: " +
                product.getQuantity()
        );

        System.out.print("Enter quantity to remove: ");
        int amount =
                InputValidator.getPositiveInt(scanner);

        if (amount <= 0) {


            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        boolean removed = product.removeStock(amount);
        if (removed) {

            Transaction transaction = new Transaction(
                    product.getProductId(),
                    product.getProductName(),
                    "REMOVE_STOCK",
                    amount,
                    product.getQuantity()
            );

            inventory.addTransaction(transaction);

            FileManager.saveTransaction(transaction);

            FileManager.saveAllProducts(inventory);

            System.out.println(
                    "\nStock removed successfully!"
            );

            System.out.println(
                    "Remaining Quantity: " +
                    product.getQuantity()
            );

        } else {

            System.out.println(
                    "Insufficient stock."
            );
        }
    }
}