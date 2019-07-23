package Servlet;

import Services.FavoursService;
import Services.IFileUtilService;
import Services.PaintingService;
import Services.ServicesImpl.FavoursServiceImpl;
import Services.ServicesImpl.FileUtilServiceImpl;
import Services.ServicesImpl.PaintingServiceImpl;
import bean.Painting;
import dao.IUserDAO;
import dao.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WorkDeleteServlet", value = "/workDelete")
public class WorkDeleteServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "D:\\好好学习\\好好学习\\卓越软件\\Project-JavaEE-2019Summer\\web\\博物馆图片资源\\其他\\";
    private static final String UPLOAD_VIDEO_DIC = "D:\\好好学习\\好好学习\\卓越软件\\Project-JavaEE-2019Summer\\web\\博物馆图片资源\\videos\\";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paintingID = Integer.parseInt(request.getParameter("artworkID"));
        PaintingService paintingService = new PaintingServiceImpl();
        FavoursService favoursService = new FavoursServiceImpl();
        IFileUtilService fileUtilService = new FileUtilServiceImpl();
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Painting painting = paintingService.getOnePainting(paintingID);
        String filePath = UPLOAD_DIRECTORY + painting.getImageFileName();
        String videoPath = UPLOAD_VIDEO_DIC + painting.getVideoPath();
        if (paintingService.delete(paintingID) == 1) {
            String deleteFavour = "DELETE FROM favours WHERE artworkID=" + "'" + paintingID + "'";
            favoursService.delete(deleteFavour);
            fileUtilService.deleteFile(filePath);
            //fileUtilService.deleteFile(videoPath);
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
