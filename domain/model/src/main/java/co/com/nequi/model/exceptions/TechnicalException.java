package co.com.nequi.model.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TechnicalException extends RuntimeException{

    private Integer statusCode;

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
