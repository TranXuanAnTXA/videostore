<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Đăng Nhập</title>

<div
	style="max-width: 400px; margin: 0 auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
	<h2 style="text-align: center; color: #333;">ĐĂNG NHẬP</h2>

	<!-- Hiện thông báo lỗi hoặc thành công -->
	<p style="color: red; text-align: center;">${error}</p>
	<p style="color: green; text-align: center;">${message}</p>

	<form action="${pageContext.request.contextPath}/login" method="post">
		<div style="margin-bottom: 15px;">
			<label>Tên đăng nhập:</label><br> <input type="text"
				name="username" required style="width: 100%; padding: 8px;">
		</div>

		<div style="margin-bottom: 15px;">
			<label>Mật khẩu:</label><br> <input type="password"
				name="password" required style="width: 100%; padding: 8px;">
		</div>

		<button type="submit"
			style="width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; cursor: pointer; font-weight: bold;">
			ĐĂNG NHẬP</button>

		<div style="margin-top: 15px; text-align: center;">
			<a href="${pageContext.request.contextPath}/register">Chưa có tài
				khoản? Đăng ký ngay</a>
		</div>
	</form>
</div>