import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String PRODUCTS_FILE =
            "data/products.txt";

    private static final String TRANSACTIONS_FILE =
            "data/transactions.txt";

    // Create data folder and files

    public static void initializeFiles() {

        try {

            // Create data folder
            File folder = new File(DATA_FOLDER);

            if (!folder.exists()) {
                folder.mkdir();
            }

            // Create products file
            File productsFile =
                    new File(PRODUCTS_FILE);

            if (!productsFile.exists()) {
                productsFile.createNewFile();
            }

            // Create transactions file
            File transactionsFile =
                    new File(TRANSACTIONS_FILE);

            if (!transactionsFile.exists()) {
                transactionsFile.createNewFile();
            }

            System.out.println(
                    "Data files initialized successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error creating data files."
            );
        }
    }

        // Save all products to file

    public static void saveAllProducts(Inventory inventory) {

        try {

            FileWriter writer =
                    new FileWriter(PRODUCTS_FILE);

            for (Product product : inventory.getProducts()) {

                writer.write(
                        product.getProductId() + "|" +
                        product.getProductName() + "|" +
                        product.getCategory() + "|" +
                        product.getPrice() + "|" +
                        product.getQuantity() + "|" +
                        product.getReorderLevel() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving product data."
            );
        }
    }

        // Load products from file

    public static void loadProducts(Inventory inventory) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 6) {

                    String productId = data[0];
                    String productName = data[1];
                    String category = data[2];

                    double price =
                            Double.parseDouble(data[3]);

                    int quantity =
                            Integer.parseInt(data[4]);

                    int reorderLevel =
                            Integer.parseInt(data[5]);

                    Product product = new Product(
                            productId,
                            productName,
                            category,
                            price,
                            quantity,
                            reorderLevel
                    );

                    inventory.addProduct(product);
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error loading product data."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid product data found."
            );
        }
    }

        // Save transaction to file

    public static void saveTransaction(Transaction transaction) {

        try {

            FileWriter writer =
                    new FileWriter(TRANSACTIONS_FILE, true);

            writer.write(
                    transaction.getProductId() + "|" +
                    transaction.getProductName() + "|" +
                    transaction.getType() + "|" +
                    transaction.getQuantity() + "|" +
                    transaction.getRemainingStock() + "|" +
                    transaction.getDateTime() + "\n"
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving transaction."
            );
        }
    }

    
    // Load transactions from file

    public static void loadTransactions(Inventory inventory) {

        try (BufferedReader reader =
                new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", 6);

                if (data.length == 6) {

                    String productId = data[0];
                    String productName = data[1];
                    String type = data[2];
                    int quantity = Integer.parseInt(data[3]);
                    int remainingStock = Integer.parseInt(data[4]);
                    LocalDateTime dateTime =
                            LocalDateTime.parse(data[5]);

                   Transaction transaction = new Transaction(
                            productId,
                            productName,
                            type,
                            quantity,
                            remainingStock,
                            dateTime
                    );

                    inventory.addTransaction(transaction);
                }
            }

        } catch (IOException e) {

            System.out.println("Error loading transactions.");

        } catch (Exception e) {

            System.out.println("Invalid transaction data found.");
        }
    }
}