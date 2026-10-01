package videostore.com.baitap.utils;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtil_24110153 {
    
    // THAY BẰNG EMAIL VÀ MẬT KHẨU ỨNG DỤNG GMAIL CỦA BẠN
    private static final String MY_EMAIL = "tranxuanansuper@gmail.com"; 
    private static final String MY_PASSWORD = "ravcwlphvqpzevt"; 

    public static boolean sendMail(String toEmail, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Server của Gmail
        props.put("mail.smtp.port", "587"); // Cổng TLS
        props.put("mail.smtp.auth", "true"); // Bật xác thực
        props.put("mail.smtp.starttls.enable", "true"); // Bật mã hóa TLS

        // Đăng nhập vào Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MY_EMAIL, MY_PASSWORD);
            }
        });

        try {
            // Tạo bức thư
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MY_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            
            // Nội dung thư (Hỗ trợ HTML để thư đẹp hơn)
            message.setContent(body, "text/html; charset=utf-8");

            // Bấm nút gửi
            Transport.send(message);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm main để test chạy thử ngay mà không cần bật Web Server
    public static void main(String[] args) {
        String testEmail = "email-bat-ky@gmail.com"; 
        String otp = OTPUtil_24110153.generateOTP();
        
        System.out.println("======================================");
        System.out.println("GIẢ LẬP GỬI EMAIL THÀNH CÔNG!");
        System.out.println("Email nhận: " + testEmail);
        System.out.println("Mã OTP của bạn là: " + otp);
        System.out.println("======================================");
    }
}