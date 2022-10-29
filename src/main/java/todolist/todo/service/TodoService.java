package todolist.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.todo.domain.Todo;
import todolist.todo.domain.TodoStatus;
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
        User user = userRepository.findByNickname(userSessionDto.getNickname());

        if (dto.getStatus() == null) {
            dto.setStatus(TodoStatus.TODO);
        }
        dto.setUser(user);
        Todo todo = dto.toEntity();
        todoRepository.save(todo);

        return todo.getId();
    }

    public Object findByUser(UserSessionDto userSessionDto) {
        User user = userRepository.findByNickname(userSessionDto.getNickname());
        List<Todo> todos = todoRepository.findByUserOrderById(user);
        List<TodoResponseDto> dtos = new ArrayList<>();
        for (Todo todo : todos) {
            dtos.add(new TodoResponseDto(todo));
        }
        return dtos;
    }

    /* UPDATE */
    @Transactional
    public void update(Long id, TodoRequestDto dto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 Todo가 존재하지 않습니다. id=" + id));

        todo.update(dto.getTitle(), dto.getDescribe(), dto.getStatus());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 Todo가 존재하지 않습니다. id=" + id));

        todoRepository.delete(todo);
    }
}
