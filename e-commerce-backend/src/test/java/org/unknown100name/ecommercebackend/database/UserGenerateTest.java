package org.unknown100name.ecommercebackend.database;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.unknown100name.ecommercebackend.ECommerceBackendApplication;
import org.unknown100name.ecommercebackend.controller.UserController;
import org.unknown100name.ecommercebackend.pojo.vo.UserRegisterParam;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author unknown100name
 * @since 2022.03.15
 */
@SpringBootTest(classes = ECommerceBackendApplication.class)
public class UserGenerateTest {

    @Resource
    private UserController userController;

    Random random = new Random();

    @Test
    public void test(){

        for (int i = 0; i < 100; i++) {
            userController.register(new UserRegisterParam(
                    "买家用户" + i,
                    "123456",
                    getRandomPhone(),
                    1,
                    random.nextInt(2),
                    "邹皓杰" + i,
                    getRandomIdCard(),
                    ""
            ), null);
        }
    }

    public String getRandomPhone(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public String getRandomIdCard(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


}
