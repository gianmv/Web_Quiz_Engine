package engine.Quiz.service;

import engine.Quiz.model.Quiz;
import engine.Quiz.model.SolvedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface SolvedQuizService {
    Page<SolvedQuiz> findQuizzesSolvedBy(Principal principal, Pageable page);
    void save(SolvedQuiz solvedQuiz);
}
