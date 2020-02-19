package com.ml.shortener.infraestructure.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends UrlServiceException {

  public UrlNotFoundException(String message) {
    super(message);
  }

}
