package com.smartitengineering.demo.wicket.groovy;

import groovy.lang.GroovyClassLoader;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author imyousuf
 */
public class GroovyClassLoaderFilter
        implements Filter {

  private ClassLoader loader = null;
  private ClassLoader parentClassLoader = null;

  public GroovyClassLoaderFilter() {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
          throws IOException,
                 ServletException {
    if (loader == null) {
      parentClassLoader = Thread.currentThread().getContextClassLoader();
      loader = new GroovyClassLoader(parentClassLoader);
    }
    try {
      Thread.currentThread().setContextClassLoader(loader);
      chain.doFilter(request, response);
    }
    finally {
      Thread.currentThread().setContextClassLoader(parentClassLoader);
    }
  }

  public void init(FilterConfig filterConfig)
          throws ServletException {
  }
}
