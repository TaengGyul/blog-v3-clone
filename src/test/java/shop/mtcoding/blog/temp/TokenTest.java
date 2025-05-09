package shop.mtcoding.blog.temp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TokenTest {

    @Test
    public void create_test() {
        User user = User.builder()
                .id(1)
                .username("ssar")
                .password("$2a$10$EGXmhiF/hM0CEjuEP/gw/Ol/zFVdmgbrr1BE2xkf5CIRgtI5IE6B6")
                .email("ssar@nate.com")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        String jwt = JWT.create()
                .withSubject("blogv3") // 쓸 일이 없어서 아무거나 적어도 됨 (제목)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // (현재시간)
                .withClaim("id", user.getId()) // 유저를 삭별할 수 있는거 비밀번호는 민감한 정보라 넣지 않음
                .withClaim("username", user.getUsername()) // 유저를 삭별할 수 있는거 비밀번호는 민감한 정보라 넣지 않음
                .sign(Algorithm.HMAC256("metacoding")); // HMAC 단방향 해시 알고리즘, 해시할 때 시크릿 값

        // 198 156 236 87 42 53 186 254 56 151 169 7 107 178 5 197 147 172 56 100 145 97 133 14 17 46 135 193 73 199 201 144
        // xpzsVyo1uv44l6kHa7IFxZOsOGSRYYUOES6HwUnHyZA
        System.out.println(jwt);
    }

    @Test
    public void verify_test() {
        // 2025.05.09. 11:50까지 유효
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibG9ndjMiLCJpZCI6MSwiZXhwIjoxNzQ2NzU5MDMyLCJ1c2VybmFtZSI6InNzYXIifQ.oU2g1aNZ3_nzQ2FVaxZgjlbsQjot7ZnwIuV2mzq0yiM";

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metadocing")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();

        System.out.println(id);
        System.out.println(username);

        User user = User.builder()
                .id(id)
                .username(username)
                .build();
    }
}
