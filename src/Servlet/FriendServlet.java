package Servlet;

import Services.FriendService;
import Services.ServicesImpl.FriendServiceImpl;
import bean.FriendRelation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FriendServlet")
public class FriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getServletPath();
        function = function.substring(1, function.length()-7);
        System.out.println(function);
        switch (function){
            case "send": sendRequest(request,response);break;
            case "delete" :deleteFriend(request,response);break;
            case "accept": acceptRequest(request,response);break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }

    private void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientID");
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");
        FriendService friendService = new FriendServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        friendService.delete(userID, Integer.parseInt(clientID));
        String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
        PrintWriter out = response.getWriter();
        out.println(temp);
        out.flush();
        out.close();

    }

    private void sendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientID");
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");
        FriendService friendService = new FriendServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

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

    }

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientID");
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");
        FriendService friendService = new FriendServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

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
