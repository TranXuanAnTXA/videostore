<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Tiết Video</title>
<style>
    /* Chỉnh style đồng bộ với các trang khác */
    .detail-container { max-width: 900px; margin: 30px auto; background: #fff; padding: 25px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,.15); font-family: Arial, sans-serif; }
    
    /* Style cho bảng đúng chuẩn yêu cầu đề bài */
    .detail-table { width: 100%; border-collapse: collapse; margin-top: 20px; font-size: 16px; }
    .detail-table td { border: 1px solid #333; padding: 15px; vertical-align: top; }
    
    .poster-img { width: 100%; max-width: 320px; height: auto; object-fit: cover; display: block; margin: auto; border-radius: 5px; }
    .info-text { margin-bottom: 12px; font-size: 17px; }
    
    .btn-group { margin-top: 30px; display: flex; justify-content: center; gap: 15px; }
    .btn-back { background: #6c757d; color: white; text-decoration: none; padding: 10px 25px; border-radius: 5px; font-weight: bold; display: flex; align-items: center; }
    .btn-cart { background: #ffc107; color: #333; border: none; padding: 10px 25px; border-radius: 5px; font-weight: bold; cursor: pointer; font-size: 16px; }
    .btn-cart:hover { background: #e0a800; }
</style>
</head>
<body>

<div class="detail-container">

    <h2 style="text-align:center; color:#007bff; margin-bottom: 20px;">
        CHI TIẾT VIDEO
    </h2>

    <!-- Bảng thông tin làm đúng theo Wireframe của câu 3 (1.5 điểm) -->
    <table class="detail-table">
        <tr>
            <!-- Cột trái: Poster -->
            <td style="width: 40%; text-align: center; vertical-align: middle;">
                <img src="${video.poster}" alt="${video.title}" class="poster-img">
            </td>
            
            <!-- Cột phải: Thông tin -->
            <td style="width: 60%; line-height: 1.6;">
                <div class="info-text">Tiêu đề: <b>${video.title}</b></div>
                <div class="info-text">Mã video: <b>${video.videoId}</b></div>
                
                <div class="info-text">
                    Category name: <b>
                    <c:choose>
                        <c:when test="${not empty video.category}">
                            ${video.category.categoryName}
                        </c:when>
                        <c:otherwise>Chưa phân loại</c:otherwise>
                    </c:choose>
                    </b>
                </div>
                
                <div class="info-text">View: <b>${video.views}</b></div>
                
                <!-- Hiển thị Like và Share. Nếu Servlet chưa truyền biến này thì mặc định hiển thị 0 -->
                <div class="info-text">Share(${shareCount == null ? 0 : shareCount})</div>
                <div class="info-text">Like(${likeCount == null ? 0 : likeCount})</div>
                
                <!-- THÊM SỐ LƯỢNG Ở ĐÂY CHO ĐỒNG BỘ -->
                <div class="info-text">Kho còn: <b style="color: red;">${video.quantity} bản</b></div>
            </td>
        </tr>
        
        <!-- Dòng dưới cùng: Description gộp 2 cột -->
        <tr>
            <td colspan="2">
                <b style="font-size: 18px;">description</b> <br><br>
                <div style="line-height: 1.5; color: #444;">
                    <c:choose>
                        <c:when test="${not empty video.description}">
                            ${video.description}
                        </c:when>
                        <c:otherwise>
                            <i>Đang cập nhật mô tả cho video này...</i>
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
        </tr>
    </table>

    <!-- Nút điều hướng & Mua hàng -->
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/home" class="btn-back">
            ← Quay lại Trang chủ
        </a>
        
        <!-- Nút Thêm vào giỏ hàng -->
        <form action="${pageContext.request.contextPath}/cart" method="post" style="margin: 0;">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="videoId" value="${video.videoId}">
            <button type="submit" class="btn-cart">
                🛒 Thêm Vào Giỏ
            </button>
        </form>
    </div>

</div>

</body>
</html>