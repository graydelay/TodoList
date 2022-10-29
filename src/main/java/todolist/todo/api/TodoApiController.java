package todolist.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todolist.todo.config.auth.LoginUser;
import todolist.todo.dto.TodoRequestDto;
import todolist.todo.dto.UserSessionDto;
import todolist.todo.service.TodoService;
import todolist.todo.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;
    private final UserService userService;

    /* CREATE */
    @PostMapping("/todo")
    public ResponseEntity saveTodo(@RequestBody @Valid TodoRequestDto dto, @LoginUser UserSessionDto user) {
        System.out.println("dto = " + dto);
        System.out.println("user = " + user);
        return ResponseEntity.ok(todoService.save(dto, user));
    }

    /* READ */
    @GetMapping("/todo")
    public ResponseEntity read(@LoginUser UserSessionDto user) {
        return ResponseEntity.ok(todoService.findByUser(user));
    }

    /* UPDATE */
    @PutMapping("/todo/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TodoRequestDto dto) {
        todoService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/todo/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok(id);
    }
}
