package com.ml.shortener.infraestructure.entrypoints.translator;

import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.infraestructure.repository.domain.DomainDocument;
import org.springframework.stereotype.Component;

@Component
public class UrlTranslator {

  public UrlResponse translateResponse(String ref) {
    UrlResponse.UrlResponseBuilder urlResponseBuilder = UrlResponse.builder();
    urlResponseBuilder.ref("http://localhost:8080/".concat(ref));
    return urlResponseBuilder.build();
  }

  public UrlResponse translateResponse(DomainDocument domainDocument) {
    UrlResponse.UrlResponseBuilder urlResponseBuilder = UrlResponse.builder();
    urlResponseBuilder.ref(domainDocument.getOriginal());
    return urlResponseBuilder.build();
  }

}
