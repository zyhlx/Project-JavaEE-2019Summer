import db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession(true);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (DBUtil.login(name, pwd)) {
            session.setAttribute("user", name);
            //session.setAttribute("id", "very good!");
            String temp = "{\"type\":\"true\",\"msg\":\"登陆成功\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }else {
            String temp = "{\"type\":\"false\",\"msg\":\"用户名密码错误\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}