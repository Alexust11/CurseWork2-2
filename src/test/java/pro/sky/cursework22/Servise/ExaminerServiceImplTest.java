package pro.sky.cursework22.Servise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.cursework22.Exception.IncorrectAmountQuestionException;
import pro.sky.cursework22.Model.Question;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionServiceImpl javaQuestionServiceImpl;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestionsDistinctTest() {
        List<Question> questionList = List.of(
                new Question("Q1", "A1"),
                new Question("Q1", "A1"),
                new Question("Q1", "A1")
        );
        when(javaQuestionServiceImpl.getAllQuestion()).thenReturn(questionList);
        when(javaQuestionServiceImpl.getRandomQuestion()).thenReturn(
                questionList.get(0),
                questionList.get(1)
        );
        assertThat(examinerService.getQuestions(2)).hasSize(2)
                .containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void IncorrectAmountOfQuestionsException() {
        when(javaQuestionServiceImpl.getAllQuestion()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> examinerService.getQuestions(111))
                .isInstanceOf(IncorrectAmountQuestionException.class);
    }

}