package com.pw.sign.common.exception.handler;

import com.pw.sign.common.exception.BusinessException;
import com.pw.sign.common.exception.code.BaseResponseCode;
import com.pw.sign.common.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * RestExceptionHandler
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * 系统繁忙，请稍候再试"
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("Exception,exception:{}", e, e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_BUSY);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object handleNoPageException(MethodArgumentTypeMismatchException e) {
        log.error("Exception,exception:{}", e, e);
        ModelAndView modelAndView = new ModelAndView("/fast/route/n/error");
        return modelAndView;
    }

    /**
     * 自定义全局异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessExceptionHandler(BusinessException e) {
        log.error("Exception,exception:{}", e, e);
        return new DataResult(e.getMessageCode(), e.getDetailMessage());
    }

    /**
     * 处理validation 框架异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return DataResult.getResult(BaseResponseCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), errors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    public DataResult methodArgumentNotValidSpirngExceptionHandler(BindException e) {
        log.error("methodArgumentNotValidSpirngExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return DataResult.getResult(BaseResponseCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), errors.get(0).getDefaultMessage());
    }

    /**
     * 校验List<entity>类型， 需要controller添加@Validated注解
     * 处理Validated List<entity> 异常
     */
    @ExceptionHandler
    public DataResult handle(ConstraintViolationException exception) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", exception, exception);
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            builder.append(violation.getMessage());
            break;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), builder.toString());
    }

}
