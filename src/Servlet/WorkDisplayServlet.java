package Servlet;

import bean.Painting;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WorkDisplayServlet", value = "/workDisplay")
public class WorkDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int artworkID = Integer.parseInt(request.getParameter("artworkID"));

        Painting painting = null;
        // 新增作品
        if (artworkID == 0) {
        painting = new Painting();
        painting.setPaintingID(artworkID);
        }
        // 修改作品
        else {
            // 获得对应artworkID的painting对象
            String query = "SELECT * FROM paintings WHERE PaintingID=" + "'" + artworkID + "'";
            painting = DAOFactory.getIPaintingDAOInstance().getPaintings(query).get(0);
        }


        // 返回painting给jsp
        request.setAttribute("painting", painting);
        request.getRequestDispatcher("./work-management.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}

