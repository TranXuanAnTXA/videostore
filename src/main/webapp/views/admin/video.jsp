<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>Quản Lý Video</title>

<div style="padding: 20px; font-family: Arial;">
	<h2 style="color: #333;">QUẢN LÝ VIDEO</h2>
	<hr>

	<!-- FORM THÊM / SỬA -->
	<form
		action="${pageContext.request.contextPath}/admin/video/${empty video.videoId ? 'create' : 'edit'}"
		method="post" enctype="multipart/form-data"
		style="background: #f9f9f9; padding: 20px; border-radius: 8px; margin-bottom: 20px;">

		<c:if test="${not empty video.videoId}">
			<label>ID Video:</label>
			<br>
			<input type="text" name="id" value="${video.videoId}" readonly
				style="width: 100%; padding: 8px; background: #eee;">
			<br>
			<br>
		</c:if>

		<label>Tiêu đề:</label><br> <input type="text" name="title"
			value="${video.title}" required style="width: 100%; padding: 8px;"><br>
		<br> <label>Poster:</label><br>

		<c:if test="${not empty video.poster}">
			<img src="${video.poster}" width="120"
				style="margin-bottom: 10px; border-radius: 5px;">
			<br>
		</c:if>

		<input type="file" name="posterFile" accept="image/*"><br>
		<br> <input type="hidden" name="oldPoster"
			value="${video.poster}"> <label> <input
			type="checkbox" name="active"
			${empty video || video.active ? "checked" : ""}> Đang hoạt
			động
		</label> <input type="hidden" name="views"
			value="${empty video ? 0 : video.views}"> <br>
		<br>

		<button type="submit"
			style="padding: 10px 20px; background: #28a745; color: white; border: none;">
			${empty video.videoId ? 'THÊM MỚI' : 'CẬP NHẬT'}</button>

		<a href="${pageContext.request.contextPath}/admin/video"
			style="padding: 10px 20px; background: #6c757d; color: white; text-decoration: none;">
			Làm mới </a>

	</form>
	<!-- BẢNG DANH SÁCH -->
	<table border="1"
		style="width: 100%; border-collapse: collapse; text-align: center;">
		<thead style="background: #007bff; color: white;">
			<tr>
				<th style="padding: 10px;">ID</th>
				<th>Tiêu đề</th>
				<th>Poster</th>
				<th>Lượt xem</th>
				<th>Trạng thái</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${videos}">
				<tr>
					<td style="padding: 10px;">${v.videoId}</td>
					<td>${v.title}</td>
					<td><img src="${v.poster}" alt="poster" width="60" height="40"
						style="object-fit: cover;"></td>
					<td>${v.views}</td>
					<td>${v.active ? '<span style="color:green; font-weight:bold;">Active</span>' : '<span style="color:red;">Hidden</span>'}</td>
					<td><a
						href="${pageContext.request.contextPath}/admin/video/edit?id=${v.videoId}"
						style="color: blue; text-decoration: none;">Sửa</a> | <a
						href="${pageContext.request.contextPath}/admin/video/delete?id=${v.videoId}"
						style="color: red; text-decoration: none;"
						onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- THANH PHÂN TRANG -->
	<div style="margin-top: 20px; text-align: center;">
		<c:if test="${totalPages > 0}">
			<span style="margin-right: 15px; color: #555;">Trang
				${currentPage} / ${totalPages}</span>
			<c:forEach begin="1" end="${totalPages}" var="i">
				<a href="${pageContext.request.contextPath}/admin/video?page=${i}"
					style="display: inline-block; padding: 8px 14px; margin: 0 4px; border: 1px solid #007bff; text-decoration: none; border-radius: 4px; font-weight: bold;
                   ${currentPage == i ? 'background: #007bff; color: white;' : 'background: white; color: #007bff;'}">
					${i} </a>
			</c:forEach>
		</c:if>
	</div>
</div>