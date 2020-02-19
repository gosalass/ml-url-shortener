package com.ml.shortener.domain.usescases.port;

import com.ml.shortener.domain.entities.request.UrlRequest;
import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.infraestructure.exception.UrlServiceException;

public interface ShortenDataProvider {

  UrlResponse createShortenUrl(final UrlRequest urlRequest) throws UrlServiceException;

  UrlResponse retrieveOriginalUrl(final String shortenUrl) throws UrlServiceException;

  UrlResponse retrieveOriginalUrlByShortenPath(final String shortenUrl) throws UrlServiceException;

}
