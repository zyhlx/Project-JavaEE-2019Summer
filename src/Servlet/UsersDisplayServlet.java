package Servlet;

import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.User;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersDisplayServlet", value = "/userDisplay")
public class UsersDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetailService userDetailService = new UserDetailServiceImpl();
        List<User> users = userDetailService.getUserAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("./WEB-INF/user-management.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
