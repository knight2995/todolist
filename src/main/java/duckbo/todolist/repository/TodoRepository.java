package duckbo.todolist.repository;

import duckbo.todolist.domain.Todo;
import duckbo.todolist.domain.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository implements ITodoRepository{

    private final EntityManager em;

    @Autowired
    public TodoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Todo save(Todo todo) {
        em.persist(todo);
        return todo;
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(em.find(Todo.class, id));
    }

    @Override
    public List<Todo> findAll() {

        return em.createQuery("select todo from Todo todo",Todo.class).getResultList();
    }

    @Override
    public Todo update(Long todoId, Todo param) {

        Todo todo = findById(todoId).get();
        todo.setTitle(param.getTitle());
        todo.setDescription(param.getDescription());
        todo.setEnd(param.isEnd());

        em.flush();
        return todo;
    }

    @Override
    public void delete(Long todoId) {

        Todo todo = findById(todoId).get();
        em.remove(todo);

        em.flush();

    }
}
