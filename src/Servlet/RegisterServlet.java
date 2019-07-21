package Servlet;

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

@WebServlet(name = "Servlet.RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username-register");
        String pwd = request.getParameter("password-register");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");

        IUserDAO userDAO = DAOFactory.getIUserDAOInstance();
        int userID = userDAO.getUserID(name);
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
            if (userDAO.insertUser(name,pwd,email,tel,address)!=0){
                session.setAttribute("user", name);

                //TODO 获得id
                userID = userDAO.getUserID(name);
                //session.setAttribute("userID", userID);
                //session.setAttribute("id", "very good!");
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
