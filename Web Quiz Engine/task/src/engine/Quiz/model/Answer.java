package engine.Quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answer {

    List<Integer> answer;

    Answer() {}

    public List<Integer> getAnswer() {
        return this.answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer=" + answer +
                '}';
    }
}
