package videostore.com.baitap.controller;

import videostore.com.baitap.dao.OrderDAO_24110153;
import videostore.com.baitap.entity.Order;
import videostore.com.baitap.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/order-history")
public class OrderHistoryServlet_24110153 extends HttpServlet {
    
    private final OrderDAO_24110153  orderDAO = new OrderDAO_24110153 ();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // Đã sửa thành "user" để khớp với session đăng nhập của bạn
        User user = (User) session.getAttribute("user");

        // 1. Kiểm tra đăng nhập
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2. Lấy tham số trạng thái từ URL (VD: ?status=Đã xác nhận)
        String status = request.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            status = "ALL"; // Mặc định hiển thị tất cả
        }

        // 3. Truy vấn Database
        List<Order> orders = orderDAO.findByUsernameAndStatus(user.getUsername(), status);

        // 4. Đẩy dữ liệu sang trang JSP
        request.setAttribute("orders", orders);
        request.setAttribute("currentStatus", status);

        // 5. Chuyển hướng tới file JSP (nằm trong thư mục views do dùng Sitemesh)
        request.getRequestDispatcher("/views/order-history.jsp").forward(request, response);
    }
}