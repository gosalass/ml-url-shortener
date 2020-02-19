package com.ml.shortener.infraestructure.entrypoints.controller;

import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.domain.usescases.ShortenUseCase;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/gateway")
public class UrlRedirectController {

  private final ShortenUseCase shortenUseCase;

  public UrlRedirectController(ShortenUseCase shortenUseCase) {
    this.shortenUseCase = shortenUseCase;
  }

  @GetMapping(value = "/{shortenUrl}")
  public ResponseEntity<Void> redirect(@PathVariable(value = "shortenUrl") final String shortenUrl) {

    final UrlResponse urlResponse = this.shortenUseCase.retrieveOriginalUrlByShortenPath(shortenUrl);

    return
        ResponseEntity
          .status(HttpStatus.MOVED_PERMANENTLY)
            .location(URI.create(urlResponse.getRef()))
              .build();
  }

}
