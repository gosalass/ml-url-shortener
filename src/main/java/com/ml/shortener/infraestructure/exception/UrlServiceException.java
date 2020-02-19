package com.ml.shortener.infraestructure.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlServiceException extends RuntimeException implements Serializable {

  private String message;

}
