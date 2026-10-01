<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Quản Lý Thể Loại (Category)</title>

<div style="padding: 20px; font-family: Arial;">
    <h2 style="color: #333;">QUẢN LÝ THỂ LOẠI (CATEGORY)</h2>
    <hr>
    
    <!-- FORM THÊM / SỬA -->
    <form action="${pageContext.request.contextPath}/admin/category/${empty category.categoryId ? 'create' : 'edit'}" 
          method="post" enctype="multipart/form-data" 
          style="background: #f9f9f9; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
        
        <label>Mã Thể Loại (CategoryId):</label><br>
        <!-- Khi Sửa (Edit) thì readonly không cho đổi mã -->
        <input type="text" name="categoryId" value="${category.categoryId}" ${not empty category.categoryId ? 'readonly style="background:#eee;"' : 'required'} style="width: 100%; padding: 8px; margin-bottom: 15px;"><br>
        
        <label>Tên Thể Loại (Category Name):</label><br>
        <input type="text" name="categoryName" value="${category.categoryName}" required style="width: 100%; padding: 8px; margin-bottom: 15px;"><br>
        
        <label>Hình ảnh minh họa (Chọn file):</label><br>
        <input type="file" name="imageFile" accept="image/*" style="width: 100%; padding: 8px; margin-bottom: 15px;"><br>
        <input type="hidden" name="oldImage" value="${category.images}">
        
        <label>Trạng thái hiển thị:</label><br>
        <input type="checkbox" name="status" value="true" ${category.status ? 'checked' : ''} ${empty category ? 'checked' : ''}> Đang hoạt động<br><br>
        
        <button type="submit" style="padding: 10px 20px; background: #28a745; color: white; border: none; font-weight: bold; cursor: pointer;">
            ${empty category.categoryId ? 'THÊM MỚI' : 'CẬP NHẬT'}
        </button>
        <a href="${pageContext.request.contextPath}/admin/category" style="padding: 10px 20px; background: #6c757d; color: white; text-decoration: none; margin-left: 10px;">Làm mới Form</a>
    </form>

    <!-- BẢNG DANH SÁCH CATEGORY -->
    <table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
        <thead style="background: #6c757d; color: white;">
            <tr>
                <th style="padding: 10px;">Mã Thể Loại</th>
                <th>Tên Thể Loại</th>
                <th>Hình Ảnh</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td style="padding: 10px; font-weight:bold;">${c.categoryId}</td>
                    <td>${c.categoryName}</td>
                    <td>
                        <c:if test="${not empty c.images}">
                            <img src="${c.images}" alt="image" width="60" height="40" style="object-fit: cover;">
                        </c:if>
                    </td>
                    <td>${c.status ? '<span style="color:green; font-weight:bold;">Active</span>' : '<span style="color:red;">Hidden</span>'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.categoryId}" style="color: blue; text-decoration: none;">Sửa</a> | 
                        <a href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}" style="color: red; text-decoration: none;" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>