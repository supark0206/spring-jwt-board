package wanted.preassignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST : 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),

    //404 NOT_FOUND : 리소스를 찾을 수 없음
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),

    //409 CONFLICT : 리소스 충돌
    EXIST_USER_EMAIL(HttpStatus.CONFLICT, "중복된 이메일이 존재합니다."),

    //405 METHOD_NOT_ALLOWED : 허용 되지않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드 호출입니다."),

    //500 INTERNAL_SERVER_ERROR : 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버 오류입니다.");

    private final HttpStatus status;
    private final String MESSAGE;
}