<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng Ký Tài Khoản</title>
<style>
    .auth-container { max-width: 400px; margin: 50px auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); font-family: Arial, sans-serif; }
    .auth-title { text-align: center; color: #333; margin-bottom: 20px; font-weight: bold; }
    .form-group { margin-bottom: 15px; text-align: left; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    .form-control { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 15px; }
    .btn-success { width: 100%; padding: 12px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; font-size: 16px; margin-top: 10px; }
    .btn-success:hover { background-color: #218838; }
    .msg-error { color: red; text-align: center; margin-bottom: 10px; font-weight: bold; }
    .auth-links { margin-top: 20px; text-align: center; font-size: 14px; }
    .auth-links a { color: #007bff; text-decoration: none; font-weight: bold; }
    .auth-links a:hover { text-decoration: underline; }
</style>
</head>
<body>
    <div class="auth-container">
        <h2 class="auth-title">ĐĂNG KÝ</h2>
        
        <!-- Hiện thông báo lỗi nếu có -->
        <p class="msg-error">${error}</p>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label>Tên đăng nhập:</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Họ và tên:</label>
                <input type="text" name="fullname" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Email (Nhận OTP):</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Số điện thoại:</label>
                <input type="text" name="phone" class="form-control">
            </div>
            
            <button type="submit" class="btn-success">ĐĂNG KÝ</button>
            
            <div class="auth-links">
                <a href="${pageContext.request.contextPath}/login">Đã có tài khoản? Đăng nhập tại đây</a>
            </div>
        </form>
    </div>
</body>
</html>