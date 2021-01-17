package engine.Quiz.controller;

import engine.Quiz.model.*;
import engine.Quiz.service.QuizService;
import engine.Quiz.service.SolvedQuizService;
import engine.Quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class QuizController {

    QuizService quizService;
    UserService userService;
    SolvedQuizService solvedQuizService;

    @Autowired
    QuizController(QuizService quizService, SolvedQuizService solvedQuizService, UserService userService) {
        this.quizService = quizService;
        this.solvedQuizService = solvedQuizService;
        this.userService = userService;
    }

    public QuizController() {}

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuiz(@PathVariable("id") int id) {
        Optional<Quiz> optional = quizService.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @GetMapping(path = "/quizzes")
    public Page<Quiz> getQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable p = PageRequest.of(page,pageSize);
        return quizService.findAll(p);
    }

    @PostMapping(path = "/quizzes")
    public Quiz postQuiz(@RequestBody @Valid Quiz body,Principal principal) {
        quizService.save(body, principal.getName());
        return body;
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public Feedback solve(@PathVariable int id, @RequestBody Answer ans, Principal principal) {
        Optional<Quiz> optional = quizService.findById(id);

        if (optional.isPresent()) {
            User user = this.userService.findById(principal.getName()).get();
            SolvedQuiz solvedQuiz = new SolvedQuiz();
            solvedQuiz.setCompletedAt(new Date());
            solvedQuiz.setQuizSolved(optional.get());
            solvedQuiz.setUser(user);

            if (optional.get().checkAnswer(ans)) {
                this.solvedQuizService.save(solvedQuiz);
                return Feedback.OK;
            } else {
                return Feedback.NOT_OK;
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PostMapping(path = "/actuator/shutdown")
    public void shutdown() {

    }

    @GetMapping(path = "/quizzes/completed")
    public Page<SolvedQuiz> solvedQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          Principal principal) {
        Pageable p = PageRequest.of(page,pageSize);
        return solvedQuizService.findQuizzesSolvedBy(principal,p);
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody @Valid User user) {
        this.userService.save(user);
    }

    @DeleteMapping(path = "/quizzes/{id}")
    public ResponseEntity delete(@PathVariable int id, Principal principal){
        return this.quizService.delete(id, principal.getName());
    }
}