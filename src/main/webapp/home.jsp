<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Trang Chủ - Video Store</title>

<div style="text-align: center; padding: 40px 20px;">
    <h1 style="color: #333; margin-bottom: 10px;">CHÀO MỪNG ĐẾN VỚI VIDEO STORE</h1>
    
    <!-- Nút hành động dành cho người chưa đăng nhập -->
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/login" style="display: inline-block; padding: 12px 25px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; margin-right: 10px;">Đăng Nhập</a>
        <a href="${pageContext.request.contextPath}/register" style="display: inline-block; padding: 12px 25px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">Đăng Ký Ngay</a>
    </c:if>

    <!-- Câu chào dành cho người đã đăng nhập -->
    <c:if test="${not empty sessionScope.user}">
        <p style="color: green; font-size: 20px; font-weight: bold;">
            Xin chào, ${sessionScope.user.fullname}! Chúc bạn xem video vui vẻ.
        </p>
    </c:if>
</div>

<div style="display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; padding: 20px;">
    
    <!-- Duyệt qua danh sách Video lấy từ HomeController -->
    <c:forEach var="v" items="${videos}">
        <div style="width: 300px; border: 1px solid #ddd; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 8px rgba(0,0,0,0.1); background: #fff;">
            
            <div style="width: 100%; height: 180px; background: #000;">
                <!-- Link hình ảnh lấy từ Database -->
                <img src="${v.poster}" alt="${v.title}" style="width: 100%; height: 100%; object-fit: cover;">
            </div>
            
            <div style="padding: 15px; text-align: left;">
                <h3 style="margin: 0 0 10px 0; font-size: 18px; color: #333;">${v.title}</h3>
                <p style="color: #777; font-size: 14px; margin: 0;">Lượt xem: ${v.views}</p>
                
                <div style="margin-top: 15px; text-align: center;">
                    <a href="${pageContext.request.contextPath}/video/detail?id=${v.id}" 
                       style="display: inline-block; padding: 8px 15px; background: #28a745; color: white; text-decoration: none; border-radius: 4px;">Xem ngay</a>
                </div>
            </div>
            
        </div>
    </c:forEach>

    <!-- Thông báo nếu Database chưa có video nào -->
    <c:if test="${empty videos}">
        <h3 style="color: #888; width: 100%; text-align: center;">Hiện tại chưa có video nào!</h3>
    </c:if>

</div>