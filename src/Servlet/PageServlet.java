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
        //调用servicenew出对象
        PageService service = new PageServiceImpl();
        String[] method = req.getParameter("method").split("&");
        if (method.length==0){
            method = new String[1];
            method[0] = "title";
        }
        StringBuilder sql = new StringBuilder();
        String input = req.getParameter("input_text");
        if (input.equals("")){
            sql.append("");
        }else {
            sql.append(" where ").append(method[0]).append(" LIKE '%").append(input).append("%'");
            for (int i =1; i<method.length;i++){
                sql.append(" OR ").append(method[i]).append(" LIKE '%").append(input).append("%' ");
            }
        }
        PageInfo<Painting> news = service.findAlls(Painting.class,"paintings",sql.toString(),
                Integer.parseInt(req.getParameter("pageNum")), 5);
        news.setTotal(service.getTotalCount(sql.toString()));
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

    }
}
