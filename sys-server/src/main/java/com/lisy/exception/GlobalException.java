package com.lisy.exception;

import com.lisy.utils.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: lisy
 * @Date: 2021/4/13
 * @version: 1.0
 * @Description: ""
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlExcetion(SQLException e) {
        log.error("发生空指针异常！原因是:",e);
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据库有关联,操作失败!");
        }
        return RespBean.error("数据库操作异常!");
    }
    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public RespBean exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return RespBean.error("空指针异常！");
    }
    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public RespBean exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return RespBean.error("未知异常！");
    }
}
