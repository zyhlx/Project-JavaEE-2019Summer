package Servlet;

import Services.FriendService;
import Services.LetterService;
import Services.ServicesImpl.FriendServiceImpl;
import Services.ServicesImpl.LetterServiceImpl;
import bean.FriendRelation;
import bean.Letter;
import dao.factory.DAOFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LetterServlet",value="/letter")
public class LetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=UTF-8");
        int userID = Integer.parseInt(request.getParameter("userID"));
        String receive = request.getParameter("receiver");
        String content = request.getParameter("content");
        LetterService letterService = new LetterServiceImpl();
        if (letterService.sendLetter(userID,receive,content)==-1){
            String temp = "{\"msg\":\"查无此人\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }else {
            String temp = "{\"msg\":\"发送成功\"}";
            PrintWriter out = response.getWriter();
            out.println(temp);
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        String isOne = request.getParameter("isOne");
        LetterService letterService = new LetterServiceImpl();
        FriendService friendService = new FriendServiceImpl();
        if(isOne!=null){
            int letterID = Integer.parseInt(request.getParameter("letterID"));
            if (request.getParameter("isW")==null){
                letterService.read(letterID);
            }
            Letter letter = letterService.getLetter(letterID);
            JSONObject gson = new JSONObject();
            gson.put("letter",letter);
            gson.put("sendTime",letter.getTimeReleased().toString());
            String json = gson.toString();
            // 获取输出流对象
            PrintWriter writer = response.getWriter();
            writer.print(json); // 返回数据给前台
            writer.close();
        }else {
            int userID = Integer.parseInt(request.getParameter("userID"));
            List<Letter> receiveLetters = letterService.getReceiveLetter(userID);
            List<Letter> sendLetters = letterService.getSendLetters(userID);
            List<FriendRelation> friendRequests = friendService.getFriendRequests(userID);
            List<FriendRelation> friendLetters = friendService.getAllFriends(userID);
            request.setAttribute("sendLetters", sendLetters);
            request.setAttribute("receiveLetters",receiveLetters);
            request.setAttribute("friendRequests", friendRequests);
            request.setAttribute("friendLetters", friendLetters);
            request.getRequestDispatcher("./letter.jsp").forward(request, response);
        }


    }
}
