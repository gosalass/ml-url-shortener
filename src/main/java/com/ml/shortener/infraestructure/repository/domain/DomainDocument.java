package com.ml.shortener.infraestructure.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "domain")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DomainDocument {

  @Id
  private String id;
  private Integer uniqueId;
  private String original;

}
