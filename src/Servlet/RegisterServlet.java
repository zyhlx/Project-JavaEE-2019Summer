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

@WebServlet(name = "Servlet.RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username-register");
        String pwd = request.getParameter("password-register");
        String email = request.getParameter("email");
        LoginService loginService = new LoginServiceImpl();

        int userID = loginService.getUserID(name);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 写入收藏表
        if (userID!=0) {
            String temp = "{\"type\":\"false\",\"msg\":\"用户名已注册过\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }else {
            HttpSession session = request.getSession(true);
            if (loginService.insertUser(name,pwd,email)!=0){
                User user = loginService.login(name, pwd);
                session.setAttribute("user", name);

                session.setAttribute("userID", user.getUserID());
                session.setAttribute("userType", user.getType());
                String temp = "{\"type\":\"true\",\"msg\":\"注册成功！\"}";

                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();
            }else {
                String temp = "{\"type\":\"false\",\"msg\":\"未知错误请重试\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();
            }

        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
