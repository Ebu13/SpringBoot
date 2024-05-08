package entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {

    public int productId;
    public int userId;
    public String productName;
    public String description;
    public BigDecimal price;
    public Timestamp creationDate;

    public Product(int productId, int userId, String productName, String description, BigDecimal price, Timestamp creationDate) {
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Product(int userId, String productName, String description, BigDecimal price, Timestamp creationDate) {
        this.userId = userId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
