package com.cow.horse.common.handler;

import com.cow.horse.common.enums.ErrorMsg;
import com.cow.horse.common.exception.ParamException;
import com.cow.horse.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * controller的方法参数错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResultVo.fail(ErrorMsg.PARAM_ERROR,collect);
    }

    /**
     * 缺少request body错误
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo HttpMessageNotReadableExceptionHandler() {
        return ResultVo.fail(ErrorMsg.MISSING_PARAMETER, "requestBody错误!");
    }

    /**
     * url中缺少Query Params
     * @param e e.getMessage()返回首个缺少的参数名
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return ResultVo.fail(ErrorMsg.MISSING_PARAMETER, "缺少参数"+e.getParameterName());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo ConstraintViolationExceptionHandler(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        Map<String, String> map = new HashMap<>();
        if (set.size() > 0) {
            for (ConstraintViolation<?> cv : set) {
                String[] param = cv.getPropertyPath().toString().split("\\.");
                String message = cv.getMessage();
                map.put(param[param.length - 1], message);
            }
        }

        return ResultVo.fail(ErrorMsg.PARAM_ERROR, map);
    }

    @ExceptionHandler(ParamException.class)
    public ResultVo ParamExceptionHandler(ParamException e) {
        return ResultVo.fail(ErrorMsg.PARAM_ERROR, e.getMap());
    }

    /*@ExceptionHandler(Exception.class)
    public Object CommonExceptionHandler(Exception e){
        return "服务器错误";
    }*/


    /**
     * 拦截cookie缺失异常
     * @return
     */
    @ExceptionHandler(MissingRequestCookieException.class)
    public ResultVo MissingRequestCookieExceptionHandler(){
        return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        // 自定义异常处理逻辑
        String message = e.getMessage();
        e.printStackTrace();
        if (message.contains("(using password: YES)")) {
            if (!message.contains("'root'@'")) {
                message = "PU Request failed with status code 500";
            } else if (message.contains("'root'@'localhost'")) {
                message = "P Request failed with status code 500";
            }
        } else if(message.contains("Table") && message.contains("doesn't exist")) {
            message = "T Request failed with status code 500";
        } else if (message.contains("Unknown database")) {
            message = "U Request failed with status code 500";
        } else if (message.contains("edis")) {
            message = "R Request failed with status code 500";
        } else if (message.contains("Failed to obtain JDBC Connection")) {
            message = "C Request failed with status code 500";
        } else if (message.contains("SQLSyntaxErrorException")) {
            message = "S Request failed with status code 500";
        }
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
/**
 * DefaultHandlerExceptionResolver
 *
 * HttpRequestMethodNotSupportedException
 * 405 (SC_METHOD_NOT_ALLOWED)
 * HttpMediaTypeNotSupportedException
 * 415 (SC_UNSUPPORTED_MEDIA_TYPE)
 * HttpMediaTypeNotAcceptableException
 * 406 (SC_NOT_ACCEPTABLE)
 * MissingPathVariableException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * MissingServletRequestParameterException
 * 400 (SC_BAD_REQUEST)
 * ServletRequestBindingException
 * 400 (SC_BAD_REQUEST)
 * ConversionNotSupportedException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * TypeMismatchException
 * 400 (SC_BAD_REQUEST)
 * HttpMessageNotReadableException
 * 400 (SC_BAD_REQUEST)
 * HttpMessageNotWritableException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * MethodArgumentNotValidException
 * 400 (SC_BAD_REQUEST)
 * MissingServletRequestPartException
 * 400 (SC_BAD_REQUEST)
 * BindException
 * 400 (SC_BAD_REQUEST)
 * NoHandlerFoundException
 * 404 (SC_NOT_FOUND)
 * AsyncRequestTimeoutException
 * 503 (SC_SERVICE_UNAVAILABLE)
 */
