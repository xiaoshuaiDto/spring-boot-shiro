package com.zw.admin.server.advice;

import java.io.Serializable;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * springmvc异常处理
 * 
 * @author 小威老师
 *
 */
@Slf4j(topic = "adminLogger")
@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseInfo badRequestException(IllegalArgumentException exception) {
		return ResponseInfo.builder().code(HttpStatus.BAD_REQUEST.value() + "").message(exception.getMessage()).build();
	}

	@ExceptionHandler({ UnknownAccountException.class, IncorrectCredentialsException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseInfo loginException(Exception exception) {
		return ResponseInfo.builder().code(HttpStatus.UNAUTHORIZED.value() + "").message(exception.getMessage())
				.build();
	}

	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseInfo forbidden(Exception exception) {
		return ResponseInfo.builder().code(HttpStatus.FORBIDDEN.value() + "").message(exception.getMessage()).build();
	}

	@ExceptionHandler({ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
			UnsatisfiedServletRequestParameterException.class, MethodArgumentTypeMismatchException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseInfo badRequestException(Exception exception) {
		return ResponseInfo.builder().code(HttpStatus.BAD_REQUEST.value() + "").message(exception.getMessage()).build();
	}

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseInfo exception(Throwable throwable) {
		log.error("系统异常", throwable);
		return ResponseInfo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value() + "")
				.message(throwable.getMessage()).build();

	}

	@Data
	@Builder
	public static class ResponseInfo implements Serializable {

		private static final long serialVersionUID = -6414227214551871784L;

		private String code;
		private String message;
	}

}
