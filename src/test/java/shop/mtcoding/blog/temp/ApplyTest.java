package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.Apply;
import shop.mtcoding.blog.ApplyEnum;

public class ApplyTest {

    @Test
    public void create_test() {
        System.out.println(ApplyEnum.PASS.value);
        System.out.println(ApplyEnum.FAIL.value);
//        apply.setId(1);
//        apply.setName("홍길동");
//        apply.setComId(3);
//        apply.setStatus("합격");

        Apply apply = new Apply(1, "홍길동", 3, ApplyEnum.PASS);
        System.out.println(apply.getStatus());


    }
}
