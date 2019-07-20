package Servlet;

import bean.Favour;
import bean.FriendRelation;
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

@WebServlet(name = "FriendManageServlet", value = "/friendManage")
public class FriendManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getParameter("function");
        String clientID = request.getParameter("clientID");
        HttpSession session = request.getSession(true);
        int userID = (Integer)session.getAttribute("userID");

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        switch (function){
            // 删除好友
            case "0":
                String deleteFriend = "DELETE FROM friends WHERE patronID=" + "'" + userID + "'" + "AND clientID=" + "'" + clientID + "'";
                DAOFactory.getIFriendRelationDAOInstance().delete(deleteFriend);
                String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();
                break;
            // 添加好友
            case "1":
                FriendRelation friendRelation = new FriendRelation();
                friendRelation.setClientID(Integer.parseInt(clientID));
                friendRelation.setPatronID(userID);
                DAOFactory.getIFriendRelationDAOInstance().insert(friendRelation);
                String temp1 = "{\"type\":\"true\",\"msg\":\"添加成功\"}";
                PrintWriter out1 = response.getWriter();
                out1.println(temp1);
                out1.flush();
                out1.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
