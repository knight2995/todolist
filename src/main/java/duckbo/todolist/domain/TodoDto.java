package duckbo.todolist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private String description;
    private boolean end;

    public static TodoDto toDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setEnd(todo.isEnd());

        return todoDto;
    }

    public static Todo toEntity(TodoDto todoDto) {
        return new Todo(todoDto.getId(),todoDto.getTitle(),todoDto.getDescription(),todoDto.isEnd());
    }

}
