package Servlet;

import Services.FavoursService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.Favour;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserDetailServlet",value = "/userDetail")
public class UserDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(true);
        UserDetailService userDetailService = new UserDetailServiceImpl();
        int userID = Integer.parseInt(request.getParameter("userID"));
        String newName = request.getParameter("username");
        String oldName = request.getParameter("oldName");
        String pwd = request.getParameter("password");
        String email = request.getParameter("email");
        String signature = request.getParameter("signature");
        int result =userDetailService.updateUserInformation(userID,newName,oldName,pwd,email,signature);
        String temp = "";
        if (result==1){
            temp = "{\"type\":\"true\",\"msg\":\"修改成功！\"}";
            session.setAttribute("user", newName);
        }else if (result == 0){
            temp = "{\"type\":\"false\",\"msg\":\"密码错误！\"}";
        }else if (result == -1) {
            temp = "{\"type\":\"false\",\"msg\":\"用户名已被占用！\"}";
        }else {
            temp = "{\"type\":\"false\",\"msg\":\"未知错误！\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(temp);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.setAttribute("favoursShow", favoursService);
        request.getRequestDispatcher("./user-detail.jsp").forward(request, response);

    }
}
