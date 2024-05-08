package dao;

import entities.Message;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDAO {

    private static final Logger LOGGER = Logger.getLogger(MessageDAO.class.getName());

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM Messages")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessageId(resultSet.getInt("message_id"));
                message.setSenderId(resultSet.getInt("sender_id"));
                message.setReceiverId(resultSet.getInt("receiver_id"));
                message.setMessageText(resultSet.getString("message_text"));
                message.setSendDate(resultSet.getTimestamp("send_date"));
                message.setIsRead(resultSet.getBoolean("is_read"));
                messages.add(message);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching messages", e);
        }
        return messages;
    }

    public void sendMessage(Message message) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO Messages (sender_id, receiver_id, message_text) VALUES (?, ?, ?)")) {
            statement.setInt(1, message.getSenderId());
            statement.setInt(2, message.getReceiverId());
            statement.setString(3, message.getMessageText());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while sending message", e);
        }
    }

    public void deleteMessage(int messageId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM Messages WHERE message_id = ?")) {
            statement.setInt(1, messageId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting message with id: " + messageId, e);
        }
    }

    public void updateMessage(Message message) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE Messages SET sender_id = ?, receiver_id = ?, message_text = ?, is_read = ? WHERE message_id = ?")) {
            statement.setInt(1, message.getSenderId());
            statement.setInt(2, message.getReceiverId());
            statement.setString(3, message.getMessageText());
            statement.setBoolean(4, message.getIsRead());
            statement.setInt(5, message.getMessageId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating message with id: " + message.getMessageId(), e);
        }
    }
}
