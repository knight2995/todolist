package duckbo.todolist.service;

import duckbo.todolist.domain.Todo;
import duckbo.todolist.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private ITodoRepository todoRepository;

    public TodoService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo findOne(Long todoId) {
        return todoRepository.findById(todoId).get();
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

    public void remove(Long todoId) {

        todoRepository.delete(todoId);
    }
}
