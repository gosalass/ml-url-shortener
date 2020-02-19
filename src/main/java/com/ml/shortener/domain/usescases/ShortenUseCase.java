package com.ml.shortener.domain.usescases;

import com.ml.shortener.domain.entities.request.UrlRequest;
import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.domain.usescases.port.ShortenDataProvider;
import com.ml.shortener.infraestructure.exception.UrlServiceException;

public class ShortenUseCase {

  private final ShortenDataProvider dataProvider;

  public ShortenUseCase(ShortenDataProvider dataProvider) {
    this.dataProvider = dataProvider;
  }

  public UrlResponse createShortenUrl(UrlRequest urlRequest) throws UrlServiceException {
    return this.dataProvider.createShortenUrl(urlRequest);
  }

  public UrlResponse retrieveOriginalUrl(String urlRequest) throws UrlServiceException {
    return this.dataProvider.retrieveOriginalUrl(urlRequest);
  }

  public UrlResponse retrieveOriginalUrlByShortenPath(String shortenUrl) throws UrlServiceException {
    return this.dataProvider.retrieveOriginalUrlByShortenPath(shortenUrl);
  }

}
