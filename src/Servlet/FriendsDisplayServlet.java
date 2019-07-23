package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import bean.Favour;
import bean.FriendRelation;
import bean.Painting;
import bean.User;
import dao.IFriendRelationDAO;
import dao.factory.DAOFactory;
import db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FriendsDisplayServlet", value = "/friendsDisplay")
public class FriendsDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");
        FriendService friendService = new FriendServiceImpl();
        // 获取好友列表

        List<FriendRelation> friends = friendService.getFriends(userID,1);
// 获得收藏
        for (FriendRelation friendRelation : friends) {
            FavoursService favoursService = new FavoursServiceImpl();
            friendRelation.getClient().setFavours(favoursService.getFavourByUserID(friendRelation.getClientID()));
        }
        // 返回friends给jsp
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("./WEB-INF/friends-display.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
