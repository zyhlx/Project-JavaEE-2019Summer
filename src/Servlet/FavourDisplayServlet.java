package Servlet;

import Services.FavoursService;
import Services.ServicesImpl.FavoursServiceImpl;
import bean.Favour;
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

@WebServlet(name = "FavourDisplayServlet", value = "/favourDisplay")
public class FavourDisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int userID = (Integer) session.getAttribute("userID");

        FavoursService favoursService = new FavoursServiceImpl();
        List<Favour> favours =favoursService.getFavourByUserID(userID);
// 返回painting给jsp
        request.setAttribute("favours", favours);
        request.getRequestDispatcher("./WEB-INF/favours.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
