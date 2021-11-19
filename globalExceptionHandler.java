

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity handleCommonException(Exception e) {

        if(e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            log.error("handleCommonException throw CommonException : {}", commonException.getErrorCode());
            log.error("handleCommonException throw CommonException : {}", commonException.getErrorCode().getHttpStatus());
            log.error("handleCommonException throw CommonException : {}", commonException.getErrorCode().getDetail());

            return ErrorResponse.toResponseEntity(commonException.getErrorCode().getHttpStatus(), commonException.getErrorCode().getDetail());
        }else if(e instanceof NullPointerException){
            log.error("handleException throw NullPointerException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().toString());
        }else if(e instanceof NumberFormatException){
            log.error("handleException throw NumberFormatException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,  e.getCause().toString());
        }else if(e instanceof IOException){
            log.error("handleException throw IOException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,  e.getCause().toString());
        }else if(e instanceof ArithmeticException){
            log.error("handleException throw ArithmeticException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,  e.getCause().toString());
        }

        return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,  e.getCause().toString());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers
                                                                , HttpStatus status, WebRequest request) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            log.error("error: {}", error);
            // 코드 정보를 확인할 수 있다.
            log.error("error: {}", Arrays.toString(error.getCodes()));
        }

//        CommonResponseModel errorResponseModel = new CommonResponseModel();
//        for(ObjectError e : allErrors) {
//            log.error("error: {}", e);
//            errorResponseModel.setData( ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST,e.getDefaultMessage()));
//            break;
//        }
//        return new ResponseEntity<Object>(errorResponseModel,HttpStatus.OK);
        return super.handleMethodArgumentNotValid(ex, headers, status, request);

    }
}
