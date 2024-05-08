package dao;

import entities.ProductCategoryMapping;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCategoryMappingDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductCategoryMappingDAO.class.getName());

    public List<ProductCategoryMapping> getAllMappings() {
        List<ProductCategoryMapping> mappings = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM ProductCategoryMapping")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductCategoryMapping mapping = new ProductCategoryMapping();
                mapping.setProductId(resultSet.getInt("product_id"));
                mapping.setCategoryId(resultSet.getInt("category_id"));
                mappings.add(mapping);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching product category mappings", e);
        }
        return mappings;
    }

    public void addMapping(ProductCategoryMapping mapping) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO ProductCategoryMapping (product_id, category_id) VALUES (?, ?)")) {
            statement.setInt(1, mapping.getProductId());
            statement.setInt(2, mapping.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding product category mapping", e);
        }
    }

    public void deleteMapping(int productId, int categoryId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM ProductCategoryMapping WHERE product_id = ? AND category_id = ?")) {
            statement.setInt(1, productId);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting product category mapping with product id: " + productId + " and category id: " + categoryId, e);
        }
    }

    public void updateMapping(ProductCategoryMapping mapping) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE ProductCategoryMapping SET category_id = ? WHERE product_id = ?")) {
            statement.setInt(1, mapping.getCategoryId());
            statement.setInt(2, mapping.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating product category mapping with product id: " + mapping.getProductId(), e);
        }
    }
}
