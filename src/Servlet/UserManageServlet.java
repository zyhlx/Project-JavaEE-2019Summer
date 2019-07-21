package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.LoginService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.LoginServiceImpl;
import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserManageServlet", value = "/userManagement")
public class UserManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getParameter("function");
        String userID = request.getParameter("userID");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserDetailService userDetailService = new UserDetailServiceImpl();
        LoginService loginService = new LoginServiceImpl();
        switch (function){
            // 0. 删除用户
            case "0":
                String temp = null;
                int deleteResult = userDetailService.delete(userID);
                // 删干净
                FavoursService favoursService = new FavoursServiceImpl();
                favoursService.delete(userID);
                FriendService friendService = new FriendServiceImpl();

                friendService.deleteAll(Integer.parseInt(userID));
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
                User tempUser = userDetailService.getUser(Integer.parseInt(userID));
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
                // 返回所有用户列表
            case "2":

                List<User> users = userDetailService.getUserAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("./user-management.jsp").forward(request,response);
                break;
                // 添加用户
            case "3":

                String name = request.getParameter("username-add");
                String pwd = request.getParameter("password-add");
                String email = request.getParameter("email-add");
                String tel = request.getParameter("tel-add");
                String address = request.getParameter("address-add");


                int addUserID = userDetailService.getUserID(name);
                response.setContentType("text/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                String temp2 = null;
                // 写入收藏表
                if (addUserID!=0) {
                    temp2 = "{\"type\":\"false\",\"msg\":\"用户名已注册过\"}";
                }else {

                    if (loginService.insertUser(name,pwd,email,tel,address)!=0){
                        temp2 = "{\"type\":\"true\",\"msg\":\"添加成功！\"}";

                    }else {
                        temp2 = "{\"type\":\"false\",\"msg\":\"未知错误请重试\"}";

                    }

                }
                PrintWriter out2 = response.getWriter();
                out2.println(temp2);
                out2.flush();
                out2.close();
                break;





            default:break;

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
