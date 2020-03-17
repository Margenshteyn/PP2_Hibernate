package app.servlets;

import app.service.UserService;
import app.service.UserServiceImpl;
import app.utils.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userDelete")
public class UserDeleteServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login  = req.getParameter("login");
        userService.deleteUser(login);
//        req.setAttribute("DeleteUserLogin", login);
//        resp.sendRedirect("/list?deletedLogin=" + login);
        getServletContext().getRequestDispatcher("/list?deletedLogin=" + login).forward(req, resp);
    }
}
