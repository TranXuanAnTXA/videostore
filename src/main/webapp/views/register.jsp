<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Ký Tài Khoản</title>
</head>
<body>

<div style="max-width:400px; margin:0 auto; background:#fff;
            padding:20px; border-radius:8px;
            box-shadow:0 0 10px rgba(0,0,0,0.1);">

    <h2 style="text-align:center; color:#333;">ĐĂNG KÝ</h2>

    <!-- Hiện thông báo lỗi nếu có -->
    <p style="color:red; text-align:center;">
        ${error}
    </p>

    <form action="${pageContext.request.contextPath}/register"
          method="post">

        <div style="margin-bottom:15px;">
            <label>Tên đăng nhập:</label><br>
            <input type="text"
                   name="username"
                   required
                   style="width:100%; padding:8px;">
        </div>

        <div style="margin-bottom:15px;">
            <label>Mật khẩu:</label><br>
            <input type="password"
                   name="password"
                   required
                   style="width:100%; padding:8px;">
        </div>

        <div style="margin-bottom:15px;">
            <label>Họ và tên:</label><br>
            <input type="text"
                   name="fullname"
                   required
                   style="width:100%; padding:8px;">
        </div>

        <div style="margin-bottom:15px;">
            <label>Email (Nhận OTP):</label><br>
            <input type="email"
                   name="email"
                   required
                   style="width:100%; padding:8px;">
        </div>

        <div style="margin-bottom:15px;">
            <label>Số điện thoại:</label><br>
            <input type="text"
                   name="phone"
                   style="width:100%; padding:8px;">
        </div>

        <button type="submit"
                style="width:100%;
                       padding:10px;
                       background:#28a745;
                       color:white;
                       border:none;
                       cursor:pointer;
                       font-weight:bold;">
            ĐĂNG KÝ
        </button>

    </form>

</div>

</body>
</html>