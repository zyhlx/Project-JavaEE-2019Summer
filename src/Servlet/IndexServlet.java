package Servlet;

import Services.PaintingService;
import Services.ServicesImpl.PaintingServiceImpl;
import bean.Painting;
import dao.factory.DAOFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet.IndexServlet" , value = "/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaintingService paintingService = new PaintingServiceImpl();

        Painting[] hotPaintings =paintingService.getHotPaintings();
        Painting[] newPaintings = paintingService.getNewPostPaintings();

        request.setAttribute("hotPaintings", hotPaintings);
        request.setAttribute("lastPublish",newPaintings);
        request.setAttribute("flag","yes");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
