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
import todolist.todo.config.auth.LoginUser;
import todolist.todo.domain.User;
import todolist.todo.dto.UserRequestDto;
import todolist.todo.dto.UserResponseDto;
import todolist.todo.dto.UserSessionDto;
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
    @PutMapping("/auth/user/{id}")
    public UserResponseDto modify(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        UserResponseDto modify = userService.modify(id, dto);

        return modify;
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/auth/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
