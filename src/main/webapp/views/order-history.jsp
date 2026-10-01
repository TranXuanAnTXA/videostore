<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Theo dõi tiến độ đơn hàng</title>
    <style>
        .history-container { max-width: 1100px; margin: auto; padding: 20px; font-family: Arial, sans-serif; }
        .status-menu { display: flex; gap: 8px; margin-bottom: 25px; flex-wrap: wrap; border-bottom: 2px solid #ddd; padding-bottom: 12px; }
        .status-btn { padding: 8px 12px; background: #f8f9fa; color: #333; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 14px; border: 1px solid #ddd; transition: 0.2s; }
        .status-btn:hover { background: #e2e6ea; }
        .status-btn.active { background: #007bff; color: white; border-color: #007bff; }
        
        .order-card { border: 1px solid #ddd; border-radius: 8px; padding: 20px; margin-bottom: 20px; background: white; box-shadow: 0 2px 6px rgba(0,0,0,0.05); }
        .order-header { display: flex; justify-content: space-between; align-items: center; border-bottom: 1px dashed #ccc; padding-bottom: 12px; margin-bottom: 15px; flex-wrap: wrap; gap: 10px; }
        .table-details { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .table-details th, .table-details td { padding: 10px; text-align: left; border-bottom: 1px solid #eee; }
        .table-details th { background: #f9f9f9; font-weight: bold; color: #555; }
        .badge-status { color: #e67e22; font-weight: bold; padding: 6px 12px; background: #fff4e6; border-radius: 4px; display: inline-block; border: 1px solid #ffe8d6; font-size: 15px; }
    </style>
</head>
<body>

    <div class="history-container">
        <h2>📦 Theo dõi tiến độ đơn hàng của bạn</h2>
        <a href="${pageContext.request.contextPath}/home" style="display:inline-block; margin-bottom:20px; text-decoration:none; font-weight:bold;">← Về trang chủ</a>

        <!-- Thanh Menu Lọc Trạng Thái -->
        <div class="status-menu">
            <a href="order-history?status=ALL" class="status-btn ${currentStatus == 'ALL' ? 'active' : ''}">Tất cả</a>
            <a href="order-history?status=Đơn hàng mới" class="status-btn ${currentStatus == 'Đơn hàng mới' ? 'active' : ''}">Đơn hàng mới</a>
            <a href="order-history?status=Đã xác nhận" class="status-btn ${currentStatus == 'Đã xác nhận' ? 'active' : ''}">Đã xác nhận</a>
            <a href="order-history?status=Chuẩn bị hàng" class="status-btn ${currentStatus == 'Chuẩn bị hàng' ? 'active' : ''}">Chuẩn bị hàng</a>
            <a href="order-history?status=Vận chuyển" class="status-btn ${currentStatus == 'Vận chuyển' ? 'active' : ''}">Vận chuyển</a>
            <a href="order-history?status=Giao hàng" class="status-btn ${currentStatus == 'Giao hàng' ? 'active' : ''}">Giao hàng</a>
            <a href="order-history?status=Đã giao" class="status-btn ${currentStatus == 'Đã giao' ? 'active' : ''}">Đã giao</a>
            <a href="order-history?status=Đơn hàng hủy" class="status-btn ${currentStatus == 'Đơn hàng hủy' ? 'active' : ''}">Đơn hàng hủy</a>
            <a href="order-history?status=Đơn hàng hoàn" class="status-btn ${currentStatus == 'Đơn hàng hoàn' ? 'active' : ''}">Đơn hàng hoàn</a>
        </div>

        <c:if test="${empty orders}">
            <div style="padding: 25px; background: #fff3cd; color: #856404; border-radius: 6px; text-align: center; border: 1px solid #ffeeba;">
                <b>Không có đơn hàng nào ở trạng thái này!</b>
            </div>
        </c:if>

        <c:forEach var="o" items="${orders}">
            <div class="order-card">
                <div class="order-header">
                    <div>
                        <span style="font-size: 16px; color: #007bff;"><b>Mã đơn hàng:</b> #${o.id}</span> <br>
                        <span style="color: #666; font-size: 14px;">
                            Ngày đặt: <fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                        </span>
                    </div>
                    <!-- USER CHỈ XEM TIẾN ĐỘ THÔNG QUA NHÃN NÀY -->
                    <div style="text-align: right;">
                        <span style="font-size: 13px; color: #555; display: block; margin-bottom: 4px;">Tiến độ đơn hàng:</span>
                        <span class="badge-status">📌 ${o.status}</span> <br>
                        <span style="font-size: 16px; margin-top: 8px; display: inline-block;">Tổng tiền: <b style="color: #e74c3c;">${o.totalAmount} VNĐ</b></span>
                    </div>
                </div>

                <table class="table-details">
                    <tr>
                        <th>Mã Video</th>
                        <th style="text-align: center;">Số lượng mua</th>
                        <th style="text-align: right;">Đơn giá</th>
                        <th style="text-align: right;">Thành tiền</th>
                    </tr>
                    <c:forEach var="detail" items="${o.orderDetails}">
                        <tr>
                            <td><b>Video số ${detail.video.videoId}</b></td>
                            <td style="text-align: center;">${detail.quantity}</td>
                            <td style="text-align: right;">${detail.price} VNĐ</td>
                            <td style="text-align: right; color: #28a745; font-weight: bold;">
                                ${detail.price * detail.quantity} VNĐ
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </div>

</body>
</html>