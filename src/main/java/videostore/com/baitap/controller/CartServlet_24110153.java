package videostore.com.baitap.controller;

import videostore.com.baitap.entity.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet_24110153 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Nếu action là null thì mặc định chuyển về trang giỏ hàng
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        int videoId = Integer.parseInt(request.getParameter("videoId"));
        
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        int MAX_LIMIT = 5; // Giới hạn mua tối đa 5 bản/1 video

        if ("add".equals(action)) {
            if (cart.containsKey(videoId)) {
                CartItem item = cart.get(videoId);
                // Kiểm tra giới hạn khi cộng dồn
                if (item.getQuantity() < MAX_LIMIT) {
                    item.setQuantity(item.getQuantity() + 1);
                } else {
                    session.setAttribute("cartError", "Bạn chỉ được mua tối đa " + MAX_LIMIT + " bản cho video này!");
                }
            } else {
                CartItem newItem = new CartItem();
                newItem.setVideoId(videoId);
                newItem.setTitle("Video số " + videoId); 
                newItem.setPrice(50000); 
                newItem.setQuantity(1);
                cart.put(videoId, newItem);
            }
        } 
        else if ("update".equals(action)) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (cart.containsKey(videoId)) {
                if (quantity > 0 && quantity <= MAX_LIMIT) {
                    cart.get(videoId).setQuantity(quantity);
                } else if (quantity > MAX_LIMIT) {
                    cart.get(videoId).setQuantity(MAX_LIMIT);
                    session.setAttribute("cartError", "Chỉ được mua tối đa " + MAX_LIMIT + " bản!");
                }
            }
        }
        else if ("remove".equals(action)) {
            cart.remove(videoId);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}