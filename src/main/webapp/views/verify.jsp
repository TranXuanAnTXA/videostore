<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác Thực OTP</title>
</head>

<body>

<div
    style="max-width:400px; margin:0 auto; background:#fff; padding:20px;
    border-radius:8px; box-shadow:0 0 10px rgba(0,0,0,.1); text-align:center;">

    <h2 style="color:#333;">XÁC THỰC EMAIL</h2>

    <p>Vui lòng nhập mã OTP gồm 6 chữ số đã được gửi đến email của bạn.</p>

    <!-- Hiện thông báo lỗi -->
    <p style="color:red">${error}</p>

    <form action="${pageContext.request.contextPath}/verify" method="post">

        <div style="margin-bottom:15px;">
            <input type="text"
                   name="otp"
                   required
                   placeholder="Nhập mã OTP..."
                   style="width:100%; padding:10px; font-size:18px;
                   text-align:center; letter-spacing:5px;">
        </div>

        <button type="submit"
                style="width:100%; padding:10px;
                background:#007bff; color:white;
                border:none; cursor:pointer; font-weight:bold;">
            XÁC NHẬN
        </button>

    </form>

</div>

</body>
</html>