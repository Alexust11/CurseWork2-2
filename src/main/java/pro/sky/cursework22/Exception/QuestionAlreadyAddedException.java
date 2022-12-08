package pro.sky.cursework22.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyAddedException extends RuntimeException{
    public QuestionAlreadyAddedException(String text) {
            super(text);
    }
}
