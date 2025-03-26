package com.example.profileservice.exception;


import com.example.profileservice.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalHandleException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResult<?>> handleException(Exception e) {
        log.error(">>>>>>>>>>>>>>>>>>> 🔴 Exception caught: {}", e.getClass().getName());
        return ResponseEntity.badRequest().body(CommonResult.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .data(e.getMessage())
                .build());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<CommonResult<?>> handleAppException(AppException e) {
        return ResponseEntity.badRequest().body(CommonResult.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build());
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<CommonResult<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        log.error("⚠ Duplicate email detected: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(CommonResult.builder()
//                        .code(409)
//                        .message("Email already exists!")
//                        .build());
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<CommonResult<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException iae) {
        }
        return ResponseEntity.badRequest().body(CommonResult.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }

//    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
//    ResponseEntity<CommonResult<?>> handleAccessDeniedException(Exception e) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommonResult.builder()
//                .data("You not permission")
//                .message("FORBIDDEN")
//                .code(403)
//                .build());
//    }

}
