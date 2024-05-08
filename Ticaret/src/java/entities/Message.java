package entities;

import java.sql.Timestamp;

public class Message {

    public int messageId;
    public int senderId;
    public int receiverId;
    public String messageText;
    public Timestamp sendDate;
    public boolean isRead;

    public Message(int messageId, int senderId, int receiverId, String messageText, Timestamp sendDate, boolean isRead) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.sendDate = sendDate;
        this.isRead = isRead;
    }

    public Message(int senderId, int receiverId, String messageText, Timestamp sendDate, boolean isRead) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.sendDate = sendDate;
        this.isRead = isRead;
    }

    public Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

}
