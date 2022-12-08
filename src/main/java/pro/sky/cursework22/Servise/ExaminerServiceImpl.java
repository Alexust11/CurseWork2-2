package pro.sky.cursework22.Servise;

import org.springframework.stereotype.Service;
import pro.sky.cursework22.Model.Question;

import java.util.Collection;
@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final

    @Override
    public Collection<Question> getQuestions(int amount) {
        return null;
    }
}
