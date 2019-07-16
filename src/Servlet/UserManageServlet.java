package Servlet;

import bean.User;
import dao.IUserDAO;
import dao.factory.DAOFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserManageServlet", value = "/userManagement")
public class UserManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserDAO userDAO = DAOFactory.getIUserDAOInstance();
        String function = request.getParameter("function");
        String userID = request.getParameter("userID");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        switch (function){
            // 0. 删除用户
            case "0":
                String temp = null;
                int deleteResult = userDAO.delete(userID);
                if (deleteResult == 1) {
                    temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
                }
                else {
                    temp = "{\"type\":\"false\",\"msg\":\"删除失败\"}";
                }
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();
                break;
                // 1. 修改用户权限
            case "1":
                // 获得用户对象
                String queryForUser = "SELECT * FROM users WHERE userID=" + "'" + userID + "'";
                User tempUser = userDAO.getUser(queryForUser).get(0);
                String temp1 = null;
                // 修改用户权限
                try {
                    tempUser.changePermission();
                    temp1 = "{\"type\":\"true\",\"msg\":\"修改权限成功\"}";
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                    temp1 = "{\"type\":\"true\",\"msg\":\"修改权限失败\"}";
                }
                PrintWriter out1 = response.getWriter();
                out1.println(temp1);
                out1.flush();
                out1.close();
                break;

                default:break;

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
