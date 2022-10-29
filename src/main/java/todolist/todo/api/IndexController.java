package todolist.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import todolist.todo.config.auth.LoginUser;
import todolist.todo.dto.UserRequestDto;
import todolist.todo.dto.UserSessionDto;
import todolist.todo.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;

    @GetMapping("/")
    public String index(Model model, @LoginUser UserSessionDto user) {
        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        return "index";
    }
}
