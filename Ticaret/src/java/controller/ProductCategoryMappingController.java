package controller;

import dao.ProductCategoryMappingDAO;
import entities.ProductCategoryMapping;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductCategoryMappingController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ProductCategoryMappingController.class.getName());

    private List<ProductCategoryMapping> mappings;
    private ProductCategoryMapping selectedMapping;
    private ProductCategoryMapping newMapping;

    private final ProductCategoryMappingDAO mappingDAO;

    public ProductCategoryMappingController() {
        mappingDAO = new ProductCategoryMappingDAO();
    }

    @PostConstruct
    public void init() {
        refreshMappings();
        selectedMapping = new ProductCategoryMapping();
        newMapping = new ProductCategoryMapping();
    }

    public List<ProductCategoryMapping> getMappings() {
        return mappings;
    }

    public void deleteMapping(int productId, int categoryId) {
        try {
            mappingDAO.deleteMapping(productId, categoryId);
            refreshMappings();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting product category mapping", ex);
        }
    }

    public void addMapping() {
        try {
            mappingDAO.addMapping(newMapping);
            refreshMappings();
            newMapping = new ProductCategoryMapping();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding product category mapping", ex);
        }
    }

    public void updateMapping() {
        try {
            mappingDAO.updateMapping(selectedMapping);
            refreshMappings();
            selectedMapping = new ProductCategoryMapping();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating product category mapping", ex);
        }
    }

    private void refreshMappings() {
        try {
            mappings = mappingDAO.getAllMappings();
            LOGGER.info("Product category mappings list size: " + mappings.size());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching product category mappings", ex);
        }
    }

    public ProductCategoryMapping getSelectedMapping() {
        return selectedMapping;
    }

    public void setSelectedMapping(ProductCategoryMapping selectedMapping) {
        this.selectedMapping = selectedMapping;
    }

    public ProductCategoryMapping getNewMapping() {
        return newMapping;
    }

    public void setNewMapping(ProductCategoryMapping newMapping) {
        this.newMapping = newMapping;
    }
}
