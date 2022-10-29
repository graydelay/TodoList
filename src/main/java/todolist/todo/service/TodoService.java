package todolist.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.todo.domain.Todo;
import todolist.todo.domain.User;
import todolist.todo.dto.TodoRequestDto;
import todolist.todo.dto.TodoResponseDto;
import todolist.todo.dto.UserSessionDto;
import todolist.todo.repository.TodoRepository;
import todolist.todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(TodoRequestDto dto, UserSessionDto userSessionDto) {
        User user = null;
        if (userSessionDto != null) {
            user = userRepository.findByNickname(userSessionDto.getNickname());
        } else {
            user = userRepository.findByNickname("lee");
        }
        dto.setUser(user);
        Todo todo = dto.toEntity();
        todoRepository.save(todo);

        return todo.getId();
    }

    public Object findByUser(UserSessionDto userSessionDto) {
        User user = null;
        if (userSessionDto != null) {
            user = userRepository.findByNickname(userSessionDto.getNickname());
        } else {
            user = userRepository.findByNickname("lee");
        }
        List<Todo> todos = todoRepository.findByUserOrderById(user);
        List<TodoResponseDto> dtos = new ArrayList<>();
        for (Todo todo : todos) {
            dtos.add(new TodoResponseDto(todo));
        }
        return dtos;
    }
}
