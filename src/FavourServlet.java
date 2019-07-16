
import bean.Favour;
import dao.factory.DAOFactory;
import dao.impl.UserDAOImpl;
import db.DBUtil;
import db.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "FavourServlet",value = "/favour")
public class FavourServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String artworkID = request.getParameter("artworkID");
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 查找用户ID
        int userID = new UserDAOImpl().getUserID(userName);


        // 避免重复收藏
        String queryForExistedFavours = "SELECT * FROM favours WHERE userID=" + "'" + userID + "'" + " AND" + " artworkID=" + "'" + artworkID + "'";
        if (!DAOFactory.getIFavourDAOInstance().getFavour(queryForExistedFavours).isEmpty()) {
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
        DAOFactory.getIFavourDAOInstance().insert(tempFavour);
                String temp = "{\"type\":\"true\",\"msg\":\"收藏成功\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();




        }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
