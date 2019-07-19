package Servlet;

import bean.Painting;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailDisplayServlet", value = "/detailDisplay")
public class DetailDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int artworkID = Integer.parseInt(request.getParameter("paintingID"));

        Painting painting = null;

            // 获得对应artworkID的painting对象
            String query = "SELECT * FROM paintings WHERE PaintingID=" + "'" + artworkID + "'";
            painting = DAOFactory.getIPaintingDAOInstance().getPaintings(query).get(0);



        // 返回painting给jsp
        request.setAttribute("painting", painting);
        request.getRequestDispatcher("./detail.jsp").forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }
}
