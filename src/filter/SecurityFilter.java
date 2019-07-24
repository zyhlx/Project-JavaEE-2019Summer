package filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecurityFilter implements Filter {

    private Log log = LogFactory.getLog(SecurityFilter.class);
    private List<String> whitelist = new ArrayList<String>();
    private List<String> regexlist = new ArrayList<String>();
    private static final String _JSON_CONTENT = "application/json; charset=UTF-8";
    private static final String _HTML_CONTENT = "text/html; charset=UTF-8";
    private static final String _403_JSON = "{'code': '403', 'msg': '访问被拒绝，客户端未授权！'}";
    private static final String _403_HTML = "<html><body><div style='text-align:center'><h1 style='margin-top: 10px;'>403 Forbidden!</h1><hr><span>@lichmama</span></div></body></html>";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
            throws IOException, ServletException {
        // 1、获得当前请求访问资源路径
        HttpServletRequest myReq = (HttpServletRequest) servletrequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletresponse;
        myReq.setCharacterEncoding("utf-8");
        String path = myReq.getRequestURI().substring(
                myReq.getContextPath().length());
        path = URLDecoder.decode(path, "utf-8");
        System.out.println(path);
        // 2、如果路径 以/public/ 开始 ----- 游客就可以访问 无需登陆
        if (isPublic(path)) {
            filterchain.doFilter(myReq, servletresponse);
            return;
        } else {
            // 需要 用户身份 或者 管理员 --- 需要登陆 ----- 判断是否登陆
            String userType = (String) myReq.getSession().getAttribute(
                    "userType");
            System.out.println(userType);
            if (userType == null) {
                // 未登陆--- 没有权限 --- 跳转到登陆页面
                servletrequest.setAttribute("msg", "您还没有登陆！");
                httpServletResponse.sendRedirect("./index.jsp");
                return;
            } else {
                // 已经登陆 --- 用户有身份
                if (isNormal(path)) { // user 身份

                    filterchain.doFilter(myReq, servletresponse);
                    return;
                }

                if (isAdmin(path)) { // 管理员身份
                    // 需要管理员身份
                    if (userType.equals("admin")) {
                        // 权限满足
                        filterchain.doFilter(myReq, servletresponse);
                        return;
                    } else {
                        throw new RuntimeException("对不起您的权限不足！");
                    }
                }
            }
        }

        filterchain.doFilter(myReq, servletresponse);
    }


    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
//        String allowedIP = filterconfig.getInitParameter("allowedIP");
//        if (allowedIP != null && allowedIP.length() > 0) {
//            for (String item : allowedIP.split(",\\s*")) {
//                // 支持通配符*
//                if (item.contains("*")) {
//                    String regex = item.replace(".", "\\.").replace("*", "\\d{1,3}");
//                    regexlist.add(regex);
//                } else {
//                    whitelist.add(item);
//                }
//            }
//        }
    }

    private boolean isPublic(String path) {
        List<String> pubLicPath = new ArrayList<>();

        pubLicPath.add("/detailDisplay");
        pubLicPath.add("/index");
        pubLicPath.add("/login");
        pubLicPath.add("/page");
        pubLicPath.add("/register");
        pubLicPath.add("/search");
        pubLicPath.add("/common");
        pubLicPath.add("/css");
        pubLicPath.add("/js");
        pubLicPath.add("/博物馆图片资源");
        pubLicPath.add("/images");
        pubLicPath.add("/paintingDetail.display");
        pubLicPath.add("/userDetail.display");
        pubLicPath.add("/painting.search");

        for (String testPath : pubLicPath) {
            if (path.startsWith(testPath))
                return true;
        }

        return false;
    }

    private boolean isNormal(String path) {
        List<String> normalPath = new ArrayList<>();


        normalPath.add("/favour");
        normalPath.add("/friend");
        normalPath.add("letter");
        normalPath.add("/userDetail");

        for (String testPath : normalPath) {
            if (path.startsWith(testPath))
                return true;
        }

        return false;
    }

    private boolean isAdmin(String path) {
        List<String> adminPath = new ArrayList<>();
        adminPath.add("/upload");
        adminPath.add("/userManagement");
        adminPath.add("/userDisplay");
        adminPath.add("/work");

        for (String testPath : adminPath) {
            if (path.startsWith(testPath))
                return true;
        }

        return false;
    }

    /**
     * 判断当前请求是否来自可信任的地址
     *
     * @param request
     * @return
     */
    private boolean isSecurityRequest(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        for (String item : whitelist) {
            if (ip.equals(item))
                return true;
        }
        for (String item : regexlist) {
            if (ip.matches(item))
                return true;
        }
        return false;
    }

    /**
     * 判断请求是否是AJAX请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && header.length() > 0) {
            if ("XMLHttpRequest".equalsIgnoreCase(header))
                return true;
        }
        return false;
    }

//    class MyRequest extends HttpServletRequestWrapper {
//        ServletRequest r;
//        private HttpServletRequest req;
//
//        MyRequest(HttpServletRequest req) {
//            super(req);
//            this.req = req;
//        }
//
//
//
//        @Override
//        public String getParameter(String name) {
//            Map<String, String[]> map = getParameterMap();
//            if (map.get(name) == null) {
//                return "";
//            }
//            return map.get(name)[0];
//
//        }
//
//        @Override
//        public String[] getParameterValues(String name) {
//            Map<String, String[]> map = getParameterMap();
//            return map.get(name);
//        }
//
//        boolean flag = true;
//
//        @Override
//        public Map<String, String[]> getParameterMap() {
//            Map<String, String[]> map = req.getParameterMap();
//            if (flag) {
//                for (Map.Entry<String, String[]> en : map.entrySet()) {
//                    String[] arr = en.getValue();
//
//                    System.out.println(en.getValue());
//                    System.out.println(arr);
//
//                    for (int i = 0; i < arr.length; i++) {
//                        //数组中的每个值都要再编码再解码处理
//                        try {
//                            arr[i] = new String(arr[i].getBytes("ISO8859-1"), "utf-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                flag = false;
//            }
//            return map;
//        }
//
//
//    }
}