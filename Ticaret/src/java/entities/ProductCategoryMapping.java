package entities;

public class ProductCategoryMapping {

    public int productId;
    public int categoryId;

    public ProductCategoryMapping(int productId, int categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public ProductCategoryMapping(int categoryId) {
        this.categoryId = categoryId;
    }

    public ProductCategoryMapping() {
    }
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
