<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Xác Thực OTP</title>
<style>
    .auth-container { max-width: 400px; margin: 50px auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); font-family: Arial, sans-serif; text-align: center; }
    .auth-title { color: #333; margin-bottom: 15px; font-weight: bold; }
    .auth-desc { color: #666; margin-bottom: 20px; font-size: 15px; line-height: 1.5; }
    .form-group { margin-bottom: 20px; }
    .form-control-otp { width: 100%; padding: 12px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 20px; text-align: center; letter-spacing: 5px; font-weight: bold; }
    .btn-primary { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; font-size: 16px; }
    .btn-primary:hover { background-color: #0056b3; }
    .msg-error { color: red; margin-bottom: 15px; font-weight: bold; }
</style>
</head>
<body>
    <div class="auth-container">
        <h2 class="auth-title">XÁC THỰC EMAIL</h2>
        
        <p class="auth-desc">Vui lòng nhập mã OTP gồm 6 chữ số đã được gửi đến email của bạn.</p>
        
        <!-- Hiện thông báo lỗi -->
        <p class="msg-error">${error}</p>
        
        <form action="${pageContext.request.contextPath}/verify" method="post">
            <div class="form-group">
                <input type="text" name="otp" class="form-control-otp" required placeholder="NHẬP MÃ OTP" maxlength="6">
            </div>
            
            <button type="submit" class="btn-primary">XÁC NHẬN</button>
        </form>
    </div>
</body>
</html>