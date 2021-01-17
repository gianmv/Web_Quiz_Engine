package engine.Quiz.service;

import engine.Quiz.model.Quiz;
import engine.Quiz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    public Optional<Quiz> findById(int id);
    public List<Quiz> findAll();
    public void save(Quiz quiz, String email);
    public ResponseEntity delete(int quizId, String email);

    public Page<Quiz> findAll(Pageable p);
}
