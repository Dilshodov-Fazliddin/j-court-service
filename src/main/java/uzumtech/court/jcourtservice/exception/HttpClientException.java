package uzumtech.court.jcourtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import uzumtech.court.jcourtservice.constant.enums.ErrorType;

import static uzumtech.court.jcourtservice.constant.enums.Error.HTTP_CLIENT_ERROR_CODE;


public class HttpClientException extends ApplicationException {

    public HttpClientException(String message, HttpStatusCode status) {
        super(HTTP_CLIENT_ERROR_CODE.getCode(), message, ErrorType.EXTERNAL, HttpStatus.valueOf(status.value()));
    }
}
