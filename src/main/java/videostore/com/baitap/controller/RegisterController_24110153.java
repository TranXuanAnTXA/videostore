package videostore.com.baitap.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import videostore.com.baitap.entity.User;
import videostore.com.baitap.service.UserService_24110153;
import videostore.com.baitap.utils.OTPUtil_24110153;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController_24110153 extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService_24110153 userService = new UserService_24110153();

    // Hiển thị giao diện khi người dùng vào URL /register
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    // Xử lý khi người dùng bấm nút "Đăng Ký" (Submit Form)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy dữ liệu từ form HTML
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        
        // 2. Kiểm tra tài khoản đã tồn tại trong CSDL chưa
        User existUser = userService.findByUsername(username);
        if (existUser != null) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // 3. Nếu chưa tồn tại, đóng gói dữ liệu vào đối tượng User (chưa lưu vào DB)
        User pendingUser = new User();
        pendingUser.setUsername(username);
        pendingUser.setPassword(req.getParameter("password"));
        pendingUser.setFullname(req.getParameter("fullname"));
        pendingUser.setEmail(email);
        pendingUser.setPhone(req.getParameter("phone"));
        pendingUser.setAdmin(false);
        pendingUser.setActive(true);

        // 4. Sinh mã OTP
        String otpCode = OTPUtil_24110153.generateOTP();

        // 5. GIẢ LẬP GỬI EMAIL (In ra Console để bạn lấy mã)
        System.out.println("=========================================");
        System.out.println("ĐÃ MÔ PHỎNG GỬI EMAIL ĐẾN: " + email);
        System.out.println("MÃ OTP CỦA BẠN LÀ: " + otpCode);
        System.out.println("=========================================");

        // 6. Lưu tạm thông tin User và mã OTP vào Session
        HttpSession session = req.getSession();
        session.setAttribute("pendingUser", pendingUser);
        session.setAttribute("sentOtp", otpCode);

        // 7. Chuyển hướng sang trang xác thực (sẽ làm ở bước 8)
        resp.sendRedirect(req.getContextPath() + "/verify");
    }
}