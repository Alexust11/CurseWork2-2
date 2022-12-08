package pro.sky.cursework22.Servise;

import pro.sky.cursework22.Model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
