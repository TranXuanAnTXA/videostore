package videostore.com.baitap.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
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
   
        // Lấy tất cả danh sách người dùng
        public List<User> findAll() {
            EntityManager em = JpaUtils_24110153.getEntityManager();
            try {
                TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
                return query.getResultList();
            } finally {
                em.close();
            }
        }

        // Cập nhật thông tin User (hoặc phân quyền Admin)
        public boolean update(User user) {
            EntityManager em = JpaUtils_24110153.getEntityManager();
            EntityTransaction trans = em.getTransaction();
            try {
                trans.begin();
                em.merge(user);
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

        // Xóa user
        public boolean delete(String username) {
            EntityManager em = JpaUtils_24110153.getEntityManager();
            EntityTransaction trans = em.getTransaction();
            try {
                trans.begin();
                User user = em.find(User.class, username);
                if (user != null) {
                    em.remove(user);
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
    }