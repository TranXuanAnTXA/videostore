package videostore.com.baitap.controller;

import videostore.com.baitap.dao.UserDAO_24110153;
import videostore.com.baitap.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet({
    "/admin/user",
    "/admin/user/edit",
    "/admin/user/update",
    "/admin/user/delete"
})
public class AdminUserServlet_24110153 extends HttpServlet {
    private final UserDAO_24110153 userDAO = new UserDAO_24110153();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Kiểm tra quyền Admin bảo mật
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !currentUser.getAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String uri = request.getRequestURI();
        
        if (uri.contains("edit")) {
            // Lấy thông tin user cần sửa để đổ lên form
            String username = request.getParameter("username");
            User userEdit = userDAO.findById(username);
            request.setAttribute("userEdit", userEdit);
            
            // Lấy toàn bộ danh sách để hiển thị bên dưới bảng
            List<User> list = userDAO.findAll();
            request.setAttribute("users", list);
            request.getRequestDispatcher("/views/admin/admin-users.jsp").forward(request, response);
            
        } else if (uri.contains("delete")) {
            // Xóa người dùng theo username
            String username = request.getParameter("username");
            userDAO.delete(username);
            response.sendRedirect(request.getContextPath() + "/admin/user");
            
        } else {
            // Mặc định: Hiển thị danh sách toàn bộ người dùng
            List<User> list = userDAO.findAll();
            request.setAttribute("users", list);
            request.getRequestDispatcher("/views/admin/admin-users.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !currentUser.getAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String uri = request.getRequestURI();
        
        if (uri.contains("update")) {
            try {
                String username = request.getParameter("username");
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                
                // Checkbox trả về "on" hoặc không null nếu được tích, null nếu bỏ trống
                boolean admin = request.getParameter("admin") != null;
                boolean active = request.getParameter("active") != null;

                // Lấy user cũ để giữ nguyên mật khẩu và hình ảnh
                User user = userDAO.findById(username);
                if (user != null) {
                    user.setFullname(fullname);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setAdmin(admin);
                    user.setActive(active);
                    
                    userDAO.update(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Cập nhật xong chuyển hướng về lại trang quản lý user
            response.sendRedirect(request.getContextPath() + "/admin/user");
        }
    }
}