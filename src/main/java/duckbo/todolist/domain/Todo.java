package duckbo.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean end;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.end = false;
    }

    public Todo() {
        this.end = false;
    }
}
