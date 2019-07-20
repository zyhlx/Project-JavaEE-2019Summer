package Servlet;

import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDetailServlet",value = "/userDetail")
public class UserDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserDetailService userDetailService = new UserDetailServiceImpl();

        //主页显示这些内容
        User user = userDetailService.getUser(userID);
        user.setFavours(userDetailService.getFavourPaintings(userID));
        user.setFriends(userDetailService.getFriend(userID));

        request.setAttribute("user", user);
        request.getRequestDispatcher("./user-detail.jsp").forward(request, response);

    }
}
