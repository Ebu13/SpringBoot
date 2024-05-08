package dao;

import entities.OrderDetail;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDetailDAO.class.getName());

    public List<OrderDetail> getAllOrderDetails() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM OrderDetails")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setDetailId(resultSet.getInt("detail_id"));
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                orderDetail.setProductId(resultSet.getInt("product_id"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getBigDecimal("price"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching order details", e);
        }
        return orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, orderDetail.getOrderId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setBigDecimal(4, orderDetail.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding order detail", e);
        }
    }

    public void deleteOrderDetail(int detailId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM OrderDetails WHERE detail_id = ?")) {
            statement.setInt(1, detailId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting order detail with id: " + detailId, e);
        }
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE OrderDetails SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE detail_id = ?")) {
            statement.setInt(1, orderDetail.getOrderId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setBigDecimal(4, orderDetail.getPrice());
            statement.setInt(5, orderDetail.getDetailId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating order detail with id: " + orderDetail.getDetailId(), e);
        }
    }
}
