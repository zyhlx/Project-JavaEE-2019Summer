package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.PageService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.PageServiceImpl;
import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.Favour;
import bean.PageInfo;
import bean.Painting;
import bean.User;
import dao.factory.DAOFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FriendSearchServlet", value = "/friendSearch")
public class FriendSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("text/json;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
        // 检索
        String input = request.getParameter("input_text");
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");
        FavoursService favoursService = new FavoursServiceImpl();
        FriendService friendService = new FriendServiceImpl();
        UserDetailService userDetailService = new UserDetailServiceImpl();


        List<User> users = userDetailService.searchUsers(input);
        User myself = null;

        if (users.isEmpty()) {
            request.getRequestDispatcher("./WEB-INF/friends-search.jsp").forward(request, response);
        } else {
            // 判断是否好友
            for (User user : users) {
                // 删掉自己
                if (user.getUserID() == userID) {
                    myself = user;
                } else {
                    // 获得收藏
                    if (friendService.isFriends(userID, user.getUserID())) {
                        user.setIsFriend(1);
                        user.setFavours(favoursService.getFavourByUserID(user.getUserID()));
                    } else {
                        user.setIsFriend(0);
                    }
                }
            }

            if (myself != null) {
                users.remove(myself);
            }


            JSONObject gson = new JSONObject();
            gson.put("users", users);
            String json = gson.toString();
            System.out.println(json);
            // 获取输出流对象
            PrintWriter writer = response.getWriter();
            writer.print(json); // 返回数据给前台
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
