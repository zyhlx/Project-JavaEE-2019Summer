<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/7/14
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar nav-pills navbar-light navbar-fix" id="fixed-nar">

    <div class="col-sm-4 navbar-header row" id="nav-header-form">

        <button class="btn mobile-nav-taggle btn-collase" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>

        <a class="navbar-brand nav-item logo" href="#">Art Museum</a>
    </div>
    <div class="col-sm-7 row justify-content-end">
        <ul class="nav nav-pills list-inline-item col-sm-10 justify-content-end " id="move">
            <li class="nav-item list-inline-item">
                <a href="#" class="nav-link">Home</a>
            </li>
            <% if(session.getAttribute("user")!=null){%>

            <li class="nav-item list-inline-item"><a href="#" class="nav-link"><i class="fa fa-sign-out"></i>登出</a></li>
            <li class="nav-item list-inline-item"><a href="#" class="nav-link"><i class="fa fa-user-circle"></i><%= session.getAttribute("user")%></a></li>
            <li class="nav-item list-inline-item"><a href="favours.jsp" class="nav-link"><i class="fa fa-user-plus"></i>收藏夹</a></li>
            <%}else {%>
            <li class="nav-item list-inline-item">
                <a href="#" class="nav-link" data-toggle="modal" data-target="#Register">Register</a>
            </li>
            <li class="nav-item list-inline-item">
                <a href="#" class="nav-link" data-toggle="modal" data-target="#Login">Login</a>
            </li>
              <%
            }%>

            <li>
                <a href="#" class="nav-link">
                    <svg width="24px" height="24px" viewBox="0 0 48 48">
                        <path
                                d="M31 28h-1.59l-.55-.55C30.82 25.18 32 22.23 32 19c0-7.18-5.82-13-13-13S6 11.82 6 19s5.82 13 13 13c3.23 0 6.18-1.18 8.45-3.13l.55.55V31l10 9.98L40.98 38 31 28zm-12 0c-4.97 0-9-4.03-9-9s4.03-9 9-9 9 4.03 9 9-4.03 9-9 9z"></path>
                    </svg>
                </a>
            </li>
        </ul>

    </div>

    <div class="col-sm-1 row" id="user-button-div">

        <button class="btn" type="button">
            <img src="images/nav/user.PNG">
        </button>

    </div>


</nav>

<nav class="navbar nav-pills navbar-light flex-column" id="mobile-menu" aria-hidden="true">
    <ul class="navbar-nav">
        <li class="nav-item p-2">
            <a href="/" class="nav-link slide-nav-active" data-gacategory="navigation" data-gaaction="clicked" data-galabel="home">
                <svg x="0px" y="0px" viewBox="0 0 24 24">
                    <g>
                        <path d="M12,3L4,9v12h16V9L12,3z M18,19h-3v-6H9v6H6v-9l6-4.5l6,4.5V19z"></path>
                    </g>
                    <path fill="none" d="M0,0h24v24H0V0z"></path>
                </svg>
                Home
            </a>
        </li>
        <li class="nav-item p-2">
            <a class="nav-link" href="detail.jsp">详情</a>
        </li>
        <li class="nav-item p-2">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item p-2">
            <a class="nav-link" href="#">Link</a>
        </li>
    </ul>
</nav>





<!-- 模态框 -->
<div class="modal fade" id="Register">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">注册</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="register" method="post" name="form_register" id="form_register">
                    <div class="form-group">
                        <label for="username-register" id="username-register-label">用户名:</label>
                        <input type="text" class="form-control" id="username-register" name="username-register" onblur="ontimecheckname()" placeholder="8-16位字母数字">
                    </div>
                    <div class="form-group">
                        <label for="password-register" id="password-register-label">密码:</label>
                        <input type="password" class="form-control" name="password-register" id="password-register" placeholder="8-16位字母数字" onblur="ontimecheckpassword()">
                    </div>
                    <div class="form-group">
                        <label for="pwdconfirm-register" id="pwdconfirm-register-label">确认密码：</label>
                        <input type="password" class="form-control" name="pwdconfirm-register" id="pwdconfirm-register" placeholder="Enter password" onblur="ontimecheckpassword2()">
                    </div>
                    <div class="form-group">
                        <label for="email" id="email-label">邮箱:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" onblur="ontimecheckemail()">
                    </div>
                    <div class="form-group">
                        <label for="address" id="address-label">地址:</label>
                        <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" onblur="ontimecheckaddress()">
                    </div>
                    <div class="form-group">
                        <label for="tel" id="tel-label">电话:</label>
                        <input type="tel" class="form-control" id="tel" name="tel" placeholder="Enter tel" onblur="ontimechecktel()">
                    </div>
                    <div class="form-group">
                        <label for="yanzhengma" id="yanzhengma_label">验证码：</label>
                        <input type="text" class="form-control" name="yanzhengma" id="yanzhengma">
                        <div id="check" onclick="change()">
                            <a href="#">点击刷新验证码</a>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" onclick="registe()">Submit</button>
                    </div>
                </form>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="Login">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">登录</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="login"  name="form_login" method="post" id="form_login">
                    <div class="form-group">
                        <label for="username" id="username-label">用户名:</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="password" id="password-label">密码:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label for="yanzhengma">验证码：</label>--%>
                        <%--<input type="text" class="form-control" name="yanzhengma" id="yanzhengma">--%>
                        <%--<div id="check" onclick="change()">--%>
                            <%--<a href="#">点击刷新验证码</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" onclick="login()">Submit</button>
                    </div>
                </form>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>



