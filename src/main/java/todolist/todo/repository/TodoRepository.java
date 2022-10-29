package todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.todo.domain.Todo;
import todolist.todo.domain.User;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserOrderById(User user);

    List<Todo> findFirstByUserOrderByIdDesc(User user);
}
