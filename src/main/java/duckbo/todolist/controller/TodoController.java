package duckbo.todolist.controller;


import duckbo.todolist.domain.Todo;
import duckbo.todolist.domain.TodoDto;
import duckbo.todolist.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
@Transactional
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String todos(Model model) {

        List<Todo> todos = todoService.findTodos();
        model.addAttribute("todos", todos);

        return "todos";
    }

    @GetMapping("/todos/{todoId}")
    public String todo(@PathVariable Long todoId, Model model) {

        Todo todo = todoService.findOne(todoId);
        model.addAttribute("todo", todo);

        return "todo";

    }

    @GetMapping("/todos/add")
    public String addForm() {
        return "addForm";
    }

    @PostMapping("/todos/add")
    public String addTodo(@ModelAttribute TodoDto todoDto, RedirectAttributes redirectAttributes) {

        Todo todo = new Todo(todoDto.getTitle(), todoDto.getDescription());

        Todo savedTodo = todoService.add(todo);
        redirectAttributes.addAttribute("todoId", savedTodo.getId());
        return "redirect:/todos";
    }


    @PostMapping("/todos/end/{todoId}")
    public String endingProcess(@PathVariable Long todoId) {

        Todo todo = todoService.ending(todoId);

        return "redirect:/todos";
    }

    @PostMapping("/todos/revertEnd/{todoId}")
    public String revertEnd(@PathVariable Long todoId) {

        Todo todo = todoService.revertEnding(todoId);

        return "redirect:/todos";
    }

    @DeleteMapping("/todos/delete/{todoId}")
    public String deleteTodo(@PathVariable Long todoId) {

        todoService.remove(todoId);

        return "redirect:/todos";
    }

    @GetMapping("/todos/{todoId}/edit")
    public String editForm(@PathVariable Long todoId, Model model) {

        Todo todo = todoService.findOne(todoId);
        model.addAttribute("todo", todo);

        return "editForm";
    }

    @PostMapping("/todos/{todoId}/edit")
    public String edit(@PathVariable Long todoId, @ModelAttribute TodoDto todoDto, RedirectAttributes redirectAttributes) {

        Todo todo = todoService.findOne(todoId);
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());

        redirectAttributes.addAttribute("todoId", todo.getId());

        return "redirect:/todos/{todoId}";
    }


}
