package dev.tferdous.familycashcardapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles a custom CashCardNotFound exception at a global level
 * @author Tasnim Ferdous
 */
@ControllerAdvice
public class CashCardNotFoundExceptionAdvice {

    /**
     * Exception handler method that stores the error message w/ exception msg in a HashMap
     * @param exception CashCardNotFoundException
     * @return map of error and exception msg
     */
    @ResponseBody
    @ExceptionHandler(CashCardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(CashCardNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
