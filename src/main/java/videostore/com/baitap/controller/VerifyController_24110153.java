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

@WebServlet("/verify")
public class VerifyController_24110153 extends HttpServlet {

    private UserService_24110153 userService = new UserService_24110153();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/verify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // Lấy User và OTP đang được "treo" trên Session (từ bước Register chuyển sang)
        User pendingUser = (User) session.getAttribute("pendingUser");
        String sentOtp = (String) session.getAttribute("sentOtp");
        
        // Lấy mã OTP người dùng nhập vào trên giao diện
        String userOtp = req.getParameter("otp");

        // Nếu Session bị mất (do để máy quá lâu) hoặc truy cập lụi
        if (pendingUser == null || sentOtp == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }

        // Kiểm tra OTP
        if (sentOtp.equals(userOtp)) {
            // Đúng OTP -> Lưu vào Database
            userService.register(pendingUser);
            
            // Xóa rác trên Session
            session.removeAttribute("pendingUser");
            session.removeAttribute("sentOtp");
            
            // Chuyển hướng sang trang đăng nhập (sẽ làm ở bước sau)
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Sai OTP -> Báo lỗi và bắt nhập lại
            req.setAttribute("error", "Mã OTP không chính xác!");
            req.getRequestDispatcher("/views/verify.jsp").forward(req, resp);
        }
    }
}