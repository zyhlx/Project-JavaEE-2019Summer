package Servlet;

import bean.Painting;
import dao.factory.DAOFactory;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

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

        Painting[] hotPaintings = DAOFactory.getIPaintingDAOInstance().getHotPaintings();
        Painting[] newPaintings = DAOFactory.getIPaintingDAOInstance().getNewPostPaintings();

        request.setAttribute("hotPaintings", hotPaintings);
        request.setAttribute("lastPublish",newPaintings);
        request.setAttribute("flag","yes");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
