package engine.Quiz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SolvedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    Date completedAt;
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User user;
    @OneToOne
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    @JsonProperty(value = "id")
    Quiz quizSolved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuizSolved() {
        return quizSolved;
    }

    public void setQuizSolved(Quiz quizSolved) {
        this.quizSolved = quizSolved;
    }

    @Override
    public String toString() {
        return "SolvedQuiz{" +
                "id=" + id +
                ", completedAt=" + completedAt +
                ", user=" + user +
                ", quizSolved=" + quizSolved +
                '}';
    }
}
