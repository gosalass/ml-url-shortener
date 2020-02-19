package com.ml.shortener.infraestructure.configuration;

import com.ml.shortener.domain.usescases.ShortenUseCase;
import com.ml.shortener.infraestructure.dataprovider.ShortenDataProviderImpl;
import com.ml.shortener.infraestructure.dataprovider.service.UrlShortenService;
import com.ml.shortener.infraestructure.entrypoints.translator.UrlTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

  @Bean
  public ShortenUseCase shortenUseCase(
      final UrlTranslator urlTranslator,
      final UrlShortenService urlShortenService
      ) {
    return new ShortenUseCase(
        new ShortenDataProviderImpl(urlShortenService, urlTranslator)
    );
  }

}
