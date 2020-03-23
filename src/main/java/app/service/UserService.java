package app.service;

import app.dao.UserDAO;
import app.dao.UserDaoFactory;
import app.entities.User;
import app.utils.PropertyReaderHelper;

import java.util.List;

public class UserService {

    private static UserService userService;
    private static UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getUserService() {
        if (userService == null) {
            String daoType = new PropertyReaderHelper().getProperty("typeDAO");
            UserDAO userDAO = UserDaoFactory.getUserDAO(daoType);
            userService = new UserService(userDAO);
        }
        return userService;
    }

    public boolean addUser(User user) {
        if (userDAO.getUserByLogin(user.getLogin()) != null) {
            return false;
        }
        userDAO.addUser(user);
        return true;
    }

    public boolean updateUser(User user, String password) {
        if (userDAO.validateUser(user.getLogin(), password)) {
            userDAO.updateUser(user);
            return true;
        }
        return false;
    }

    public void deleteUser(String login) {
        userDAO.deleteUser(login);
    }

    public List<User> getUsersList() {
        return userDAO.getAllUsers();
    }
}
