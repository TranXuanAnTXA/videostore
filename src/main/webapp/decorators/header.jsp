<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<style>
.header{
    background: linear-gradient(90deg,#0d6efd,#6610f2);
    color:white;
    padding:15px 40px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    box-shadow:0 3px 10px rgba(0,0,0,.2);
}

.logo{
    font-size:28px;
    font-weight:bold;
    text-decoration:none;
    color:white;
    letter-spacing:1px;
}

.menu{
    display:flex;
    align-items:center;
    gap:15px;
}

.menu a{
    color:white;
    text-decoration:none;
    padding:8px 16px;
    border-radius:6px;
    transition:.3s;
    font-weight:500;
}

.menu a:hover{
    background:rgba(255,255,255,.18);
}

.user{
    background:rgba(255,255,255,.18);
    padding:8px 14px;
    border-radius:20px;
}

.btn-login{
    background:#198754;
}

.btn-login:hover{
    background:#157347;
}

.btn-register{
    background:#ffc107;
    color:#000 !important;
    font-weight:bold;
}

.btn-register:hover{
    background:#ffca2c;
}

.btn-admin{
    background:#dc3545;
}

.btn-admin:hover{
    background:#bb2d3b;
}

.btn-video{
    background:#fd7e14;
}

.btn-video:hover{
    background:#e36d08;
}

.btn-category{
    background:#20c997;
}

.btn-category:hover{
    background:#16a085;
}

.btn-home{
    background:#0dcaf0;
}

.btn-home:hover{
    background:#0aa6c0;
}

.btn-favorite{
    background:#6f42c1;
}

.btn-favorite:hover{
    background:#59359a;
}

.btn-logout{
    background:#212529;
}

.btn-logout:hover{
    background:#000;
}
</style>

<div class="header">

    <a class="logo"
       href="${pageContext.request.contextPath}/home">
        🎬 VIDEO STORE
    </a>

    <div class="menu">

        <c:choose>

  

            <c:when test="${empty sessionScope.user}">

                <a class="btn-login"
                   href="${pageContext.request.contextPath}/login">
                    🔑 Đăng nhập
                </a>

                <a class="btn-register"
                   href="${pageContext.request.contextPath}/register">
                    📝 Đăng ký
                </a>

            </c:when>

      

            <c:when test="${sessionScope.user.admin}">

                <span class="user">
                    👑 Xin chào,
                    <b>${sessionScope.user.fullname}</b>
                </span>

                <a class="btn-admin"
                   href="${pageContext.request.contextPath}/admin/home">
                    Dashboard
                </a>

                <a class="btn-video"
                   href="${pageContext.request.contextPath}/admin/video">
                    Video
                </a>

                <a class="btn-category"
                   href="${pageContext.request.contextPath}/admin/category">
                    Category
                </a>

                <a class="btn-logout"
                   href="${pageContext.request.contextPath}/logout">
                    Đăng xuất
                </a>

            </c:when>

      

            <c:otherwise>

                <span class="user">
                    👋 Xin chào,
                    <b>${sessionScope.user.fullname}</b>
                </span>

                <a class="btn-home"
                   href="${pageContext.request.contextPath}/home">
                    Trang chủ
                </a>

                <a class="btn-favorite"
                   href="${pageContext.request.contextPath}/favorite">
                    ❤️ Yêu thích
                </a>

                <a class="btn-logout"
                   href="${pageContext.request.contextPath}/logout">
                    Đăng xuất
                </a>

            </c:otherwise>

        </c:choose>

    </div>

</div>