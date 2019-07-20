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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FriendsDisplayServlet", value = "/friendsDisplay")
public class FriendsDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = 1;
// 获取好友列表
        List<User> friends = DAOFactory.getIUserDAOInstance().getFriends(userID);
// 获得收藏
        for (User user : friends) {
            user.setFavours(DAOFactory.getIPaintingDAOInstance().getFavourPaintings(user.getUserID()));
        }
        // 返回friends给jsp
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("./friends-display.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
