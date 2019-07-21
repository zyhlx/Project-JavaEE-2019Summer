package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.PageService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.PageServiceImpl;
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
        // 检索
        String input = request.getParameter("input_text");
        HttpSession session = request.getSession(true);
        int userID = (Integer)session.getAttribute("userID");
        FavoursService favoursService = new FavoursServiceImpl();
        FriendService friendService = new FriendServiceImpl();

        List<User> users = friendService.searchFriends(input,userID);
        // 判断是否好友
        for (User user:users) {
            // 获得收藏
            user.setFavours(favoursService.getFavourByUserID(userID));
            if (DAOFactory.getIUserDAOInstance().isFriend(userID, user.getUserID())){
                user.setIsFriend(1);
            }
            else {
                user.setIsFriend(0);
            }
        }


        JSONObject gson = new JSONObject();
        gson.put("users",users);
        String json = gson.toString();
        System.out.println(json);
        // 获取输出流对象
        PrintWriter writer = response.getWriter();
        writer.print(json); // 返回数据给前台
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
