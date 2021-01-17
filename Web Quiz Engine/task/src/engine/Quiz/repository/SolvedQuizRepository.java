package engine.Quiz.repository;

import engine.Quiz.model.Quiz;
import engine.Quiz.model.SolvedQuiz;
import engine.Quiz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Integer> {
    Page<SolvedQuiz> findByUserOrderByCompletedAtDesc(User user, Pageable p);
    void deleteByQuizSolved(Quiz quiz);
}
