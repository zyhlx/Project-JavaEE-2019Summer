package Servlet;

import Services.PageService;
import Services.ServicesImpl.PageServiceImpl;
import bean.PageInfo;
import bean.Painting;
import net.sf.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet.PageServlet",value = "/page")

public class PageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置响应编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //调用servicenew出对象
        PageService service = new PageServiceImpl();
        String input_info = req.getParameter("input_text");
        String method_info = req.getParameter("method");
        PageInfo<Painting> news = service.findAlls(Painting.class,input_info,method_info,
                Integer.parseInt(req.getParameter("pageNum")), 5);
        news.setTotal(service.getTotalCount(method_info,input_info));
        System.out.println("pageNum=====>" + req.getParameter("pageNum"));      news.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
        System.out.println("总记录数===》" + news.getTotal());
        JSONObject gson = new JSONObject();
        gson.put("page",news);
        String json = gson.toString();
        System.out.println(json);
        // 获取输出流对象
        PrintWriter writer = resp.getWriter();
        writer.print(json); // 返回数据给前台
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
    }
}
