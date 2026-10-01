package videostore.com.baitap.dao;

import jakarta.persistence.EntityManager;
import videostore.com.baitap.entity.User;
import videostore.com.baitap.utils.JpaUtils_24110153;

public class UserDAO_24110153 {
    
    // Hàm thêm 1 User mới vào CSDL
    public void insert(User user) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user); // JPA tự động sinh câu lệnh INSERT INTO
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Nếu lỗi thì hoàn tác
            throw e;
        } finally {
            em.close();
        }
    }

    // Hàm tìm kiếm User theo Username (Dùng cho đăng nhập và check trùng)
    public User findById(String username) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            return em.find(User.class, username); // JPA tự sinh SELECT * FROM Users WHERE Username = ?
        } finally {
            em.close();
        }
    }
}