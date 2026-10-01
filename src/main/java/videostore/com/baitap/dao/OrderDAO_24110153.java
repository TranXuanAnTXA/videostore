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

    // Hàm 2: Lấy danh sách đơn hàng theo trạng thái (Dùng cho OrderHistoryServlet)
    public List<Order> findByUsernameAndStatus(String username, String status) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            // Viết câu lệnh JPQL lọc theo Username của bảng User
            String jpql = "SELECT o FROM Order o WHERE o.user.username = :username";
            
            // Nếu có chọn trạng thái (khác ALL) thì nối thêm điều kiện WHERE
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                jpql += " AND o.status = :status";
            }
            jpql += " ORDER BY o.orderDate DESC"; // Sắp xếp cho đơn hàng mới nhất lên trên cùng

            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("username", username);
            
            // Truyền tham số trạng thái vào câu query
            if (status != null && !status.isEmpty() && !status.equals("ALL")) {
                query.setParameter("status", status);
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}