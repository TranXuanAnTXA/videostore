package videostore.com.baitap.dao;

import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import videostore.com.baitap.entity.Video;
import videostore.com.baitap.utils.JpaUtils_24110153;

import java.util.List;

public class VideoDAO_24110153 {
    
    // Lấy toàn bộ danh sách Video đang kích hoạt (Active)
    public List<Video> findAll() {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            // JPQL: Truy vấn trên Entity Class, không phải bảng dưới SQL
            String jpql = "SELECT v FROM Video v WHERE v.active = true";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
 // Tìm video theo ID
    public Video findById(int id) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    // Thêm Video mới
    public void insert(Video video) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Cập nhật Video
    public void update(Video video) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Xóa (Ẩn) Video
    public void delete(int id) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.remove(video);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
 // 1. Hàm đếm tổng số lượng Video trong CSDL
    public long count() {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(v) FROM Video v", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    // 2. Hàm lấy danh sách Video có phân trang
    public List<Video> findAll(int page, int pageSize) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            // Tính toán vị trí bắt đầu lấy dữ liệu
            int offset = (page - 1) * pageSize;
            
            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
            query.setFirstResult(offset);     // Bắt đầu từ dòng số mấy
            query.setMaxResults(pageSize);    // Lấy bao nhiêu dòng (6 dòng)
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Video> findByCategory(String categoryId, int page, int pageSize) {

        EntityManager em = JpaUtils_24110153.getEntityManager();

        try {

            TypedQuery<Video> query = em.createQuery(
                    "SELECT v FROM Video v WHERE v.category.categoryId = :cid",
                    Video.class);

            query.setParameter("cid", categoryId);

            query.setFirstResult((page - 1) * pageSize);

            query.setMaxResults(pageSize);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public long countByCategory(String categoryId) {

        EntityManager em = JpaUtils_24110153.getEntityManager();

        try {

            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(v) FROM Video v WHERE v.category.categoryId = :cid",
                    Long.class);

            query.setParameter("cid", categoryId);

            return query.getSingleResult();

        } finally {
            em.close();
        }
    }
    public Map<Integer, Long> countFavorites() {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            Query q = em.createQuery(
                "SELECT f.video.videoId, COUNT(f) " +
                "FROM Favorite f GROUP BY f.video.videoId");

            Map<Integer, Long> result = new HashMap<>();

            for (Object obj : q.getResultList()) {
                Object[] row = (Object[]) obj;
                result.put((Integer) row[0], (Long) row[1]);
            }

            return result;
        } finally {
            em.close();
        }
    }

    public Map<Integer, Long> countShares() {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            Query q = em.createQuery(
                "SELECT s.video.videoId, COUNT(s) " +
                "FROM Share s GROUP BY s.video.videoId");

            Map<Integer, Long> result = new HashMap<>();

            for (Object obj : q.getResultList()) {
                Object[] row = (Object[]) obj;
                result.put((Integer) row[0], (Long) row[1]);
            }

            return result;
        } finally {
            em.close();
        }
    }
}