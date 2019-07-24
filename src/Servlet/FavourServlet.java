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

@WebServlet(name = "Servlet.FavourServlet")
public class FavourServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getServletPath();
        function = function.substring(1, function.length()-7);
        System.out.println(function);
        switch (function){
            case "add" :add(request,response);
            break;
            case "delete": delete(request,response);
            break;
            case "change": change(request,response);
            break;
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String artworkID = request.getParameter("artworkID");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 查找用户ID
        int userID = DAOFactory.getIUserDAOInstance().getUserID(userName);


        // 避免重复收藏
        FavoursService favoursService = new FavoursServiceImpl();
        if (favoursService.isFavoured(userID, Integer.parseInt(artworkID))) {
            String temp = "{\"type\":\"false\",\"msg\":\"您已经收藏过了哦\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
            return;
        }

        // 写入收藏表
        Favour tempFavour = new Favour();
        tempFavour.setUserID(userID);
        tempFavour.setPaintingID(Integer.parseInt(artworkID));
        favoursService.insert(tempFavour);
        String temp = "{\"type\":\"true\",\"msg\":\"收藏成功\"}";
        PrintWriter out = response.getWriter();
        out.println(temp);
        out.flush();
        out.close();

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String favourID = request.getParameter("favourID");
        FavoursService favoursService = new FavoursServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
                favoursService.delete(Integer.parseInt(favourID));
                String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();

        }

    private void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String favourID = request.getParameter("favourID");
        FavoursService favoursService = new FavoursServiceImpl();

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

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
