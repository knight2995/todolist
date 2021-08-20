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
import java.util.NoSuchElementException;
import java.util.Optional;

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

        Todo todo = todoService.findOne(todoId).get();
        model.addAttribute("todo", todo);

        return "todo";

    }

    @GetMapping("/todos/add")
    public String addForm() {
        return "addForm";
    }

    @PostMapping("/todos/add")
    public String addTodo(@ModelAttribute TodoDto todoDto, RedirectAttributes redirectAttributes) {

        //Title 공백 및 길이 체크
        String title = todoDto.getTitle();
        if(title.trim().length() == 0 || title.length() > 20)
        {
            redirectAttributes.addAttribute("error", "잘 못된 입력입니다.");
        }
        else
        {
            Todo todo = new Todo(todoDto.getTitle(), todoDto.getDescription());

            Todo savedTodo = todoService.add(todo);
        }

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
    public String deleteTodo(@PathVariable Long todoId, RedirectAttributes redirectAttributes) {

        try {
            todoService.remove(todoId);
        } catch (NoSuchElementException e) {
            redirectAttributes.addAttribute("error", "이미 삭제된 todo 입니다.");
            return "redirect:/todos";
        }


        return "redirect:/todos";
    }

    @GetMapping("/todos/{todoId}/edit")
    public String editForm(@PathVariable Long todoId, Model model, RedirectAttributes redirectAttributes) {

        Optional<Todo> todoOptional = todoService.findOne(todoId);

        if (todoOptional.isPresent()) {
            model.addAttribute("todo", todoOptional.get());
            return "editForm";
        } else {
            redirectAttributes.addAttribute("error", "이미 삭제된 todo 입니다.");
            return "redirect:/todos";
        }
    }

    @PostMapping("/todos/{todoId}/edit")
    public String edit(@PathVariable Long todoId, @ModelAttribute TodoDto todoDto, RedirectAttributes redirectAttributes) {

        String title = todoDto.getTitle();
        if(title.trim().length() == 0 || title.length() > 20)
        {
            redirectAttributes.addAttribute("error", "잘 못된 입력입니다.");
        }
        else
        {
            Optional<Todo> todoOptional = todoService.findOne(todoId);
            if (todoOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setTitle(todoDto.getTitle());
                todo.setDescription(todoDto.getDescription());
            } else {
                redirectAttributes.addAttribute("error", "이미 삭제된 todo 입니다.");
            }
        }

        return "redirect:/todos";
    }

}
