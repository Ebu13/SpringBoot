package controller;

import dao.UserDAO;
import entities.User;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class UserController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private List<User> users;
    private User selectedUser;
    private User newUser;

    private final UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    @PostConstruct
    public void init() {
        refreshUsers();
        selectedUser = new User();
        newUser = new User();
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUser(int userId) {
        try {
            userDAO.deleteUser(userId);
            refreshUsers();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting user with id: " + userId, ex);
        }
    }

    public void updateUser() {
        try {
            userDAO.updateUser(selectedUser);
            refreshUsers();
            selectedUser = new User(); // Güncelleme sonrasında formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating user with id: " + selectedUser.getUserId(), ex);
        }
    }

    public void addUser() {
        try {
            userDAO.addUser(newUser);
            refreshUsers();
            newUser = new User();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding user", ex);
        }
    }

    public void editUser(User user) {
        selectedUser = user;
    }

    private void refreshUsers() {
        try {
            users = userDAO.getAllUsers();
            LOGGER.info("User list size: " + users.size()); // Kullanıcı listesi boyutunu logla
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching users", ex);
        }
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
