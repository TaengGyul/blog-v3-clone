package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashTest {

    // $2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6
    // $2a$10$FLPJzFPtXvY.oHntyaiJseWviOwKNK0sqatjsTuHat7qngdM4Jz/6
    @Test
    public void encode_test() {
        String password = "1234";

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // solt 붙이는 이유 : 비밀번호가 뚫려도 확인할 수 없어서
        System.out.println(encPassword);
    }


    // $2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6
    // $2a$10$FLPJzFPtXvY.oHntyaiJseWviOwKNK0sqatjsTuHat7qngdM4Jz/6
    @Test
    public void decode_test() {
        String dbPassword = "$2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6";
        String password = "1234";

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // solt 붙이는 이유 : 비밀번호가 뚫려도 확인할 수 없어서
        if (encPassword.equals(dbPassword)) {
            System.out.println("비밀번호가 같아요");
        } else {
            System.out.println("비밀번호가 달라요");
        }
    }

    // $2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6
    // $2a$10$FLPJzFPtXvY.oHntyaiJseWviOwKNK0sqatjsTuHat7qngdM4Jz/6
    @Test
    public void decodeV2_test() {
        String dbPassword = "$2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6";
        String password = "1234";

        Boolean isSame = BCrypt.checkpw(password, dbPassword);
        System.out.println(isSame);
    }
}
