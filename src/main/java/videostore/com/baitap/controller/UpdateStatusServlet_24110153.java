package videostore.com.baitap.controller;

import videostore.com.baitap.dao.OrderDAO_24110153;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-status")
public class UpdateStatusServlet_24110153 extends HttpServlet {
    private final OrderDAO_24110153 orderDAO = new OrderDAO_24110153();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String status = request.getParameter("status");
            
            // Gọi hàm cập nhật trong DAO
            orderDAO.updateOrderStatus(orderId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Cập nhật xong chuyển hướng về lại trang lịch sử
        response.sendRedirect(request.getContextPath() + "/order-history");
    }
}