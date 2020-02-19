package com.ml.shortener.infraestructure.dataprovider;

import com.ml.shortener.domain.entities.request.UrlRequest;
import com.ml.shortener.domain.entities.response.UrlResponse;
import com.ml.shortener.domain.usescases.port.ShortenDataProvider;
import com.ml.shortener.infraestructure.dataprovider.service.UrlShortenService;
import com.ml.shortener.infraestructure.entrypoints.translator.UrlTranslator;
import com.ml.shortener.infraestructure.exception.UrlServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;

public class ShortenDataProviderImpl implements ShortenDataProvider {

  private final UrlShortenService urlShortenService;
  private final UrlTranslator urlTranslator;

  @Autowired
  public ShortenDataProviderImpl(UrlShortenService urlShortenService,
     UrlTranslator urlTranslator) {
    this.urlShortenService = urlShortenService;
    this.urlTranslator = urlTranslator;
  }

  @Override
  public UrlResponse createShortenUrl(UrlRequest urlRequest) throws UrlServiceException {
    try {
      String document = this.urlShortenService.createDomain(urlRequest);
      return this.urlTranslator.translateResponse(document);
    } finally {
      //TODO LoggerEvent
    }
  }

  @Override
  public UrlResponse retrieveOriginalUrl(String urlRequest) throws UrlServiceException {
    try {
      final URL url = new URL(urlRequest);
      final String key = url.getPath().substring(1);
      return this.retrieveOriginalUrlByShortenPath(key);
    } catch (MalformedURLException e) {
      throw new UrlServiceException("ERROR getting path from URL");
    } finally {
      //TODO
    }
  }

  @Override
  public UrlResponse retrieveOriginalUrlByShortenPath(String shortenUrl) throws UrlServiceException {
    final Integer id = this.urlShortenService.retrieveUrlKey(shortenUrl);
    return this.urlTranslator.translateResponse(this.urlShortenService.retrieveDomainById(id));
  }

}
