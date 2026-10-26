import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String productId;
    private String productName;
    private String type;
    private int quantity;
    private int remainingStock;
    private LocalDateTime dateTime;

    // Constructor
    public Transaction(String productId,
                       String productName,
                       String type,
                       int quantity,
                       int remainingStock) {

        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.quantity = quantity;
        this.remainingStock = remainingStock;
        this.dateTime = LocalDateTime.now();
    }

    // Constructor for loading existing transactions

    public Transaction(String productId,
                    String productName,
                    String type,
                    int quantity,
                    int remainingStock,
                    LocalDateTime dateTime) {

        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.quantity = quantity;
        this.remainingStock = remainingStock;
        this.dateTime = dateTime;
    }

    // Display transaction
    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.printf(
                "%-10s %-20s %-15s %-10d %-15d %s%n",
                productId,
                productName,
                type,
                quantity,
                remainingStock,
                dateTime.format(formatter)
        );
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}