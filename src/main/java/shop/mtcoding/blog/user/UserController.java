package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.Resp;

import java.util.Map;

// TODO: 미완!! JWT 배우고 응답 완료하기
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @PutMapping("/s/api/user")
    public String update(@Valid @RequestBody UserRequest.UpdateDTO updateDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // TODO: JWT 이후에
        User userPS = userService.회원정보수정(updateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", userPS);
        return "redirect:/";
    }

    @GetMapping("/api/check-username-available/{username}")
    public @ResponseBody Resp<?> checkUsernameAvailable(@PathVariable("username") String username) {
        Map<String, Object> dto = userService.유저네임중복체크(username);
        return Resp.ok(dto);
    }


    @PostMapping("/join")
    // x-www-form / key=value&key=value&key=value -> JoinDTO가 받는 거, @RequestBody를 붙이는 이유 : 클라이언트가 전송한 JSON 데이터를 자바 객체로 변환해주기 위해서
    public @ResponseBody Resp<?> join(@Valid @RequestBody UserRequest.JoinDTO reqDTO, Errors errors) {
        UserResponse.DTO respDTO = userService.회원가입(reqDTO);
        return Resp.ok(respDTO);
    }

    // TODO: JWT 이후에
    @PostMapping("/login")
    public @ResponseBody Resp<?> login(@Valid @RequestBody UserRequest.LoginDTO loginDTO, Errors errors, HttpServletResponse response) {
        //System.out.println(loginDTO);
        UserResponse.TokenDTO respDTO = userService.로그인(loginDTO);
        return Resp.ok(respDTO);
    }

    // TODO: JWT 이후에
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login-form";
    }
}
