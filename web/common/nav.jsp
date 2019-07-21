<%--
  Created by IntelliJ IDEA.
  bean.User: DELL
  Date: 2019/7/14
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar nav-pills navbar-light fixed-top" id="fixed-nar">

        <div class="col-sm-4 navbar-header row" id="nav-header-form">

            <button class="btn mobile-nav-taggle btn-collase" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a class="navbar-brand nav-item logo" href="#">Art Museum</a>
        </div>
        <div class="col-sm-7 row justify-content-end">
            <ul class="nav nav-pills list-inline-item col-sm-10 justify-content-end " id="move">
                <li class="nav-item list-inline-item">
                    <a href="index.jsp" class="nav-link">首页</a>
                </li>
                <% if (session.getAttribute("user") != null) {%>
                <li class="nav-item list-inline-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user-circle"></i> <%= session.getAttribute("user")%>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="./friendsDisplay">好友列表</a>
                        <a class="dropdown-item" href="#">信箱</a>
                        <a href="./favourDisplay" class="dropdown-item"><i
                                class="fa fa-user-plus"></i>收藏夹</a>
                        <a href="#" class="dropdown-item"><i class="fa fa-sign-out"></i>登出</a>
                    </div>
                </li>
                <%--<li class="nav-item list-inline-item"><a href="../favours.jsp" class="nav-link"><i--%>
                <%--class="fa fa-user-plus"></i>收藏夹</a>--%>
                <%--</li>--%>
                <%} else {%>
                <li class="nav-item list-inline-item">
                    <a href="#" class="nav-link" data-toggle="modal" data-target="#Register">Register</a>
                </li>
                <li class="nav-item list-inline-item">
                    <a href="#" class="nav-link" data-toggle="modal" data-target="#Login">Login</a>
                </li>
                <%
                    }%>

                <li class="nav-item list-inline-item">
                    <a href="./userManagement?function=2" class="nav-link" data-gacategory="navigation" data-gaaction="clicked"
                       data-galabel="explore">
                        <svg width="24" height="24" viewBox="0 0 24 24" class="doawgc">
                            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z"></path>
                            <path d="M6.5 17.5l7.51-3.49L17.5 6.5 9.99 9.99 6.5 17.5zm5.5-6.6c.61 0 1.1.49 1.1 1.1s-.49 1.1-1.1 1.1-1.1-.49-1.1-1.1.49-1.1 1.1-1.1z"></path>
                            <path fill="none" d="M0 0h24v24H0V0z"></path>
                        </svg>
                    </a>
                </li>
                <li>
                    <a href="search.jsp" class="nav-link">
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
                <a href="index.jsp" class="nav-link slide-nav-active" data-gacategory="navigation" data-gaaction="clicked"
                   data-galabel="home">
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
                <a class="nav-link" href="detail.jsp" data-gacategory="navigation" data-gaaction="clicked"
                   data-galabel="detail">
                    <svg width="48px" height="48px" viewBox="0 0 48 48">
                        <path d="M42 38V10c0-2.21-1.79-4-4-4H10c-2.21 0-4 1.79-4 4v28c0 2.21 1.79 4 4 4h28c2.21 0 4-1.79 4-4zM17 27l5 6.01L29 24l9 12H10l7-9z"></path>
                    </svg>
                    详情</a>
            </li>
            <li class="nav-item p-2">
                <a class="nav-link" href="search.jsp" data-gacategory="navigation" data-gaaction="clicked" data-galabel="search">
                    <svg width="24px" height="24px" viewBox="0 0 48 48">
                        <path d="M31 28h-1.59l-.55-.55C30.82 25.18 32 22.23 32 19c0-7.18-5.82-13-13-13S6 11.82 6 19s5.82 13 13 13c3.23 0 6.18-1.18 8.45-3.13l.55.55V31l10 9.98L40.98 38 31 28zm-12 0c-4.97 0-9-4.03-9-9s4.03-9 9-9 9 4.03 9 9-4.03 9-9 9z"></path>
                    </svg>
                    搜索</a>
            </li>
            <% if (session.getAttribute("user") == null) {%>
            <li class="nav-item p-2">
                <a class="nav-link" href="#" data-gacategory="navigation" data-gaaction="clicked" data-galabel="profile"
                   data-toggle="modal" data-target="#Login">
                    <svg width="24" height="24" viewBox="0 0 24 24">
                        <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zM7.07 18.28c.43-.9 3.05-1.78 4.93-1.78s4.51.88 4.93 1.78C15.57 19.36 13.86 20 12 20s-3.57-.64-4.93-1.72zm11.29-1.45c-1.43-1.74-4.9-2.33-6.36-2.33s-4.93.59-6.36 2.33A7.95 7.95 0 0 1 4 12c0-4.41 3.59-8 8-8s8 3.59 8 8c0 1.82-.62 3.49-1.64 4.83z"></path>
                        <path d="M12 6c-1.94 0-3.5 1.56-3.5 3.5S10.06 13 12 13s3.5-1.56 3.5-3.5S13.94 6 12 6zm0 5c-.83 0-1.5-.67-1.5-1.5S11.17 8 12 8s1.5.67 1.5 1.5S12.83 11 12 11z"></path>
                        <path fill="none" d="M0 0h24v24H0V0z"></path>
                    </svg>
                    用户</a>
            </li>
            <%} else {%>
            <li class="nav-item p-2">
                <a class="nav-link" href="#" data-gacategory="navigation" data-gaaction="clicked"
                   data-galabel="profile">
                    <svg width="24" height="24" viewBox="0 0 24 24">
                        <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zM7.07 18.28c.43-.9 3.05-1.78 4.93-1.78s4.51.88 4.93 1.78C15.57 19.36 13.86 20 12 20s-3.57-.64-4.93-1.72zm11.29-1.45c-1.43-1.74-4.9-2.33-6.36-2.33s-4.93.59-6.36 2.33A7.95 7.95 0 0 1 4 12c0-4.41 3.59-8 8-8s8 3.59 8 8c0 1.82-.62 3.49-1.64 4.83z"></path>
                        <path d="M12 6c-1.94 0-3.5 1.56-3.5 3.5S10.06 13 12 13s3.5-1.56 3.5-3.5S13.94 6 12 6zm0 5c-.83 0-1.5-.67-1.5-1.5S11.17 8 12 8s1.5.67 1.5 1.5S12.83 11 12 11z"></path>
                        <path fill="none" d="M0 0h24v24H0V0z"></path>
                    </svg>
                    <%= session.getAttribute("user")%>
                </a>
            </li>


            <%}%>
        </ul>
    </nav>
