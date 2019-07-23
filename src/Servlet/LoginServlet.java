package Servlet;

import Services.LoginService;
import Services.ServicesImpl.LoginServiceImpl;
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
      login(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
logout(request, response);
    }

    private void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(true);
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("userID");
        httpSession.removeAttribute("userType");
        request.getRequestDispatcher("/index.jsp").forward(
                request, response);

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession(true);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        LoginService loginService = new LoginServiceImpl();
        User user = loginService.login(name,pwd);
        if (user!=null) {
            session.setAttribute("user", name);
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("userType", user.getType());
            loginService.updateLoadTime( user.getUserID());
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
}
