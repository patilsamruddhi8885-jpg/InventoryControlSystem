import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products = new ArrayList<>();
    
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Add a product
    public void addProduct(Product product) {
        products.add(product);
    }

    // Get all products
    public ArrayList<Product> getProducts() {
        return products;
    }

    // Find product using product ID
    public Product findProduct(String productId) {

        for (Product product : products) {

            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }

        return null;
    }

    // Display all products
    public void displayAllProducts() {

        if (products.isEmpty()) {
            System.out.println("\nNo products available.");
            return;
        }

        System.out.println("\n============================ PRODUCT LIST ============================");

        System.out.printf(
                "%-10s %-20s %-15s %-12s %-10s %-12s%n",
                "ID",
                "Product Name",
                "Category",
                "Price",
                "Quantity",
                "Total Value"
        );

        System.out.println(
                "-----------------------------------------------------------------------"
        );

        for (Product product : products) {

            System.out.printf(
                    "%-10s %-20s %-15s ₹%-11.2f %-10d ₹%-11.2f%n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getTotalValue()
            );
        }

        System.out.println(
                "-----------------------------------------------------------------------"
        );
    }

    // Search and display one product
    public void searchProduct(String productId) {

        Product product = findProduct(productId);

        if (product == null) {
            System.out.println("\nProduct not found.");
            return;
        }

        System.out.println("\n================ PRODUCT DETAILS ================");
        System.out.println("Product ID     : " + product.getProductId());
        System.out.println("Product Name   : " + product.getProductName());
        System.out.println("Category       : " + product.getCategory());
        System.out.println("Price          : ₹" + product.getPrice());
        System.out.println("Quantity       : " + product.getQuantity());
        System.out.println("Reorder Level  : " + product.getReorderLevel());
        System.out.println("Total Value    : ₹" + product.getTotalValue());
    }

    // Delete a product
    public boolean deleteProduct(String productId) {

        Product product = findProduct(productId);

        if (product == null) {
            return false;
        }

        products.remove(product);
        return true;
    }

    // Display low-stock products
    public void displayLowStockProducts() {

        boolean found = false;

        System.out.println("\n================ LOW STOCK PRODUCTS ================");

        for (Product product : products) {

            if (product.isLowStock()) {

                System.out.println(
                        product.getProductId() + " - " +
                        product.getProductName() +
                        " | Quantity: " +
                        product.getQuantity() +
                        " | Reorder Level: " +
                        product.getReorderLevel()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No low-stock products.");
        }
    }

    // Calculate total inventory value
    public double getTotalInventoryValue() {

        double totalValue = 0;

        for (Product product : products) {
            totalValue += product.getTotalValue();
        }

        return totalValue;
    }
    
        // Add transaction

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Display transaction history

    public void displayTransactionHistory() {

        if (transactions.isEmpty()) {

            System.out.println(
                    "\nNo transactions available."
            );

            return;
        }

        System.out.println(
                "\n======================== TRANSACTION HISTORY ========================"
        );

        System.out.printf(
                "%-10s %-20s %-15s %-10s %-15s %s%n",
                "ID",
                "Product",
                "Type",
                "Quantity",
                "Remaining",
                "Date & Time"
        );

        System.out.println(
                "---------------------------------------------------------------------"
        );

        for (Transaction transaction : transactions) {

            transaction.displayTransaction();
        }

        System.out.println(
                "---------------------------------------------------------------------"
        );
    }
}