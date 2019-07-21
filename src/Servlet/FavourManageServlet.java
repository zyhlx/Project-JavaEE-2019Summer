package Servlet;

import Services.FavoursService;
import Services.ServicesImpl.FavoursServiceImpl;
import bean.Favour;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FavourManageServlet",value = "/favourManage")
public class FavourManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String function = request.getParameter("function");
String favourID = request.getParameter("favourID");
        FavoursService favoursService = new FavoursServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
switch (function){
    // 删除用户
    case "0":
        favoursService.delete(Integer.parseInt(favourID));
        String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
        PrintWriter out = response.getWriter();
        out.println(temp);
        out.flush();
        out.close();
        break;
        // 修改权限
    case "1":

        Favour result = favoursService.getFavourByFavourID(Integer.parseInt(favourID));
        String temp1 = "{\"type\":\"true\",\"msg\":\"修改访问权限成功\"}";
        PrintWriter out1 = response.getWriter();
        if (result !=null) {

            favoursService.changeOpen(result);
        }
        else {
            temp1 = "{\"type\":\"false\",\"msg\":\"修改访问权限失败\"}";

        }
        out1.println(temp1);
        out1.flush();
        out1.close();
}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }
}
