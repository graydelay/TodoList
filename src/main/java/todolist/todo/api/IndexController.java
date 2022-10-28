package todolist.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import todolist.todo.config.auth.LoginUser;
import todolist.todo.dto.UserSessionDto;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @LoginUser UserSessionDto user) {
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        return "index";
    }
}
