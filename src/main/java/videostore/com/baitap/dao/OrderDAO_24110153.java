package videostore.com.baitap.dao;

import videostore.com.baitap.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import videostore.com.baitap.utils.JpaUtils_24110153;
import java.util.List;

public class OrderDAO_24110153 {
    
    // Hàm 1: Lưu đơn hàng và Trừ số lượng tồn kho (Dùng cho CheckoutServlet)
    public boolean saveOrder(Order order) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            
            // 1. Lưu đơn hàng và chi tiết đơn hàng xuống Database trước
            em.persist(order);
            
            // 2. Duyệt qua từng sản phẩm có trong đơn hàng vừa đặt để tiến hành trừ kho
            for (videostore.com.baitap.entity.OrderDetail detail : order.getOrderDetails()) {
                // Tìm Video thật trong DB bằng ID
                videostore.com.baitap.entity.Video v = em.find(videostore.com.baitap.entity.Video.class, detail.getVideo().getVideoId());
                
                if (v != null) {
                    // Lấy số lượng hiện tại trong kho trừ đi số lượng người dùng vừa mua
                    int newQuantity = v.getQuantity() - detail.getQuantity();
                    
                    // Đảm bảo không bị âm số lượng (phòng hờ lỗi logic)
                    if (newQuantity < 0) newQuantity = 0; 
                    
                    v.setQuantity(newQuantity);
                    em.merge(v); // Cập nhật lại số lượng mới vào DB
                }
            }
            
            trans.commit(); // Hoàn tất giao dịch (Lưu đơn + Trừ kho thành công)
            return true;
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback(); // Nếu có lỗi xảy ra thì hoàn tác lại toàn bộ
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Order> findByUsernameAndStatus(String username, String status) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            // Thêm JOIN FETCH o.orderDetails để nạp sẵn chi tiết đơn hàng, tránh lỗi LazyInitializationException
            String jpql = "SELECT DISTINCT o FROM Order o JOIN FETCH o.orderDetails WHERE o.user.username = :username";
            
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                jpql += " AND o.status = :status";
            }
            jpql += " ORDER BY o.orderDate DESC"; 

            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("username", username);
            
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                query.setParameter("status", status);
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
 // Hàm cập nhật trạng thái đơn hàng
    public boolean updateOrderStatus(int orderId, String newStatus) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                order.setStatus(newStatus);
                em.merge(order);
                trans.commit();
                return true;
            }
            trans.rollback();
            return false;
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
 // Hàm riêng biệt cho Admin lấy toàn bộ đơn hàng (có lọc trạng thái hoặc lấy tất cả)
    public List<Order> findOrdersForAdmin(String status) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            // Không có điều kiện theo username, lấy toàn bộ đơn hàng của tất cả user
            String jpql = "SELECT DISTINCT o FROM Order o JOIN FETCH o.orderDetails";
            
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                jpql += " WHERE o.status = :status";
            }
            jpql += " ORDER BY o.orderDate DESC";

            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                query.setParameter("status", status);
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }}