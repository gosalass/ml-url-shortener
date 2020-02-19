package com.ml.shortener.domain.entities.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(
    value = "UrlResponse",
    description = "Object that contain a reference convertion")
public class UrlResponse {

  @ApiModelProperty(
      value = "Url reference",
      required = true)
  @URL(regexp = "^(http|https).*")
  private String ref;

}
