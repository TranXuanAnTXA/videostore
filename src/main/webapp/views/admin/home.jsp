<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>Trang Chủ Admin</title>

<!-- Bổ sung text-align: center để căn giữa toàn bộ chữ -->
<div style="padding: 60px 20px; font-family: Arial, sans-serif; text-align: center;">
    
    <h1 style="color: #007bff; font-size: 36px; margin-bottom: 15px; text-transform: uppercase;">BẢNG ĐIỀU KHIỂN QUẢN TRỊ</h1>
    <p style="font-size: 18px; color: #555;">Chào mừng Admin <b style="color: #333;">${sessionScope.user.fullname}</b> đã quay trở lại!</p>
    
    <!-- Đổi justify-content thành center để căn giữa các nút -->
    <div style="display: flex; gap: 40px; justify-content: center; margin-top: 60px;">
        
        <!-- Nút Quản lý Video được làm to ra, thêm bóng đổ (box-shadow) để nổi bật -->
        <a href="${pageContext.request.contextPath}/admin/video" 
           style="display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 30px; background: #28a745; color: white; text-decoration: none; border-radius: 12px; font-size: 22px; font-weight: bold; box-shadow: 0 6px 12px rgba(0,0,0,0.15); width: 280px; min-height: 120px;">
            <span style="font-size: 40px; margin-bottom: 10px;">🎬</span>
            Quản lý Video
        </a>
        
        <!-- Nút Quản lý Người dùng -->
        <a href="${pageContext.request.contextPath}/admin/user" 
           style="display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 30px; background: #17a2b8; color: white; text-decoration: none; border-radius: 12px; font-size: 22px; font-weight: bold; box-shadow: 0 6px 12px rgba(0,0,0,0.15); width: 280px; min-height: 120px;">
            <span style="font-size: 40px; margin-bottom: 10px;">👥</span>
            Quản lý Người dùng
        </a>
        
        <!-- Đã xóa hoàn toàn nút Đăng xuất màu đỏ ở đây -->
    </div>
    
</div>