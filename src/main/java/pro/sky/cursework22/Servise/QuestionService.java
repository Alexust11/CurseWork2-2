package pro.sky.cursework22.Servise;

import pro.sky.cursework22.Model.Question;

import java.util.Collection;

public interface QuestionService {
    Question addQuestion(String question, String answer);

    Question addQuestion(Question question);

    Question removeQuestion(Question question);

    Collection<Question> getAllQuestion();

    Question getRandomQuestion();
}
