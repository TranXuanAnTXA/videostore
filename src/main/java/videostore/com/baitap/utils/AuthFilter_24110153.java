package videostore.com.baitap.utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import videostore.com.baitap.entity.User;
import java.io.IOException;

// Bộ lọc này sẽ chặn TẤT CẢ các đường dẫn có chữ /admin/ ở đầu
@WebFilter("/admin/*")
public class AuthFilter_24110153 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        // Lấy thông tin user đang đăng nhập
        User user = (User) session.getAttribute("user");

        // Kiểm tra: Nếu đã đăng nhập VÀ là Admin
        if (user != null && Boolean.TRUE.equals(user.getAdmin())) {
            // Hợp lệ -> Mở cổng cho đi tiếp vào Controller tương ứng
            chain.doFilter(request, response);
        } else {
            // Không hợp lệ (chưa login hoặc là user thường) -> Đá về Login
            req.setAttribute("error", "Bạn không có quyền truy cập khu vực Quản trị!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}