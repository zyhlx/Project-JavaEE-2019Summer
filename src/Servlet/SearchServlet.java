package Servlet;

import Services.FavoursService;
import Services.FriendService;
import Services.PageService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.PageServiceImpl;
import Services.ServicesImpl.UserDetailServiceImpl;
import Services.UserDetailService;
import bean.PageInfo;
import bean.Painting;
import bean.User;
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

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getServletPath();
        function = function.substring(1, function.length()-7);
        System.out.println(function);
        switch (function){
            case "friend": searchFriend(request,response);
            break;
            case "painting": searchPainting(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }

    private void searchFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码格式
        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
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

    private void searchPainting(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置响应编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //调用servicenew出对象
        PageService service = new PageServiceImpl();
        String input_info = req.getParameter("input_text");
        String method_info = req.getParameter("method");
        PageInfo<Painting> news = service.findAlls(Painting.class,method_info,input_info,
                Integer.parseInt(req.getParameter("pageNum")), 5);
        news.setTotal(service.getTotalCount(method_info,input_info));
        System.out.println("pageNum=====>" + req.getParameter("pageNum"));      news.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
        System.out.println("总记录数===》" + news.getTotal());
        JSONObject gson = new JSONObject();
        gson.put("page",news);
        String json = gson.toString();
        System.out.println(json);
        // 获取输出流对象
        PrintWriter writer = resp.getWriter();
        writer.print(json); // 返回数据给前台
        writer.close();
    }
}
