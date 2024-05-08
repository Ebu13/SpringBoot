package entities;

import java.sql.Timestamp;

public class Stock {

    public int stockId;
    public int productId;
    public int quantity;
    public Timestamp lastUpdate;

    public Stock(int stockId, int productId, int quantity, Timestamp lastUpdate) {
        this.stockId = stockId;
        this.productId = productId;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public Stock(int productId, int quantity, Timestamp lastUpdate) {
        this.productId = productId;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public Stock() {
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
