package pro.sky.cursework22.Servise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.cursework22.Model.Question;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceImplTest {
    private final JavaQuestionServiceImpl questionService = new JavaQuestionServiceImpl();
    private List<Question> questions = List.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );
    @BeforeEach
    void setUp() {
        questions.forEach(questionService::addQuestion);
    }
    public static Stream<Arguments> ParamsJavaQuestServiceImplTest() {
        return Stream.of(
                Arguments.of("Q1", "A1"),
                Arguments.of("Q2", "A2"),
                Arguments.of("Q3", "A3"),
                Arguments.of("Q4", "A4"),
                Arguments.of("Q5", "A5")

        );
    }


    @ParameterizedTest
    @MethodSource("ParamsJavaQuestServiceImplTest")
    public void addTest(String question, String answer) {
        Question expected = new Question(question, answer);
        Question actual = questionService.addQuestion(question,answer);
        assertEquals(expected, actual);
    }



    @Test
    void testAddQuestion() {
        Question expected = new Question("Q7", "A7");
        Question actual = questionService.addQuestion(expected);
        assertEquals(expected, actual);
    }

    @Test
       void removeQuestion() {
        Question expected = this.questions.get(0);
        Question actual = questionService.removeQuestion(expected);
        assertThat(actual).isEqualTo(expected);
        }

    @Test
    void getAllQuestion() {
        assertThat(questionService.getAllQuestion()).hasSize(5).containsAll(questions);
    }

    @Test
    void getRandomQuestion() {
        Question actual = questionService.getRandomQuestion();
        assertThat(actual).isIn(questions);
    }
}