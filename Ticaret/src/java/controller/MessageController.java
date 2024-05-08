package controller;

import dao.MessageDAO;
import entities.Message;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class MessageController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    private List<Message> messages;
    private Message newMessage;
    private Message selectedMessage;

    private final MessageDAO messageDAO;

    public MessageController() {
        messageDAO = new MessageDAO();
    }

    @PostConstruct
    public void init() {
        refreshMessages();
        newMessage = new Message();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage() {
        try {
            messageDAO.sendMessage(newMessage);
            refreshMessages();
            newMessage = new Message(); // Gönderimden sonra formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Mesaj gönderilirken hata oluştu", ex);
        }
    }

    public void deleteMessage(int messageId) {
        try {
            messageDAO.deleteMessage(messageId);
            refreshMessages();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Mesaj silinirken hata oluştu: " + messageId, ex);
        }
    }

    public void updateMessage() {
        try {
            messageDAO.updateMessage(selectedMessage);
            refreshMessages();
            selectedMessage = null; // Güncellemeden sonra seçili mesajı temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Mesaj güncellenirken hata oluştu: " + selectedMessage.getMessageId(), ex);
        }
    }

    public void editMessage(Message message) {
        setSelectedMessage(message); // Seçilen mesajı belirtilen mesaj olarak ayarla
    }

    private void refreshMessages() {
        try {
            messages = messageDAO.getAllMessages();
            LOGGER.info("Mesaj listesi boyutu: " + messages.size()); // Mesaj listesinin boyutunu günlüğe kaydet
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Mesajlar alınırken hata oluştu", ex);
        }
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(Message newMessage) {
        this.newMessage = newMessage;
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
}
