package Servlet;

import bean.Painting;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkManageServlet", value = "/workManagement")
@MultipartConfig
public class WorkManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String function = request.getParameter("title");

        System.out.println(function);

        switch (function) {
            case "0":
                changeInfo(request, response);
                break;

            default:
                break;

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

    private void changeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 从表单获取数据

        String title = request.getParameter("title");

        String description = request.getParameter("description");
        String gallery = request.getParameter("place");
        String year = request.getParameter("year");
        String artworkID = request.getParameter("artworkID");
        String changed = request.getParameter("chenged");
        String queryForPainting = "SELECT * FROM paintings WHERE PaintingID=" + "'" + artworkID + "'";

// 创建painting对象
        Painting painting = DAOFactory.getIPaintingDAOInstance().getPaintings(queryForPainting).get(0);

        painting.setTitle(title);
        painting.setDescription(description);
        painting.setGalleryID(DAOFactory.getIGalleryDAOInstance().getGalleryID(gallery));
        painting.setYearOfWork(Integer.parseInt(year));


        // 文件处理

        if (changed.equals("1")) {
            request.setAttribute("painting", painting);
            request.getRequestDispatcher("./upload").forward(request, response);
        }


    }

    private void deleteWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void addWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}