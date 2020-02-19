package com.ml.shortener.infraestructure.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sequences")
public class Sequence {

  @Transient
  public static final String SEQUENCE_NAME = "sequence_name";

  @Id
  private String id;
  private Integer sequence;

}
