package duckbo.todolist.service;

import duckbo.todolist.domain.Todo;
import duckbo.todolist.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TodoService {

    private ITodoRepository todoRepository;

    public TodoService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findOne(Long todoId) {
        return todoRepository.findById(todoId);
    }

    public Todo add(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo ending(Long todoId) {

        Todo todo = todoRepository.findById(todoId).get();
        todo.setEnd(true);
        todoRepository.update(todo.getId(),todo);
        return todo;
    }
    public Todo revertEnding(Long todoId) {

        Todo todo = todoRepository.findById(todoId).get();
        todo.setEnd(false);
        todoRepository.update(todo.getId(),todo);
        return todo;
    }
    public void remove(Long todoId) throws NoSuchElementException{

        try {
            todoRepository.delete(todoId);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

}
