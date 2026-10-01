package videostore.com.baitap.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import videostore.com.baitap.entity.Category;
import videostore.com.baitap.utils.JpaUtils_24110153;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAO_24110153 {
    public List<Category> findAll() {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Category findById(String id) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public void insert(Category category) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Category category) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String id) {
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
  
    public Map<String, Long> countVideosByCategory() {

        EntityManager em = JpaUtils_24110153.getEntityManager();

        try {

            TypedQuery<Object[]> query = em.createQuery(

                    "SELECT v.category.categoryId, COUNT(v) " +
                    "FROM Video v GROUP BY v.category.categoryId",

                    Object[].class);

            List<Object[]> list = query.getResultList();

            Map<String, Long> map = new HashMap<>();

            for(Object[] row : list){

                map.put((String)row[0], (Long)row[1]);

            }

            return map;

        } finally {

            em.close();

        }

    }
}