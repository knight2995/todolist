package duckbo.todolist.repository;

import duckbo.todolist.domain.Todo;
import duckbo.todolist.domain.TodoDto;

import java.util.List;
import java.util.Optional;

public interface ITodoRepository {

    Todo save(Todo todo);

    Optional<Todo> findById(Long id);

    List<Todo> findAll();

    Todo update(Long todoId, Todo param);

    void delete(Long todoId);

}
