package hello.utils;

import com.google.api.Http;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*Provides handling for the errors thrown by the service*/
    @ExceptionHandler({FirebaseAuthException.class})
    public final ResponseEntity<List<String>> handleException(Exception ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();

        if(ex instanceof FirebaseAuthException){
            HttpStatus status = HttpStatus.FORBIDDEN;
            FirebaseAuthException fae = (FirebaseAuthException)ex;
            return handleFirebaseAuthException(fae,headers,status,request);
        }
        else{
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex,null,headers,status,request);
        }
    }

    private ResponseEntity<List<String>> handleFirebaseAuthException(FirebaseAuthException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex,errors,headers,status,request);

    }

    /*A single place to custom the response body of all the errors*/
    private ResponseEntity<List<String>> handleExceptionInternal(Exception ex, List<String> body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        /* Not sure ? */
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<List<String>>(body,headers,status);
    }

}
