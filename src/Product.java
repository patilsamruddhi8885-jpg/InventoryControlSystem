public class Product {

    private String productId;
    private String productName;
    private String category;
    private double price;
    private int quantity;
    private int reorderLevel;

    // Constructor
    public Product(String productId, String productName,
                   String category, double price,
                   int quantity, int reorderLevel) {

        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    // Getters

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    // Update quantity

    public void addStock(int amount) {
        quantity = quantity + amount;
    }

    public boolean removeStock(int amount) {

        if (amount <= 0 || amount > quantity) {
            return false;
        }

        quantity = quantity - amount;
        return true;
    }

    // Update product details

    public void updateProduct(String productName,
                              String category,
                              double price,
                              int reorderLevel) {

        this.productName = productName;
        this.category = category;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    // Check low stock

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    // Calculate total value of this product

    public double getTotalValue() {
        return price * quantity;
    }
}
