package Servlet;

import bean.Painting;
import dao.factory.DAOFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UploadServlet", value = "/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "D:\\好好学习\\好好学习\\卓越软件\\Project-JavaEE-2019Summer\\web\\博物馆图片资源";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            /**
             * 上传数据及保存文件
             */

                // 检测是否为多媒体上传
                if (!ServletFileUpload.isMultipartContent(request)) {
                    // 如果不是则停止
                    PrintWriter writer = response.getWriter();
                    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
                    writer.flush();
                    return;
                }

                // 配置上传参数
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
                factory.setSizeThreshold(MEMORY_THRESHOLD);
                // 设置临时存储目录
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                ServletFileUpload upload = new ServletFileUpload(factory);

                // 设置最大文件上传值
                upload.setFileSizeMax(MAX_FILE_SIZE);

                // 设置最大请求值 (包含文件和表单数据)
                upload.setSizeMax(MAX_REQUEST_SIZE);

                // 中文处理
                upload.setHeaderEncoding("UTF-8");

                // 构造临时路径来存储上传的文件
                // 这个路径相对当前应用的目录
                String uploadPath = UPLOAD_DIRECTORY;
                System.out.println(uploadPath);

                // 如果目录不存在则创建
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                try {
                    // 解析请求的内容提取文件数据
                    @SuppressWarnings("unchecked")
                    List<FileItem> formItems = upload.parseRequest(request);
                    Painting painting = null;
                    String title = null;
                    String description = null;
                    String gallery = null;
                    String year = null;
                    String artworkID = null;
                    String changed = null;
                    String imageFileName = null;



                    if (formItems != null && formItems.size() > 0) {
                        // 迭代表单数据
                        for (FileItem item : formItems) {

                            // 处理不在表单中的字段
                            if (item.isFormField()) {
                                String fieldName = item.getFieldName();
                                String fieldValue = item.getString();
                                System.out.println("fieldName" + fieldName + "fieldValue" + fieldValue);

                                switch (fieldName){
                                    case "title":
                                        title = fieldValue;
                                        break;
                                    case "description":
                                        description = fieldValue;
                                        break;
                                    case "place":
                                        gallery = fieldValue;
                                    case "year":
                                        year = fieldValue;
                                    case "artworkID":
                                        artworkID = fieldValue;

                                }
                            }
                            else {
                                String fileName = new File(item.getName()).getName();
                                imageFileName = fileName.substring(0,fileName.length()-4);
                                System.out.println("imageFileName:" + imageFileName);

                                String filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                // 在控制台输出文件的上传路径
                                System.out.println(filePath);
                                // 保存文件到硬盘
                                item.write(storeFile);
                                request.setAttribute("message",
                                        "文件上传成功!");
                                //成功，更改数据库

                            }
                        }
                    }

                    // 更新数据库
                    artworkID = "601";
                    String queryForPainting = "SELECT * FROM paintings WHERE PaintingID=" + "'" + artworkID + "'";
                   painting = DAOFactory.getIPaintingDAOInstance().getPaintings(queryForPainting).get(0);

                    painting.setTitle(title);
                    painting.setDescription(description);
                    painting.setGalleryID(DAOFactory.getIGalleryDAOInstance().getGalleryID(gallery));
                    painting.setYearOfWork(Integer.parseInt(year));
                    if (imageFileName != null) {
                        painting.setImageFileName(imageFileName);
                    }
                    DAOFactory.getIPaintingDAOInstance().update(painting);
                } catch (Exception ex) {
                    request.setAttribute("message",
                            "错误信息: " + ex.getMessage());
                }

                // 跳转到 message.jsp
                request.getServletContext().getRequestDispatcher("/message.jsp").forward(
                        request, response);
            }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
    }
}
