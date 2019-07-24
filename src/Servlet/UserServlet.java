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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String function = request.getServletPath();
        function = function.substring(1, function.length()-5);
        System.out.println(function);
        switch (function){
            case "changeInfo": changeInfo(request, response);
                break;
            case "delete": delete(request,response);
                break;
            case "changePermission": changePermission(request,response);
                break;
            case "addUser": addUser(request,response);
                break;

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    private void changeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(true);
        UserDetailService userDetailService = new UserDetailServiceImpl();
        int userID = Integer.parseInt(request.getParameter("userID"));
        String newName = request.getParameter("username");
        String oldName = request.getParameter("oldName");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        String signature = request.getParameter("signature");
        int result =userDetailService.updateUserInformation(userID,newName,oldName,pwd,email,signature);
        String temp = "";
        if (result==1){
            temp = "{\"type\":\"true\",\"msg\":\"修改成功！\"}";
            session.setAttribute("user", newName);
        }else if (result == 0){
            temp = "{\"type\":\"false\",\"msg\":\"密码错误！\"}";
        }else if (result == -1) {
            temp = "{\"type\":\"false\",\"msg\":\"用户名已被占用！\"}";
        }else {
            temp = "{\"type\":\"false\",\"msg\":\"未知错误！\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(temp);
        out.flush();
        out.close();
    }


    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");

        HttpSession session = request.getSession(true);
        String userIDOfOperator = "" + (Integer) session.getAttribute("userID");
        if (userID != null && userID.equals((userIDOfOperator))) {
            String alert = "{\"type\":\"false\",\"msg\":\"不能修改自己！\"}";
            PrintWriter out = response.getWriter();
            out.println(alert);
            out.flush();
            out.close();
            return;
        }

        UserDetailService userDetailService = new UserDetailServiceImpl();
        LoginService loginService = new LoginServiceImpl();

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
    }

    private void changePermission(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String userID = request.getParameter("userID");

        HttpSession session = request.getSession(true);
        String userIDOfOperator = "" + (Integer) session.getAttribute("userID");
        if (userID != null && userID.equals((userIDOfOperator))) {
            String alert = "{\"type\":\"false\",\"msg\":\"不能修改自己！\"}";
            PrintWriter out = response.getWriter();
            out.println(alert);
            out.flush();
            out.close();
            return;
        }

        UserDetailService userDetailService = new UserDetailServiceImpl();
        LoginService loginService = new LoginServiceImpl();

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

    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");

        HttpSession session = request.getSession(true);
        String userIDOfOperator = "" + (Integer) session.getAttribute("userID");
        if (userID != null && userID.equals((userIDOfOperator))) {
            String alert = "{\"type\":\"false\",\"msg\":\"不能修改自己！\"}";
            PrintWriter out = response.getWriter();
            out.println(alert);
            out.flush();
            out.close();
            return;
        }

        UserDetailService userDetailService = new UserDetailServiceImpl();
        LoginService loginService = new LoginServiceImpl();

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

    }


}
