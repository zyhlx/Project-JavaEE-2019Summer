package Servlet;

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

@WebServlet(name = "Servlet.FavourServlet",value = "/favour")
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
        if (DBUtil.isExisted(queryForExistedFavours)) {
            String temp = "{\"type\":\"false\",\"msg\":\"您已经收藏过了哦\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
            return;
        }

        // 写入收藏表
        if (userID!=0) {
            String writeIntoFavours = "INSERT INTO favours(userID, artworkID)"

                    +" VALUES(" + "?,?)";
            try{
                Connection conn = new OpenConnection().getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(writeIntoFavours);
            ptmt1.setInt(1,userID);
            ptmt1.setInt(2,Integer.parseInt(artworkID));
            ptmt1.executeUpdate();
            conn.commit();
                String temp = "{\"type\":\"true\",\"msg\":\"收藏成功\"}";
                PrintWriter out = response.getWriter();
                out.println(temp);
                out.flush();
                out.close();

        }
            catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
