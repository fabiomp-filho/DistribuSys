package fabio.distribusys.br.taskms.handler;

import fabio.distribusys.br.taskms.exceptions.BusinessException;
import fabio.distribusys.br.taskms.exceptions.NotFoundException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.resolve(response.status());

        if (status == null) {
            return new Exception("Unknown status code: " + response.status());
        }

        return switch (status) {
            case NOT_FOUND -> new NotFoundException(getErrorMessage(response));
            case BAD_REQUEST -> new BusinessException(getErrorMessage(response));
            case INTERNAL_SERVER_ERROR ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, getErrorMessage(response));
            default -> new Exception("Generic error: " + getErrorMessage(response));
        };
    }

    private String getErrorMessage(Response response) {
        try {
            if (response.body() != null) {
                return Util.toString(response.body().asReader());
            }
            return "Unknown error";
        } catch (IOException e) {
            return "Failed to read response body";
        }
    }
}
