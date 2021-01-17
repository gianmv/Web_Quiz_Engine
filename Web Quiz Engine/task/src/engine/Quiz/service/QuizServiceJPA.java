package engine.Quiz.service;

import engine.Quiz.model.Quiz;
import engine.Quiz.model.User;
import engine.Quiz.repository.QuizRepository;
import engine.Quiz.repository.SolvedQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceJPA implements QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    SolvedQuizRepository solvedQuizRepository;

    @Override
    public Optional<Quiz> findById(int id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public void save(Quiz quiz, String email) {
        User user = new User();
        user.setUsername(email);
        quiz.setUser(user);
        quizRepository.save(quiz);
    }

    @Override
    @Transactional
    public ResponseEntity delete(int quizId, String email) {
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);
        System.out.println(this.quizRepository.findById(quizId).orElse(null));
        ResponseEntity responseEntity;
        if (quiz.isPresent()) {
            String userEmail = quiz.get().getUser().getUsername();
            if (email.equals(userEmail)) {
                this.solvedQuizRepository.deleteByQuizSolved(quiz.get());
                System.out.println(this.solvedQuizRepository.findAll());
                this.quizRepository.deleteById(quizId);
                System.out.println(this.quizRepository.findById(quizId).orElse(null));
                responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity(HttpStatus.FORBIDDEN);
            }

        } else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    @Override
    public Page<Quiz> findAll(Pageable p) {
        return quizRepository.findAll(p);
    }


}
