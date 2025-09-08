package com.example.demo.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12); // cost = 12

        // ✅ 1) 평문 → 해시 변환
        String rawPassword = "password";  // 확인하고 싶은 비밀번호
        String encoded = encoder.encode(rawPassword);
        System.out.println("생성된 해시 = " + encoded);

        // ✅ 2) DB 저장된 해시와 비교
        String dbHash = "$2a$12$34n2GHTdh8ecPa/d.mAV.usG4u5nFQ86Tj6rPaZkjXVz5PoipvymS";
        boolean matches = encoder.matches(rawPassword, dbHash);

        System.out.println("DB 해시와 일치? " + matches);
    }
}
