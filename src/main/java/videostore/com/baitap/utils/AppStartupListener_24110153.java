package videostore.com.baitap.utils;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import videostore.com.baitap.entity.User;

// Annotation này giúp Tomcat tự động nhận diện và chạy class này khi khởi động
@WebListener 
public class AppStartupListener_24110153 implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=====================================================");
        System.out.println("KHỞI ĐỘNG HỆ THỐNG: KIỂM TRA DỮ LIỆU ADMIN BAN ĐẦU...");
        
        EntityManager em = JpaUtils_24110153.getEntityManager();
        try {
            em.getTransaction().begin();
            
            // Tìm thử tài khoản admin trong CSDL
            User existingAdmin = em.find(User.class, "admin");
            
            if (existingAdmin == null) {
                // Nếu chưa có thì tiến hành tạo mới
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword("123456"); // Thực tế nên mã hóa MD5/Bcrypt
                adminUser.setFullname("Super Admin");
                adminUser.setEmail("admin@videostore.com");
                adminUser.setAdmin(true);   // Cấp quyền Admin
                adminUser.setActive(true);
                
                em.persist(adminUser);
                System.out.println("=> THÀNH CÔNG: Đã tự động tạo tài khoản Admin mặc định!");
            } else {
                System.out.println("=> BỎ QUA: Tài khoản Admin đã tồn tại trong Database.");
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("=> LỖI: Không thể khởi tạo dữ liệu Admin: " + e.getMessage());
        } finally {
            em.close();
            System.out.println("=====================================================");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Hàm này chạy khi bạn tắt Server (Stop Tomcat)
        // (Có thể để trống)
    }
}