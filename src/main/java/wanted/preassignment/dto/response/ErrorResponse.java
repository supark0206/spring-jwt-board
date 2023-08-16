package wanted.preassignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wanted.preassignment.exception.ErrorCode;

@Getter
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.error = errorCode.getStatus().name();
        this.code = errorCode.name();
        this.message = errorCode.getMESSAGE();
    }
}
