package app.servlets;

import app.entities.User;
import app.service.UserService;
import app.service.UserServiceImpl;
import app.utils.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/userUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        User user = getUser(req, resp);
        String password = req.getParameter("password");
        if (userService.updateUser(user, password)) {
            resp.sendRedirect("/list?updatedLogin=" + user.getLogin());
//                getServletContext().getRequestDispatcher("/list?updatedLogin=" + user.getLogin()).forward(req, resp);
        } else {
            resp.sendRedirect("/list?wrongUpdate=" + user.getLogin());
//                getServletContext().getRequestDispatcher("/list?wrongUpdate=" + user.getLogin()).forward(req, resp);
        }
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("newPassword");
        String name = req.getParameter("newName");
        double amount = Double.parseDouble(req.getParameter("newAmount"));
        return new User(login, password, name, amount);
    }
}
