import db.DBUtil;
import db.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "FavourServlet",value = "/favour")
public class FavourServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String artworkID = request.getParameter("artworkID");
        // 查找用户ID
        int userID = DBUtil.getUserID(userName);

        // 写入收藏表
        if (userID!=0) {
            String writeIntoFavours = "INSERT INTO favours( userID, artworkID)"
                    +" VALUES(" + "?,?)";
            try{
                Connection conn = new OpenConnection().getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(writeIntoFavours);

            ptmt1.setInt(1,userID);
            ptmt1.setInt(2,Integer.parseInt(artworkID));
            ptmt1.executeUpdate();
            conn.commit();

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
