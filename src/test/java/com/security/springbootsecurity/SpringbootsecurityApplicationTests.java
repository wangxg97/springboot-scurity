package com.security.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class SpringbootsecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testBCrypt(){
        //对密码进行加密
        System.out.println(BCrypt.hashpw("123",BCrypt.gensalt()));
        //bcccbaaa-4ace-4f09-9060-770ae1a27bf5
        System.out.println(BCrypt.checkpw("123", "$2a$10$tZ6NMyEcWQ1OtAvw0AYvs.cXFtirj2zOfakOFpSZ0H1QlzFhPGAIq"));
    }
}