</header>


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
                        <input type="text" class="form-control" id="username-register" name="username-register"
                               onblur="ontimecheckname()" placeholder="8-16位字母数字">
                    </div>
                    <div class="form-group">
                        <label for="password-register" id="password-register-label">密码:</label>
                        <input type="password" class="form-control" name="password-register" id="password-register"
                               placeholder="8-16位字母数字" onblur="ontimecheckpassword()">
                    </div>
                    <div class="form-group">
                        <label for="pwdconfirm-register" id="pwdconfirm-register-label">确认密码：</label>
                        <input type="password" class="form-control" name="pwdconfirm-register" id="pwdconfirm-register"
                               placeholder="Enter password" onblur="ontimecheckpassword2()">
                    </div>
                    <div class="form-group">
                        <label for="email" id="email-label">邮箱:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email"
                               onblur="ontimecheckemail()">
                    </div>
                    <div class="form-group">
                        <label for="address" id="address-label">地址:</label>
                        <input type="text" class="form-control" id="address" name="address" placeholder="Enter address"
                               onblur="ontimecheckaddress()">
                    </div>
                    <div class="form-group">
                        <label for="tel" id="tel-label">电话:</label>
                        <input type="tel" class="form-control" id="tel" name="tel" placeholder="Enter tel"
                               onblur="ontimechecktel()">
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
                <form action="login" name="form_login" method="post" id="form_login">
                    <div class="form-group">
                        <label for="username" id="username-label">用户名:</label>
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="password" id="password-label">密码:</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter password">
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



