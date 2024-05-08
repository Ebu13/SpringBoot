package entities;

import java.sql.Timestamp;

public class ReturnAndCancellationRequest {

    public int requestId;
    public int orderId;
    public int userId;
    public Timestamp requestDate;
    public String requestType;
    public String status;

    public ReturnAndCancellationRequest(int requestId, int orderId, int userId, Timestamp requestDate, String requestType, String status) {
        this.requestId = requestId;
        this.orderId = orderId;
        this.userId = userId;
        this.requestDate = requestDate;
        this.requestType = requestType;
        this.status = status;
    }

    public ReturnAndCancellationRequest(int orderId, int userId, Timestamp requestDate, String requestType, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.requestDate = requestDate;
        this.requestType = requestType;
        this.status = status;
    }

    public ReturnAndCancellationRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
