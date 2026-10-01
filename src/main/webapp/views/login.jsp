<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng Nhập - Video Store</title>
<style>
    .auth-container { max-width: 400px; margin: 50px auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); font-family: Arial, sans-serif; }
    .auth-title { text-align: center; color: #333; margin-bottom: 20px; font-weight: bold; }
    .form-group { margin-bottom: 15px; text-align: left; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    .form-control { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 15px; }
    .btn-primary { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; font-size: 16px; margin-top: 10px; }
    .btn-primary:hover { background-color: #0056b3; }
    .msg-error { color: red; text-align: center; margin-bottom: 10px; font-weight: bold; }
    .msg-success { color: green; text-align: center; margin-bottom: 10px; font-weight: bold; }
    .auth-links { margin-top: 20px; text-align: center; font-size: 14px; }
    .auth-links a { color: #007bff; text-decoration: none; font-weight: bold; }
    .auth-links a:hover { text-decoration: underline; }
</style>
</head>
<body>
    <div class="auth-container">
        <h2 class="auth-title">ĐĂNG NHẬP</h2>
        
        <!-- Hiện thông báo lỗi hoặc thành công -->
        <p class="msg-error">${error}</p>
        <p class="msg-success">${message}</p>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Tên đăng nhập:</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            
            <button type="submit" class="btn-primary">ĐĂNG NHẬP</button>
            
            <div class="auth-links">
                <a href="${pageContext.request.contextPath}/register">Chưa có tài khoản? Đăng ký ngay</a>
            </div>
        </form>
    </div>
</body>
</html>