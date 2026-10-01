package videostore.com.baitap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import videostore.com.baitap.entity.User;
import videostore.com.baitap.service.UserService_24110153;

import java.io.IOException;

@WebServlet("/login")
public class LoginController_24110153 extends HttpServlet {

    private UserService_24110153 userService = new UserService_24110153();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Gọi Service tìm tài khoản trong Database
        User user = userService.findByUsername(username);

        // Kiểm tra tài khoản có tồn tại và mật khẩu có khớp không
        if (user != null && user.getPassword().equals(password)) {
            
            // 1. Lưu thông tin vào Session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

         // 2. Phân quyền điều hướng
            if (user.getAdmin() != null && user.getAdmin() == true) {
                // Nếu là Admin -> Chuyển vào trang chủ Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else {
                // Nếu là User thường -> Chuyển về trang chủ User
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            
        } else {
            // Sai tài khoản hoặc mật khẩu
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}