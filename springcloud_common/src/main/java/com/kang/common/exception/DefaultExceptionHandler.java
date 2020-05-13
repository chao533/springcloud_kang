package com.kang.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;

/**
 * <p>Title: DefaultExceptionHandler</p>  
 * <p>Description: 统一异常处理类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@RestControllerAdvice
public class DefaultExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	@ExceptionHandler(ServiceException.class)
    public Message<String> processServiceException(ServiceException e) {
		logger.error("业务异常:{}",e.getMessage());
        return new Message<String>(ErrorCode.ERROR.getCode(),e.getMessage());
    }
    
    
    

}
