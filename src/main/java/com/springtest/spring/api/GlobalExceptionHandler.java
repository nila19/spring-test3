package com.springtest.spring.api;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springtest.spring.api.model.Response;
import com.springtest.spring.exception.HelloException;
import com.springtest.spring.utils.LogUtils;

@Generated // exclude from Jacoco test coverage
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HelloException.class)
  public Response handleHelloException(final HelloException e) {
    LogUtils.logError(log, null, "Handling HelloException -> " + e.getMessage(), e);
    return new Response(1000, "Exception -> " + e.getMessage());
  }

  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public Response handleIllegalArgumentException(final IllegalArgumentException e) {
    LogUtils.logError(log, null, "Handling IllegalArgumentException -> " + e.getMessage(), e);
    return new Response(2000, "Exception -> " + e.getMessage());
  }

  @Hidden
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public Response handleException(final Exception e) {
    LogUtils.logError(log, null, "Handling Exception -> " + e.getMessage(), e);
    e.printStackTrace();
    String msg = Arrays.stream(e.getStackTrace())
        .map(StackTraceElement::toString)
        .collect(Collectors.joining(" ==> "));
    return new Response(9000, "Exception -> " + msg);
  }
}
