package duckbo.todolist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDto {

    private String title;
    private String description;
    private boolean end;

}
