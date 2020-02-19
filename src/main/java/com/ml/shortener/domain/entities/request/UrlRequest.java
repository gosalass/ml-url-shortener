package com.ml.shortener.domain.entities.request;

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
    value = "UrlRequest",
    description = "Object that contain a URL")
public class UrlRequest {

  @ApiModelProperty(
      value = "Url to change",
      required = true)
  @URL(regexp = "^(http|https).*")
  private String url;

}
