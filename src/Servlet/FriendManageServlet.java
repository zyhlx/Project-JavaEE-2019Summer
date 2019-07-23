package Servlet;

import Services.FriendService;
import Services.ServicesImpl.FriendServiceImpl;
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
        FriendService friendService = new FriendServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        switch (function){
            // 删除好友
            case "0":

               friendService.delete(userID,Integer.parseInt(clientID));
                String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();
                break;
            // 添加好友
            case "1":
                FriendRelation friendRelation = new FriendRelation();
                String temp1 = null;
                if (friendService.getFriendRealtion(userID, Integer.parseInt(clientID)) != null) {
                    temp1 = "{\"type\":\"false\",\"msg\":\"已经发送过请求了哦\"}";
                }
                else {

                    friendRelation.setClientID(Integer.parseInt(clientID));
                    friendRelation.setPatronID(userID);
                    friendRelation.setAccepted(0);
                    friendService.insert(friendRelation);
                    temp1 = "{\"type\":\"true\",\"msg\":\"发送请求成功\"}";
                }
                PrintWriter out1 = response.getWriter();
                out1.println(temp1);
                out1.flush();
                out1.close();
                // 接受请求
            case"2":
                int myClientID = (Integer) session.getAttribute("userID");
                String patronID = request.getParameter("patronID");
                FriendRelation friendRelation1 = friendService.getFriendRealtion(Integer.parseInt(patronID), myClientID);
                friendRelation1.acceptRequest();
                String temp2 = "{\"type\":\"true\",\"msg\":\"添加成功\"}";
                PrintWriter out2 = response.getWriter();
                out2.println(temp2);
                out2.flush();
                out2.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
