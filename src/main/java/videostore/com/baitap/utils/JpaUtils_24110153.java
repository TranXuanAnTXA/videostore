package videostore.com.baitap.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils_24110153 {
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        // Chỉ tạo Factory 1 lần duy nhất để tối ưu hiệu suất
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("VideoStorePU");
        }
        return factory.createEntityManager();
    }
}