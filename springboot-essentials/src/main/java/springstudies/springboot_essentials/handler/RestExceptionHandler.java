package springstudies.springboot_essentials.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springstudies.springboot_essentials.exception.BadRequestException;
import springstudies.springboot_essentials.exception.BadRequestExceptionDetails;
import springstudies.springboot_essentials.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception. Check Documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        return new ResponseEntity<>(
//                BadRequestExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Bad Request Exception. Check Documentation")
//                        .details(ex.getMessage())
//                        .developerMessage(badRequestException.getClass().getName())
//                        .build(), HttpStatus.BAD_REQUEST);
//    }
}
