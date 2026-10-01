<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Người dùng - Admin</title>
    <style>
        .admin-container { max-width: 1200px; margin: auto; padding: 20px; font-family: Arial, sans-serif; }
        .form-box { background: #f8f9fa; padding: 20px; border-radius: 8px; border: 1px solid #ddd; margin-bottom: 30px; box-shadow: 0 2px 5px rgba(0,0,0,0.05); }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 5px; color: #333; }
        .form-control { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        .btn { padding: 8px 15px; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; text-decoration: none; display: inline-block; font-size: 14px; }
        .btn-primary { background: #007bff; color: white; }
        .btn-primary:hover { background: #0056b3; }
        .btn-warning { background: #ffc107; color: #333; }
        .btn-danger { background: #dc3545; color: white; }
        .btn-danger:hover { background: #bd2130; }
        
        .table-users { width: 100%; border-collapse: collapse; margin-top: 10px; background: white; box-shadow: 0 2px 5px rgba(0,0,0,0.05); }
        .table-users th, .table-users td { padding: 12px; text-align: left; border-bottom: 1px solid #eee; font-size: 14px; }
        .table-users th { background: #343a40; color: white; }
        .badge { padding: 4px 8px; border-radius: 4px; font-size: 12px; font-weight: bold; }
        .badge-admin { background: #e74c3c; color: white; }
        .badge-user { background: #2ecc71; color: white; }
    </style>
</head>
<body>

    <div class="admin-container">
        <h2 style="color: #17a2b8; margin-bottom: 5px;">👥 QUẢN TRỊ - QUẢN LÝ NGƯỜI DÙNG</h2>
        <a href="${pageContext.request.contextPath}/home" style="text-decoration: none; font-weight: bold; display: inline-block; margin-bottom: 15px;">← Về trang chủ</a>
        <hr style="margin-bottom: 25px;">

        <!-- Form Chỉnh sửa Người dùng (Chỉ hiện khi Admin bấm nút Sửa ở bảng bên dưới) -->
        <c:if test="${not empty userEdit}">
            <div class="form-box">
                <h3 style="margin-top: 0; color: #333;">Chỉnh sửa tài khoản: <span style="color: #007bff;">${userEdit.username}</span></h3>
                <form action="${pageContext.request.contextPath}/admin/user/update" method="post">
                    <input type="hidden" name="username" value="${userEdit.username}">
                    
                    <div style="display: flex; gap: 20px;">
                        <div class="form-group" style="flex: 1;">
                            <label>Họ và tên:</label>
                            <input type="text" name="fullname" value="${userEdit.fullname}" class="form-control" required>
                        </div>
                        <div class="form-group" style="flex: 1;">
                            <label>Email:</label>
                            <input type="email" name="email" value="${userEdit.email}" class="form-control" required>
                        </div>
                    </div>

                    <div style="display: flex; gap: 20px;">
                        <div class="form-group" style="flex: 1;">
                            <label>Số điện thoại:</label>
                            <input type="text" name="phone" value="${userEdit.phone}" class="form-control">
                        </div>
                        <div class="form-group" style="flex: 1; display: flex; align-items: center; gap: 30px; padding-top: 22px;">
                            <label style="cursor: pointer; font-weight: bold;">
                                <input type="checkbox" name="admin" ${userEdit.admin ? 'checked' : ''}> Quyền Admin
                            </label>
                            <label style="cursor: pointer; font-weight: bold;">
                                <input type="checkbox" name="active" ${userEdit.active ? 'checked' : ''}> Kích hoạt (Active)
                            </label>
                        </div>
                    </div>

                    <div style="margin-top: 15px;">
                        <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                        <a href="${pageContext.request.contextPath}/admin/user" class="btn" style="background: #6c757d; color: white; margin-left: 10px;">Hủy bỏ</a>
                    </div>
                </form>
            </div>
        </c:if>

        <!-- Bảng danh sách Người dùng toàn hệ thống -->
        <h3>Danh sách tài khoản hệ thống</h3>
        <table class="table-users">
            <tr>
                <th>Tên đăng nhập</th>
                <th>Họ và tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th style="text-align: center;">Vai trò</th>
                <th style="text-align: center;">Trạng thái</th>
                <th style="text-align: center;">Thao tác</th>
            </tr>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td><b>${u.username}</b></td>
                    <td>${u.fullname}</td>
                    <td>${u.email}</td>
                    <td>${empty u.phone ? 'Chưa cập nhật' : u.phone}</td>
                    <td style="text-align: center;">
                        <c:choose>
                            <c:when test="${u.admin}">
                                <span class="badge badge-admin">Admin</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-user">User</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <span style="color: ${u.active ? 'green' : 'red'}; font-weight: bold;">
                            ${u.active ? 'Hoạt động' : 'Đã khóa'}
                        </span>
                    </td>
                    <td style="text-align: center;">
                        <a href="${pageContext.request.contextPath}/admin/user/edit?username=${u.username}" class="btn btn-warning" style="padding: 5px 12px; font-size: 13px;">Sửa</a>
                        
                        <!-- Chặn không cho Admin tự xóa chính tài khoản mình đang đăng nhập -->
                        <c:if test="${sessionScope.user.username != u.username}">
                            <a href="${pageContext.request.contextPath}/admin/user/delete?username=${u.username}" 
                               onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản [${u.username}] này không?')" 
                               class="btn btn-danger" style="padding: 5px 12px; font-size: 13px; margin-left: 5px;">Xóa</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>