package duckbo.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
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
