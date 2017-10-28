package org.lxp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.jaxrs.config.BeanConfig;

public class Bootstrap extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    LOG.debug("{} {}", config.getServletName(), config.getServletContext().getContextPath());
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion("1.0.2");
    beanConfig.setBasePath(String.format("%s/rest", config.getServletContext().getContextPath()));
    beanConfig.setResourcePackage("org.lxp.resource");
    beanConfig.setScan(true);
  }
}
