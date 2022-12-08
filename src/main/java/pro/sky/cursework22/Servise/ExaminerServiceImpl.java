package pro.sky.cursework22.Servise;

import org.springframework.stereotype.Service;
import pro.sky.cursework22.Exception.IncorrectAmountQuestionException;
import pro.sky.cursework22.Model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
        this.random = new Random();
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAllQuestion().size() || amount <= 0) {
            throw new IncorrectAmountQuestionException("Введено неккоректное число заданий");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
