package videostore.com.baitap.utils;

import java.util.Random;

public class OTPUtil_24110153 {
    
    // Hàm sinh ngẫu nhiên 6 chữ số
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Đảm bảo luôn ra 6 số từ 100000 đến 999999
        return String.valueOf(otp);
    }
}