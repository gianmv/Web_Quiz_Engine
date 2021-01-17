package engine.Quiz.service;

import engine.Quiz.model.Quiz;
import engine.Quiz.model.SolvedQuiz;
import engine.Quiz.model.User;
import engine.Quiz.repository.SolvedQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Service
public class SolvedQuizServiceJPA implements SolvedQuizService {

    @Autowired
    SolvedQuizRepository solvedQuizRepository;

    @Override
    public Page<SolvedQuiz> findQuizzesSolvedBy(Principal principal, Pageable p) {
        User user = new User();
        user.setUsername(principal.getName());
        Page<SolvedQuiz> ans = solvedQuizRepository.findByUserOrderByCompletedAtDesc(user,p);
        ans.forEach((solvedQuiz -> {
            solvedQuiz.setId(solvedQuiz.getQuizSolved().getId());
        }));
        return ans;
    }

    public void save(SolvedQuiz solvedQuiz) {
        this.solvedQuizRepository.save(solvedQuiz);
    }
}
