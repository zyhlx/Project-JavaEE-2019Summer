package Servlet;

import dao.IUserDAO;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WorkDeleteServlet", value = "/workDelete")
public class WorkDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paintingID = Integer.parseInt(request.getParameter("artworkID"));

        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (DAOFactory.getIPaintingDAOInstance().delete(paintingID) == 1) {

            String temp = "{\"type\":\"true\",\"msg\":\"删除成功\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        } else {
            String temp = "{\"type\":\"false\",\"msg\":\"删除失败\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }
}
