package com.ml.shortener.infraestructure.configuration;

import static com.google.common.base.Predicates.not;


import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket documentation() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
        .build()
        .useDefaultResponseMessages(false)
        .pathMapping("/")
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Micro Service url-shortener",
        "Micro Service to convert a URL to a shortened",
        "v1",
        "",
        new Contact(
            "Gonzalo Salas",
            "https://web.telegram.org/#/im?p=@Gosalass",
            "go.salass@gmail.com"),
        "GitHub resource",
        "https://github.com/gosalass/ml-url-shortener",
        Collections.emptyList());
  }

}
