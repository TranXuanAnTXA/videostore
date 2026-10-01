package videostore.com.baitap.controller;

import videostore.com.baitap.dao.OrderDAO_24110153;
import videostore.com.baitap.entity.CartItem;
import videostore.com.baitap.entity.Order;
import videostore.com.baitap.entity.OrderDetail;
import videostore.com.baitap.entity.User;
import videostore.com.baitap.entity.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/checkout")
public class CheckoutServlet_24110153 extends HttpServlet {
    
    private final OrderDAO_24110153 orderDAO = new OrderDAO_24110153();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        // Nếu chưa đăng nhập thì bắt đăng nhập
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setPaymentMethod("COD");
        order.setStatus("Đơn hàng mới");
        
        double totalAmount = 0;
        List<OrderDetail> details = new ArrayList<>();
        
        for (CartItem item : cart.values()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            
            Video video = new Video(); 
            video.setVideoId(item.getVideoId()); // Gán ID video
            
            detail.setVideo(video);
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getPrice());
            
            details.add(detail);
            totalAmount += item.getPrice() * item.getQuantity();
        }
        
        order.setTotalAmount(totalAmount);
        order.setOrderDetails(details);

        // Lưu đơn hàng xuống CSDL
        if (orderDAO.saveOrder(order)) {
            session.removeAttribute("cart"); // Xóa giỏ hàng sau khi đặt thành công
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h2 style='color:green; text-align:center;'>Thanh toán COD thành công! Đơn hàng đang được xử lý.</h2>");
            response.getWriter().write("<div style='text-align:center;'><a href='" + request.getContextPath() + "/home'>Quay về Trang chủ</a></div>");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h2 style='color:red; text-align:center;'>Lỗi hệ thống, chưa thể đặt hàng!</h2>");
        }
    }
}