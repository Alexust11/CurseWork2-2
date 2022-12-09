package pro.sky.cursework22.Servise;

import org.springframework.stereotype.Service;
import pro.sky.cursework22.Exception.QuestionAlreadyAddedException;
import pro.sky.cursework22.Exception.QuestionNotFoundException;
import pro.sky.cursework22.Model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Service
public class JavaQuestionServiceImpl implements QuestionService {
        private final Set<Question> questionList;
        private final Random random;

    public JavaQuestionServiceImpl() {
        this.random = new Random();
        this.questionList = new HashSet<>();
    }



    @Override
    public Question addQuestion(String question, String answer) {
        Question newQuest=new Question(question,answer);
        questionList.add(newQuest);
        return newQuest;
    }

    @Override
    public Question addQuestion(Question question) {
        if (!questionList.add(question)) {
            throw new QuestionAlreadyAddedException("такой вопрос есть");
        }

        return question;
    }

    @Override
    public Question removeQuestion(Question question) {
        if (!questionList.remove(question)) {
            throw new QuestionNotFoundException("Такой вопрос не найден");
        }

        return question;
    }

    @Override
    public Collection<Question> getAllQuestion() {
        return questionList;
    }

    @Override
    public Question getRandomQuestion() {
        return questionList.stream().skip(random.nextInt(questionList.size())).findAny().orElse(null);
    }
}
