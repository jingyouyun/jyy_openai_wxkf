package cn.jingyouyun.openai.wxkf.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * TODO 后续逻辑处理待开发
     * @param e
     */
    @ExceptionHandler(value = SystemException.class)
    public void exceptionHandler(SystemException e) {
        log.error("「异常处理器」全局异常捕获 SendMsgException {} - {}", e.getExceptionMsgEnum().getCode(), e.getExceptionMsgEnum().getMessage());
    }
}
