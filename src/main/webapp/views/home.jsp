<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ - Video Store</title>
</head>

<body>

	<div style="max-width: 1100px; margin: auto; padding: 20px; font-family: Arial;">

		<!-- Lời chào & Nút truy nhanh (Đã đặt đúng chuẩn bên trong container) -->
		<c:if test="${not empty sessionScope.user}">
			<div style="display: flex; justify-content: space-between; align-items: center; padding: 15px; background: #e2f3f5; border-left: 5px solid #17a2b8; border-radius: 5px; margin-bottom: 20px;">
				<div>
					👋 Xin chào <b>${sessionScope.user.fullname}</b>! Chúc bạn có trải nghiệm xem video tuyệt vời.
				</div>
				<div style="display: flex; gap: 10px;">
					<a href="${pageContext.request.contextPath}/cart"
					   style="background: #ffc107; color: #333; padding: 8px 15px; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 14px;">
					   🛒 Giỏ Hàng
					</a> 
					<a href="${pageContext.request.contextPath}/order-history"
					   style="background: #17a2b8; color: white; padding: 8px 15px; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 14px;">
					   📦 Lịch Sử Đặt Hàng
					</a>
				</div>
			</div>
		</c:if>

		<!-- Category -->
		<div style="background: #eee; padding: 15px; border-radius: 6px; margin-bottom: 30px;">
			<h3>Danh mục thể loại</h3>

			<a href="${pageContext.request.contextPath}/home"
				style="margin-right:15px; font-weight:bold; text-decoration:none; color:${empty selectedCategoryId ? 'red':'blue'}">
				Tất cả </a>

			<c:forEach var="cat" items="${categories}">
				<a href="${pageContext.request.contextPath}/home?categoryId=${cat.categoryId}"
					style="margin-right:15px; text-decoration:none; font-weight:bold; color:${selectedCategoryId==cat.categoryId?'red':'#007bff'}">
					${cat.categoryName} (${videoCounts[cat.categoryId]==null?0:videoCounts[cat.categoryId]})
				</a>
			</c:forEach>
		</div>

		<h2>DANH SÁCH VIDEO</h2>

		<!-- Danh sách video -->
		<div style="display: flex; flex-wrap: wrap; gap: 20px;">

			<c:forEach var="v" items="${videos}">

				<div style="width: 31%; border: 1px solid #ddd; border-radius: 8px; overflow: hidden; background: white; box-shadow: 0 2px 6px rgba(0, 0, 0, .1);">

					<!-- Poster -->
					<img src="${v.poster}" alt="${v.title}" style="width: 100%; height: 180px; object-fit: cover;">

					<div style="padding: 15px;">

						<h3 style="margin-top: 0;">
							<a href="${pageContext.request.contextPath}/video/detail?id=${v.videoId}"
								style="text-decoration: none; color: #007bff;"> ${v.title} </a>
						</h3>

						<p><b>Mã video:</b> ${v.videoId}</p>
						<p><b>Category:</b> ${empty v.category ? "Khác" : v.category.categoryName}</p>
						<p><b>Views:</b> ${v.views}</p>
						<p><b>Shares:</b> ${shareCounts[v.videoId]==null?0:shareCounts[v.videoId]}</p>
						<p><b>Likes:</b> ${favoriteCounts[v.videoId]==null?0:favoriteCounts[v.videoId]}</p>
						
						<p><b>Kho còn:</b> <span style="color: red; font-weight: bold;">${v.quantity}</span> bản</p>

						<a href="${pageContext.request.contextPath}/video/detail?id=${v.videoId}"
							style="display: block; margin-top: 15px; padding: 10px; text-align: center; text-decoration: none; background: #28a745; color: white; border-radius: 5px; font-weight: bold;">
							Xem Chi Tiết </a>
							
						<form action="${pageContext.request.contextPath}/cart" method="post" style="margin-top: 10px;">
							<input type="hidden" name="action" value="add"> 
							<input type="hidden" name="videoId" value="${v.videoId}">
							<button type="submit"
								style="display: block; width: 100%; padding: 10px; text-align: center; background: #ffc107; color: #333; border: none; border-radius: 5px; font-weight: bold; cursor: pointer;">
								🛒 Thêm Vào Giỏ</button>
						</form>
					</div>

				</div>

			</c:forEach>

		</div>

		<!-- Phân trang -->
		<div style="margin-top: 35px; text-align: center;">
			<c:if test="${totalPages>0}">
				<c:forEach begin="1" end="${totalPages}" var="i">
					<a href="${pageContext.request.contextPath}/home?page=${i}${not empty selectedCategoryId ? '&categoryId='.concat(selectedCategoryId):''}"
						style="display:inline-block; padding:8px 14px; margin:0 4px; text-decoration:none; border:1px solid #007bff; border-radius:4px; font-weight:bold; ${currentPage==i?'background:#007bff;color:white;':'background:white;color:#007bff;'}">
						${i} </a>
				</c:forEach>
			</c:if>
		</div>

	</div>

</body>
</html>