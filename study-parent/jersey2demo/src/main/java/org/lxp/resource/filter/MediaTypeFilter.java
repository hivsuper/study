package org.lxp.resource.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class MediaTypeFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
      throws IOException {
    for (Annotation annotation : responseContext.getEntityAnnotations()) {
      filterProducesAnnotation(annotation, responseContext);
    }
  }

  private void filterProducesAnnotation(Annotation annotation, ContainerResponseContext responseContext) {
    if (annotation instanceof Produces) {
      Produces produces = (Produces) annotation;
      filterMediaTypes(produces, responseContext);
    }
  }

  private void filterMediaTypes(Produces produces, ContainerResponseContext responseContext) {
    List<Object> mediaTypes = new ArrayList<Object>();
    for (String mediaType : produces.value()) {
      mediaTypes.add(mediaType.equals(MediaType.APPLICATION_JSON) ? mediaType + ";charset=UTF-8" : mediaType);
    }
    responseContext.getHeaders().put("Content-Type", mediaTypes);
  }
}