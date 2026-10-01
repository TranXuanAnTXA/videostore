<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn hàng - Admin</title>
    <style>
        .admin-container { max-width: 1200px; margin: auto; padding: 20px; font-family: Arial, sans-serif; }
        .status-menu { display: flex; gap: 8px; margin-bottom: 25px; flex-wrap: wrap; border-bottom: 2px solid #ddd; padding-bottom: 12px; }
        .status-btn { padding: 8px 12px; background: #f8f9fa; color: #333; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 14px; border: 1px solid #ddd; transition: 0.2s; }
        .status-btn:hover { background: #e2e6ea; }
        .status-btn.active { background: #c0392b; color: white; border-color: #c0392b; }
        
        .order-card { border: 1px solid #ccc; border-radius: 8px; padding: 20px; margin-bottom: 20px; background: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .order-header { display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #eee; padding-bottom: 10px; margin-bottom: 15px; flex-wrap: wrap; gap: 10px; }
        .table-details { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .table-details th, .table-details td { padding: 8px; text-align: left; border-bottom: 1px solid #f1f1f1; font-size: 14px; }
        .table-details th { background: #f8f9fa; }
    </style>
</head>
<body>

    <div class="admin-container">
        <h2 style="color: #c0392b; margin-bottom: 10px;">🛠️ TRANG QUẢN TRỊ - DUYỆT ĐƠN HÀNG</h2>
        <a href="${pageContext.request.contextPath}/home" style="text-decoration: none; font-weight: bold; display: inline-block; margin-bottom: 20px;">← Về trang chủ</a>

        <!-- Thanh Menu Lọc Trạng Thái dành riêng cho Admin -->
        <div class="status-menu">
            <a href="orders?status=ALL" class="status-btn ${currentStatus == 'ALL' ? 'active' : ''}">Tất cả</a>
            <a href="orders?status=Đơn hàng mới" class="status-btn ${currentStatus == 'Đơn hàng mới' ? 'active' : ''}">Đơn hàng mới</a>
            <a href="orders?status=Đã xác nhận" class="status-btn ${currentStatus == 'Đã xác nhận' ? 'active' : ''}">Đã xác nhận</a>
            <a href="orders?status=Chuẩn bị hàng" class="status-btn ${currentStatus == 'Chuẩn bị hàng' ? 'active' : ''}">Chuẩn bị hàng</a>
            <a href="orders?status=Vận chuyển" class="status-btn ${currentStatus == 'Vận chuyển' ? 'active' : ''}">Vận chuyển</a>
            <a href="orders?status=Giao hàng" class="status-btn ${currentStatus == 'Giao hàng' ? 'active' : ''}">Giao hàng</a>
            <a href="orders?status=Đã giao" class="status-btn ${currentStatus == 'Đã giao' ? 'active' : ''}">Đã giao</a>
            <a href="orders?status=Đơn hàng hủy" class="status-btn ${currentStatus == 'Đơn hàng hủy' ? 'active' : ''}">Đơn hàng hủy</a>
            <a href="orders?status=Đơn hàng hoàn" class="status-btn ${currentStatus == 'Đơn hàng hoàn' ? 'active' : ''}">Đơn hàng hoàn</a>
        </div>

        <c:if test="${empty orders}">
            <div style="padding: 25px; background: #fff3cd; color: #856404; border-radius: 6px; text-align: center; border: 1px solid #ffeeba;">
                <b>Không tìm thấy đơn hàng nào ở trạng thái này!</b>
            </div>
        </c:if>

        <c:forEach var="o" items="${orders}">
            <div class="order-card">
                <div class="order-header">
                    <div>
                        <span style="font-size: 16px; color: #2980b9;"><b>Mã đơn:</b> #${o.id}</span> | 
                        <span><b>Khách hàng:</b> ${o.user.username} (${o.user.fullname})</span> <br>
                        <span style="color: #666; font-size: 13px;">Ngày đặt: <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/></span>
                    </div>
                    
                    <div style="text-align: right;">
                        <!-- ADMIN THAY ĐỔI TRẠNG THÁI TRỰC TIẾP -->
                        <form action="${pageContext.request.contextPath}/admin/orders" method="post" style="display: inline-block;">
                            <input type="hidden" name="orderId" value="${o.id}">
                            <label style="font-size: 13px; font-weight: bold;">Trạng thái:</label>
                            <select name="status" onchange="this.form.submit()" style="padding: 6px; border-radius: 4px; border: 1px solid #e67e22; font-weight: bold; color: #d35400;">
                                <option value="Đơn hàng mới" ${o.status == 'Đơn hàng mới' ? 'selected' : ''}>Đơn hàng mới</option>
                                <option value="Đã xác nhận" ${o.status == 'Đã xác nhận' ? 'selected' : ''}>Đã xác nhận</option>
                                <option value="Chuẩn bị hàng" ${o.status == 'Chuẩn bị hàng' ? 'selected' : ''}>Chuẩn bị hàng</option>
                                <option value="Vận chuyển" ${o.status == 'Vận chuyển' ? 'selected' : ''}>Vận chuyển</option>
                                <option value="Giao hàng" ${o.status == 'Giao hàng' ? 'selected' : ''}>Giao hàng</option>
                                <option value="Đã giao" ${o.status == 'Đã giao' ? 'selected' : ''}>Đã giao</option>
                                <option value="Đơn hàng hủy" ${o.status == 'Đơn hàng hủy' ? 'selected' : ''}>Đơn hàng hủy</option>
                                <option value="Đơn hàng hoàn" ${o.status == 'Đơn hàng hoàn' ? 'selected' : ''}>Đơn hàng hoàn</option>
                            </select>
                        </form>
                        <br>
                        <span style="font-size: 16px; margin-top: 5px; display: inline-block;">Tổng tiền: <b style="color: #c0392b;">${o.totalAmount} VNĐ</b></span>
                    </div>
                </div>

                <table class="table-details">
                    <tr>
                        <th>Mã Video</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                        <th>Thành tiền</th>
                    </tr>
                    <c:forEach var="detail" items="${o.orderDetails}">
                        <tr>
                            <td>Video #${detail.video.videoId}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.price} VNĐ</td>
                            <td style="color: #27ae60; font-weight: bold;">${detail.price * detail.quantity} VNĐ</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </div>

</body>
</html>