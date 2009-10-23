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
  private HotReloadableGroovyClassLoader hotClassLoader;
  private boolean hotLoadEnabled;

  public GroovyClassLoaderFilter() {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
      throws IOException,
             ServletException {
    if (parentClassLoader == null) {
      parentClassLoader = Thread.currentThread().getContextClassLoader();
    }
    try {
      Thread.currentThread().setContextClassLoader(getClassLoader());
      chain.doFilter(request, response);
    }
    finally {
      Thread.currentThread().setContextClassLoader(parentClassLoader);
    }
  }

  protected ClassLoader getClassLoader() {
    if (hotLoadEnabled) {
      synchronized (this) {
        if (hotClassLoader == null) {
          hotClassLoader = new HotReloadableGroovyClassLoader(parentClassLoader);
        }
      }
      return hotClassLoader.getGroovyClassLoader();
    }
    else {
      synchronized (this) {
        if (loader == null) {
          loader = new GroovyClassLoader(parentClassLoader);
        }
      }
      return loader;
    }
  }

  public void init(FilterConfig filterConfig)
      throws ServletException {
    String hotLoad = filterConfig.getInitParameter("hotLoad");
    hotLoadEnabled = Boolean.parseBoolean(hotLoad);
  }
}
