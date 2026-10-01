package videostore.com.baitap.dao;

import videostore.com.baitap.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import videostore.com.baitap.utils.JpaUtils_24110153;
import java.util.List;

public class OrderDAO_24110153 {
    
    // Hàm 1: Lưu đơn hàng (Dùng cho Thanh toán COD)
    public boolean saveOrder(Order order) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(order);
            trans.commit();
            return true;
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Hàm 2: Lấy danh sách đơn hàng theo trạng thái (Dùng cho Lịch sử)
    public List<Order> findByUsernameAndStatus(String username, String status) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            String jpql = "SELECT o FROM Order o WHERE o.user.username = :username";
            
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
}