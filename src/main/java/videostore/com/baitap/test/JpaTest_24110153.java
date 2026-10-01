package videostore.com.baitap.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaTest_24110153 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("videostore");
        EntityManager em = emf.createEntityManager();
        System.out.println("✅ JPA ĐÃ KẾT NỐI VÀ TẠO BẢNG THÀNH CÔNG!");
        em.close();
        emf.close();
    }
}