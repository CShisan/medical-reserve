package com.cshisan.reserve.common.exception;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author CShisan
 * @date 2022-2-19 0:15
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Result<Object>> handleException(Exception e) {
        Result<Object> resp = new Result<>();
        CustomException exception = (CustomException) e;
        resp.setCode(exception.getCode());
        resp.setMsg(exception.getMessage());
        log.warn("\n【自定义异常】统一异常处理：e={}", exception.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * 处理系统异常，均抛出 500
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Object>> handleErrorException(Exception e) {
        Result<Object> resp = new Result<>();
        resp.setCode(CodeEnum.FAIL.getCode());
        resp.setMsg(CodeEnum.FAIL.getMessage());
        log.error("\n【系统异常】统一异常处理：e={}", e.getMessage(), e);
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数认证错误
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<Object>> handlerNotValidException(MethodArgumentNotValidException e) {
        Result<Object> resp = new Result<>();
        resp.setCode(CodeEnum.FAIL.getCode());
        // 返回错误提示
        FieldError fieldError = e.getBindingResult().getFieldError();
        String msg = "系统错误";
        if (null != fieldError) {
            // 修改不显示字段提示，保护实体信息
            msg = fieldError.getDefaultMessage();
        }
        resp.setMsg(msg);
        log.warn("\n【参数异常】统一异常处理：e={}", e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
