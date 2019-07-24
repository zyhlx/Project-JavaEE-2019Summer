package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.PaintingService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.PaintingServiceImpl;
import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.Favour;
import bean.FriendRelation;
import bean.Painting;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "DisplayServlet")
public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String function = request.getServletPath();
function = function.substring(1, function.length()-8);
System.out.println(function);
switch (function){
    // 获取详情页面
case "paintingDetail": getDetailPainting(request,response);
break;
    case "favour": getFavours(request,response);
    break;
    case "friend": getFriends(request,response);
    break;
    case "user": getUsers(request,response);
    break;
    case "work": getWork(request,response);
    break;
    case "userDetail": getUserDetail(request,response);
    break;
}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }


    private void getDetailPainting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int artworkID = Integer.parseInt(request.getParameter("paintingID"));

        PaintingService paintingService = new PaintingServiceImpl();


        Painting painting = null;

        // 获得对应artworkID的painting对象
        painting = paintingService.getOnePainting(artworkID);
// 热度加一
        painting.setMsrp(painting.getMsrp().add(new BigDecimal(1)));
        paintingService.update(painting);
        // 返回painting给jsp
        request.setAttribute("painting", painting);
        request.getRequestDispatcher("./detail.jsp").forward(request,response);
    }

    private void getFavours (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");

        FavoursService favoursService = new FavoursServiceImpl();
        List<Favour> favours =favoursService.getFavourByUserID(userID);
// 返回painting给jsp
        request.setAttribute("favours", favours);
        request.getRequestDispatcher("./WEB-INF/favours.jsp").forward(request, response);

    }

    private void getFriends(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDetailService userDetailService = new UserDetailServiceImpl();
        List<User> users = userDetailService.getUserAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("./WEB-INF/user-management.jsp").forward(request,response);
    }

    private void getWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int artworkID = Integer.parseInt(request.getParameter("artworkID"));

        PaintingService paintingService = new PaintingServiceImpl();
        Painting painting = null;
        // 新增作品
        if (artworkID == 0) {
            painting = new Painting();
            painting.setPaintingID(artworkID);
        }
        // 修改作品
        else {
            // 获得对应artworkID的painting对象
            painting = paintingService.getOnePainting(artworkID);
        }


        // 返回painting给jsp
        request.setAttribute("painting", painting);
        request.getRequestDispatcher("./WEB-INF/work-management.jsp").forward(request,response);
    }

    private void getUserDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserDetailService userDetailService = new UserDetailServiceImpl();
        FavoursService favoursService = new FavoursServiceImpl();

        //主页显示这些内容
        User user = userDetailService.getUser(userID);
        List<Favour> favoursShow = favoursService.getShowFavours(userID);
//        user.setFavours(userDetailService.getFavourPaintings(userID));
//        user.setFriends(userDetailService.getFriend(userID));

        request.setAttribute("user", user);
        request.setAttribute("favoursShow", favoursShow);
        request.getRequestDispatcher("./user-detail.jsp").forward(request, response);

    }

}
