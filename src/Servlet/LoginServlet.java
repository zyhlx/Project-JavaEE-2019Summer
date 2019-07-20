package Servlet;

import bean.User;
import dao.IUserDAO;
import dao.factory.DAOFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet.LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession(true);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        IUserDAO userDAO = DAOFactory.getIUserDAOInstance();
        List<User> users = userDAO.login(name, pwd);
        if (users.size()>0) {
            session.setAttribute("user", name);
            session.setAttribute("userID", users.get(0).getUserID());
            session.setAttribute("userType", users.get(0).getType());
            String temp = "{\"type\":\"true\",\"msg\":\"登陆成功\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }else {
            String temp = "{\"type\":\"false\",\"msg\":\"用户名密码错误\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
