package Servlet;

import bean.Favour;
import bean.FriendRelation;
import bean.Painting;
import bean.User;
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
        HttpSession session = request.getSession(true);
        int userID = (Integer)session.getAttribute("userID");
        // 获取好友列表
        String queryForFriends = "SELECT * FROM friends WHERE patronID=" + "'" + userID + "' AND accepted='1'";
List<FriendRelation> friends = DAOFactory.getIFriendRelationDAOInstance().getFriends(queryForFriends);
// 获得收藏
        for(FriendRelation friendRelation:friends) {
            String queryForFavours = "SELECT * FROM favours WHERE userID=" + "'" + friendRelation.getClient().getUserID() + "'" +" AND open='1'";
            friendRelation.getClient().setFavours(DAOFactory.getIFavourDAOInstance().getFavour(queryForFavours));
        }
        // 返回friends给jsp
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("./friends-display.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }
}
