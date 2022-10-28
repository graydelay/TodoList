package todolist.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.*;
import todolist.todo.domain.User;
import todolist.todo.dto.UserRequestDto;
import todolist.todo.dto.UserResponseDto;
import todolist.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    /**
     * 회원 가입
     */
    @PostMapping("/auth/joinProc")
    public UserResponseDto joinProc(@Valid @RequestBody UserRequestDto dto) {
        userService.join(dto);

        User user = dto.toEntity();
        return new UserResponseDto(user);
    }

    /**
     * 로그인
     */
    @GetMapping("/auth/login")
    public void login(@RequestParam(value = "error", required = false) String error,
                      @RequestParam(value = "exception", required = false) String exception,
                      Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/auth/user")
    public UserResponseDto modify(@RequestBody UserRequestDto dto) {
        UserResponseDto modify = userService.modify(dto);

        return modify;
    }
}
