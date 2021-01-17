package engine.Quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int id;
    @NotBlank(message = "Title is mandatory")
    protected String title;
    @NotBlank(message = "Text is mandatory")
    protected String text;

    @NotNull (message = "Options mustn't be null.")
    @Size (min = 2, message = "The quiz must have at lest 2 options.")
    @ElementCollection
    protected List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    protected List<Integer> answer;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected User user;

    public Quiz() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


    public List<Integer> getAnswer() {
        return this.answer == null ? new ArrayList<Integer>() : this.answer;
    }


    public void setAnswer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }

    public boolean checkAnswer(Answer possible) {
        return possible.getAnswer().equals(getAnswer());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
