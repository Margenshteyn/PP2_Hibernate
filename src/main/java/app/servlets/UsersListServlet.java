package app.servlets;

import app.entities.User;
import app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class UsersListServlet extends HttpServlet {

    private UserService userService = UserService.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getUsersList();
        req.setAttribute("usersList", users);
       req.getRequestDispatcher("views/list.jsp").forward(req, resp);
//       getServletContext().getRequestDispatcher("views/list.jsp").forward(req, resp);
    }
}
