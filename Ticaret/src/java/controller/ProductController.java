package controller;

import dao.ProductDAO;
import entities.Product;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    private List<Product> products;
    private Product selectedProduct;
    private Product newProduct;

    private final ProductDAO productDAO;

    public ProductController() {
        productDAO = new ProductDAO();
    }

    @PostConstruct
    public void init() {
        refreshProducts();
        selectedProduct = new Product();
        newProduct = new Product();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
            refreshProducts();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting product with id: " + productId, ex);
        }
    }

    public void updateProduct() {
        try {
            productDAO.updateProduct(selectedProduct);
            refreshProducts();
            selectedProduct = new Product(); // Güncelleme sonrasında formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating product with id: " + selectedProduct.getProductId(), ex);
        }
    }

    public void addProduct() {
        try {
            // Örnek olarak, yeni bir ürün eklerken, bazı alanlar otomatik olarak doldurulabilir
            newProduct.setCreationDate(new Timestamp(System.currentTimeMillis()));
            // Fiyat alanını örnek olarak sabit bir değerle doldurduk, gerçek uygulamada bu değer kullanıcı tarafından belirlenebilir
            newProduct.setPrice(new BigDecimal("0.00"));

            productDAO.addProduct(newProduct);
            refreshProducts();
            newProduct = new Product(); // Formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding product", ex);
        }
    }

    public void editProduct(Product product) {
        selectedProduct = product;
    }

    private void refreshProducts() {
        try {
            products = productDAO.getAllProducts();
            LOGGER.info("Product list size: " + products.size()); // Ürün listesi boyutunu logla
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching products", ex);
        }
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }
}
