package com.ml.shortener.infraestructure.entrypoints.controller;

import com.ml.shortener.domain.entities.request.UrlRequest;
import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.domain.usescases.ShortenUseCase;
import com.ml.shortener.infraestructure.exception.UrlServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/url-shorten")
@Validated
public class UrlShortenController {

  private ShortenUseCase shortenUseCase;

  public UrlShortenController(ShortenUseCase shortenUseCase) {
    this.shortenUseCase = shortenUseCase;
  }

  @ApiOperation(value = "Create new shorten URL")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = HttpServletResponse.SC_OK,
              message = "OK",
              response = UrlResponse.class),
          @ApiResponse(
              code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              message = "INTERNAL SERVER ERROR"),
          @ApiResponse(
              code = HttpServletResponse.SC_BAD_REQUEST,
              message = "BAD OR MALFORMED REQUEST")
      })
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UrlResponse> createShortenUrl(@Valid @RequestBody final UrlRequest urlRequest) throws UrlServiceException {
    return ResponseEntity.ok(this.shortenUseCase.createShortenUrl(urlRequest));
  }

  @ApiOperation(value = "Get original URL from shorten URL")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = HttpServletResponse.SC_OK,
              message = "OK",
              response = UrlResponse.class),
          @ApiResponse(
              code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              message = "INTERNAL SERVER ERROR"),
          @ApiResponse(
              code = HttpServletResponse.SC_BAD_REQUEST,
              message = "BAD OR MALFORMED REQUEST")
      })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UrlResponse> retrieveOriginalUrlByShorten(@RequestParam(value = "url") @Pattern(regexp = "^(http|https)://[a-zA-A0-9.]+/[a-zA-Z0-9]+$") final String urlShorten) throws UrlServiceException {
    return ResponseEntity.ok(this.shortenUseCase.retrieveOriginalUrl(urlShorten));
  }

  @ApiOperation(value = "Delete shorten URL")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = HttpServletResponse.SC_NO_CONTENT,
              message = "OK",
              response = UrlResponse.class),
          @ApiResponse(
              code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              message = "INTERNAL SERVER ERROR"),
          @ApiResponse(
              code = HttpServletResponse.SC_BAD_REQUEST,
              message = "BAD OR MALFORMED REQUEST")
      })
  @DeleteMapping(value = "/", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteShortenUrl(@Valid @RequestBody final UrlRequest urlRequest) throws UrlServiceException {
    this.shortenUseCase.deleteShortenUrl(urlRequest);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
