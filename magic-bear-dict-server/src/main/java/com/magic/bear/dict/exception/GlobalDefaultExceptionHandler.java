package com.magic.bear.dict.exception;

import com.magic.bear.dict.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhoufeng
 * @description 全局异常处理
 * @create 2021-10-21 14:53
 **/
@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {



    @ExceptionHandler(DictException.class)
    @ResponseBody
    public ResultVO DictExceptionHandler(DictException e) {

        ResultVO resultVO = new ResultVO();

        resultVO.setCode(e.getErrorCode());
        resultVO.setMsg(e.getErrorMessage());
        return resultVO;
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResultVO defaultExceptionHandler(Throwable ex) {

        log.error("全局异常处理器捕获异常,请检查", ex);
        return ResultVO.fail();
    }
}
