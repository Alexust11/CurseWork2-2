package pro.sky.cursework22.Servise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.cursework22.Exception.IncorrectAmountQuestionException;
import pro.sky.cursework22.Model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionServiceImpl javaQuestionServiceImpl;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        Collection<Question> questions = Stream.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5")
        ).collect(Collectors.toSet());

        when(javaQuestionServiceImpl.getAllQuestion()).thenReturn(questions);
    }
    public static Stream<Arguments> negativeparams() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(6),
                Arguments.of(10)
        );
    }
    @ParameterizedTest
    @MethodSource("negativeparams")
    public void getQuestionsNegativeTest(int incorrectAmount) {
        assertThatExceptionOfType(IncorrectAmountQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestions(incorrectAmount));
    }

    @Test
    public void getQuestionsTest() {
        List<Question> questions = new ArrayList<>(javaQuestionServiceImpl.getAllQuestion());

        when(javaQuestionServiceImpl.getRandomQuestion()).thenReturn(
                questions.get(0),
                questions.get(1),
                questions.get(0),
                questions.get(2),
                questions.get(1)
        );

        assertThat(examinerService.getQuestions(3)).containsExactly(questions.get(0), questions.get(1), questions.get(2));

        when(javaQuestionServiceImpl.getRandomQuestion()).thenReturn(
                questions.get(0),
                questions.get(1),
                questions.get(2),
                questions.get(4),
                questions.get(2),
                questions.get(3)
        );

        assertThat(examinerService.getQuestions(5))
                .containsExactly(questions.get(0), questions.get(1), questions.get(2),questions.get(4),questions.get(3));

    }

}