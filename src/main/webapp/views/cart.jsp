<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Giỏ hàng của bạn</title>
<style>
.cart-container {
	font-family: Arial, sans-serif;
	padding: 20px;
	max-width: 1100px;
	margin: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #f4f4f4;
}

.btn {
	padding: 8px 15px;
	border: none;
	cursor: pointer;
	color: white;
	border-radius: 4px;
}

.btn-update {
	background-color: #f39c12;
}

.btn-remove {
	background-color: #e74c3c;
}

.btn-checkout {
	background-color: #2ecc71;
	font-size: 16px;
	padding: 10px 20px;
	text-decoration: none;
	display: inline-block;
}
</style>
</head>
<body>
	<!-- ĐÃ THÊM THẺ DIV NÀY ĐỂ BỌC NỘI DUNG LẠI -->
	<div class="cart-container">

		<h2>Giỏ hàng của bạn</h2>

		<c:if test="${empty sessionScope.cart}">
			<p>Giỏ hàng đang trống!</p>
			<a href="home">Quay lại trang chủ</a>
		</c:if>

		<c:if test="${not empty sessionScope.cart}">
			<table>
				<tr>
					<th>Tên Video</th>
					<th>Giá</th>
					<th>Số lượng</th>
					<th>Thành tiền</th>
					<th>Thao tác</th>
				</tr>

				<c:set var="total" value="0" />

				<c:forEach var="entry" items="${sessionScope.cart}">
					<tr>
						<td>${entry.value.title}</td>
						<td>${entry.value.price}VNĐ</td>
						<td>
							<!-- Form cập nhật số lượng -->
							<form action="${pageContext.request.contextPath}/cart"
								method="post" style="display: inline;">
								<input type="number" name="quantity"
									value="${entry.value.quantity}" min="1"
									max="${entry.value.stock}" style="width: 50px;">
								<button type="submit" class="btn btn-update">Cập nhật</button>
							</form>
						</td>
						<td>${entry.value.price * entry.value.quantity}VNĐ</td>
						<td>
							<!-- Form xóa khỏi giỏ hàng -->
							<form action="${pageContext.request.contextPath}/cart"
								method="post" style="display: inline;">
								<input type="hidden" name="action" value="remove"> <input
									type="hidden" name="videoId" value="${entry.key}">
								<button type="submit" class="btn btn-remove">Xóa</button>
							</form>
						</td>
					</tr>
					<c:set var="total"
						value="${total + (entry.value.price * entry.value.quantity)}" />
				</c:forEach>
			</table>

			<h3>Tổng cộng: ${total} VNĐ</h3>

			<!-- Form thanh toán COD -->
			<form action="${pageContext.request.contextPath}/checkout"
				method="post">
				<button type="submit" class="btn btn-checkout">Thanh toán
					(COD)</button>
			</form>
		</c:if>

	</div>
	<!-- ĐÓNG THẺ DIV Ở ĐÂY -->
</body>
</html>