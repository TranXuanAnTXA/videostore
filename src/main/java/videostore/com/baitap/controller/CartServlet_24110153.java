package videostore.com.baitap.controller;

import videostore.com.baitap.dao.VideoDAO_24110153;
import videostore.com.baitap.entity.CartItem;
import videostore.com.baitap.entity.Video;
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

 // Thêm khai báo VideoDAO ở ngay dưới dòng public class CartServlet
    private final VideoDAO_24110153 videoDAO = new VideoDAO_24110153();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        int videoId = Integer.parseInt(request.getParameter("videoId"));
        
        // 1. Lấy Video từ Database để check tồn kho
        Video video = videoDAO.findById(videoId);
        if (video == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        
        int MAX_LIMIT = video.getQuantity(); // Lấy số lượng thực tế của phim này từ CSDL

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        if ("add".equals(action)) {
            if (cart.containsKey(videoId)) {
                CartItem item = cart.get(videoId);
                if (item.getQuantity() < MAX_LIMIT) {
                    item.setQuantity(item.getQuantity() + 1);
                } else {
                    session.setAttribute("cartError", "Phim '" + video.getTitle() + "' chỉ còn " + MAX_LIMIT + " bản trong kho!");
                }
            } else {
                if (MAX_LIMIT > 0) {
                    CartItem newItem = new CartItem();
                    newItem.setVideoId(videoId);
                    newItem.setTitle(video.getTitle()); // Lấy tên thật từ DB luôn
                    newItem.setPrice(50000); // Giá bạn có thể lấy từ DB nếu có cột Price
                    newItem.setQuantity(1);
                    newItem.setStock(MAX_LIMIT); // Truyền stock sang cho JSP
                    cart.put(videoId, newItem);
                } else {
                    session.setAttribute("cartError", "Phim này đã hết hàng!");
                }
            }
        } 
        else if ("update".equals(action)) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (cart.containsKey(videoId)) {
                if (quantity > 0 && quantity <= MAX_LIMIT) {
                    cart.get(videoId).setQuantity(quantity);
                } else if (quantity > MAX_LIMIT) {
                    cart.get(videoId).setQuantity(MAX_LIMIT);
                    session.setAttribute("cartError", "Phim này chỉ còn tối đa " + MAX_LIMIT + " bản!");
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