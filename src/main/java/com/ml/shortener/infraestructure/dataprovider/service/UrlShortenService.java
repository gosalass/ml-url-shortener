package com.ml.shortener.infraestructure.dataprovider.service;

import com.ml.shortener.domain.entities.request.UrlRequest;
import com.ml.shortener.infraestructure.entrypoints.generator.UrlGenerator;
import com.ml.shortener.infraestructure.exception.UrlNotFoundException;
import com.ml.shortener.infraestructure.exception.UrlServiceException;
import com.ml.shortener.infraestructure.repository.DomainRepository;
import com.ml.shortener.infraestructure.repository.SequenceRepository;
import com.ml.shortener.infraestructure.repository.domain.DomainDocument;
import com.ml.shortener.infraestructure.repository.domain.Sequence;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

  private final UrlGenerator urlGenerator;
  private final SequenceRepository sequenceRepository;
  private final DomainRepository domainRepository;

  @Autowired
  public UrlShortenService(UrlGenerator urlGenerator, SequenceRepository sequenceRepository,
                           DomainRepository domainRepository) {
    this.urlGenerator = urlGenerator;
    this.sequenceRepository = sequenceRepository;
    this.domainRepository = domainRepository;
  }

  public String createDomain(UrlRequest urlRequest) throws UrlServiceException {

    try {

      final Integer uniqueID = this.sequenceRepository.getNextVal(Sequence.SEQUENCE_NAME);
      final String pathVariable = this.urlGenerator.fromBase10(uniqueID);

      DomainDocument.DomainDocumentBuilder domainDocumentBuilder = DomainDocument.builder();
      domainDocumentBuilder
        .original(urlRequest.getUrl())
          .uniqueId(uniqueID);

      this.domainRepository.save(domainDocumentBuilder.build());
      return pathVariable;

    } catch (Exception ex) {
      throw
          UrlServiceException
            .builder()
              .message("Error creating shorten domain")
            .build();
    }

  }

  public Integer retrieveUrlKey(String shortenUrl) {
    return this.urlGenerator.toBase10(shortenUrl);
  }

  public DomainDocument retrieveDomainById(Integer id) throws UrlServiceException {

    Optional<DomainDocument> optionalDomainDocument = this.domainRepository.findById(id.toString());
    if(!optionalDomainDocument.isPresent()) {
      throw
        new UrlNotFoundException("Domain not found");
    }

    return optionalDomainDocument.get();
  }

}
