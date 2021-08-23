package duckbo.todolist.service;

import duckbo.todolist.domain.Todo;
import duckbo.todolist.domain.TodoDto;
import duckbo.todolist.repository.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private ITodoRepository todoRepository;

    @Autowired
    public TodoService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDto> findTodos() {

        return todoRepository.findAll().stream()
                .map(TodoDto::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TodoDto> findOne(Long todoId) {

        return Optional.ofNullable(TodoDto.toDto(todoRepository.findById(todoId).get()));
    }

    public TodoDto add(TodoDto todoDto) {

        return TodoDto.toDto(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    public TodoDto ending(Long todoId) {

        Todo todo = todoRepository.findById(todoId).get();
        todo.setEnd(true);
        todoRepository.update(todo.getId(),todo);
        return TodoDto.toDto(todo);
    }
    public TodoDto revertEnding(Long todoId) {

        Todo todo = todoRepository.findById(todoId).get();
        todo.setEnd(false);
        todoRepository.update(todo.getId(),todo);
        return TodoDto.toDto(todo);
    }

    public void update(Long todoId, TodoDto todoDto) {
        todoRepository.update(todoId, TodoDto.toEntity(todoDto));

    }

    public void remove(Long todoId) throws NoSuchElementException{

        try {
            todoRepository.delete(todoId);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

}
