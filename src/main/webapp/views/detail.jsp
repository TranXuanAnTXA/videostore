<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Tiết Video</title>
</head>
<body>

<div style="max-width:800px; margin:30px auto; background:#fff; padding:25px;
            border-radius:10px; box-shadow:0 2px 10px rgba(0,0,0,.15);">

    <h2 style="text-align:center; color:#007bff;">
        Chi Tiết Video
    </h2>

    <div style="text-align:center; margin:20px 0;">
        <img src="${video.poster}"
             alt="${video.title}"
             style="width:100%; max-height:400px; object-fit:cover;
                    border-radius:8px;">
    </div>

    <table style="width:100%; border-collapse:collapse; font-size:16px;">
        <tr>
            <td style="width:180px;"><b>Mã video</b></td>
            <td>${video.videoId}</td>
        </tr>

        <tr>
            <td><b>Tiêu đề</b></td>
            <td>${video.title}</td>
        </tr>

        <tr>
            <td><b>Thể loại</b></td>
            <td>
                <c:choose>
                    <c:when test="${not empty video.category}">
                        ${video.category.categoryName}
                    </c:when>
                    <c:otherwise>
                        Chưa phân loại
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td><b>Lượt xem</b></td>
            <td>${video.views}</td>
        </tr>

        <tr>
            <td style="vertical-align:top;"><b>Mô tả</b></td>
            <td>
                <c:choose>
                    <c:when test="${not empty video.description}">
                        ${video.description}
                    </c:when>
                    <c:otherwise>
                        Không có mô tả.
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <div style="margin-top:30px; text-align:center;">
        <a href="${pageContext.request.contextPath}/home"
           style="background:#007bff;
                  color:white;
                  text-decoration:none;
                  padding:10px 25px;
                  border-radius:5px;">
            ← Quay lại Trang chủ
        </a>
    </div>

</div>

</body>
</html>