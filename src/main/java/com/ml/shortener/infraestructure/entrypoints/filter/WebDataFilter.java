package com.ml.shortener.infraestructure.entrypoints.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class WebDataFilter extends OncePerRequestFilter {

  @Value("#{T(java.util.Arrays).asList('${webDataFilter.toExcludePath:}')}")
  private List<String> excludeUrl;

  private String GATEWAY = "/gateway";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (checkRequestUrl(request.getRequestURI())) {
      filterChain.doFilter(request, response);
    } else {
      response.sendRedirect(GATEWAY.concat(request.getRequestURI()));
    }
  }

  protected boolean checkRequestUrl(String requestUrl) {
    return excludeUrl.stream().anyMatch(requestUrl::contains);
  }

}
