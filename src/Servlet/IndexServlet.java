package Servlet;

import Services.PaintingService;
import Services.RecommendService;
import Services.ServicesImpl.PaintingServiceImpl;
import Services.ServicesImpl.RecommendServiceImpl;
import bean.Painting;
import bean.User;
import dao.factory.DAOFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet.IndexServlet" , value = "/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession(true);
        PaintingService paintingService = new PaintingServiceImpl();
        RecommendService recommendService = new RecommendServiceImpl();

        Painting[] hotPaintings =paintingService.getHotPaintings();
        Painting[] newPaintings = paintingService.getNewPostPaintings();
        List<Painting> recommendPainting;
        List<User> recommendUsers;
        if (httpSession.getAttribute("userID")!=null){
            recommendPainting = recommendService.paintingRecommend((int)(httpSession.getAttribute("userID")));
            recommendUsers = recommendService.friendRecommend((int)(httpSession.getAttribute("userID")));
        }else {
            recommendPainting = recommendService.paintingRecommend(0);
            recommendUsers = recommendService.friendRecommend(0);
        }

        request.setAttribute("hotPaintings", hotPaintings);
        request.setAttribute("lastPublish",newPaintings);
        request.setAttribute("recommendFriends", recommendUsers);
        request.setAttribute("recommendPaintings",recommendPainting);
        request.setAttribute("flag","yes");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
