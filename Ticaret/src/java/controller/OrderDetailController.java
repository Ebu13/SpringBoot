package controller;

import dao.OrderDetailDAO;
import entities.OrderDetail;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class OrderDetailController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(OrderDetailController.class.getName());

    private List<OrderDetail> orderDetails;
    private OrderDetail selectedOrderDetail;
    private OrderDetail newOrderDetail;

    private final OrderDetailDAO orderDetailDAO;

    public OrderDetailController() {
        orderDetailDAO = new OrderDetailDAO();
    }

    @PostConstruct
    public void init() {
        refreshOrderDetails();
        selectedOrderDetail = new OrderDetail();
        newOrderDetail = new OrderDetail();
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void deleteOrderDetail(int detailId) {
        try {
            orderDetailDAO.deleteOrderDetail(detailId);
            refreshOrderDetails();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting order detail with id: " + detailId, ex);
        }
    }

    public void updateOrderDetail() {
        try {
            orderDetailDAO.updateOrderDetail(selectedOrderDetail);
            refreshOrderDetails();
            selectedOrderDetail = new OrderDetail(); // Güncelleme sonrasında formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating order detail with id: " + selectedOrderDetail.getDetailId(), ex);
        }
    }

    public void addOrderDetail() {
        try {
            orderDetailDAO.addOrderDetail(newOrderDetail);
            refreshOrderDetails();
            newOrderDetail = new OrderDetail();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding order detail", ex);
        }
    }

    public void editOrderDetail(OrderDetail orderDetail) {
        selectedOrderDetail = orderDetail;
    }

    private void refreshOrderDetails() {
        try {
            orderDetails = orderDetailDAO.getAllOrderDetails();
            LOGGER.info("Order detail list size: " + orderDetails.size()); // Sipariş detayı listesi boyutunu logla
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching order details", ex);
        }
    }

    public OrderDetail getSelectedOrderDetail() {
        return selectedOrderDetail;
    }

    public void setSelectedOrderDetail(OrderDetail selectedOrderDetail) {
        this.selectedOrderDetail = selectedOrderDetail;
    }

    public OrderDetail getNewOrderDetail() {
        return newOrderDetail;
    }

    public void setNewOrderDetail(OrderDetail newOrderDetail) {
        this.newOrderDetail = newOrderDetail;
    }
}
