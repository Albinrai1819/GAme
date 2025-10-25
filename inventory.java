package utils;


public class inventory {
    private String itemName;
    private int quantity;
    private double price;
    private String attribute;

    public inventory(String itemName, int quantity, double price, String attribute) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.attribute = attribute;
    }

    public inventory(inventory shopTobag) {
        this.itemName = shopTobag.getItemName();
        this.quantity = shopTobag.getQuantity();
        this.price = shopTobag.getPrice();
        this.attribute = shopTobag.getAttribute();
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-3d | %-7.1f | %-8s |",
                itemName, quantity, price, attribute);
    }
}
