package com.smartitengineering.demo.wicket.groovy;

import groovy.lang.GroovyClassLoader;
import javax.servlet.ServletException;
import org.apache.wicket.protocol.http.WicketServlet;

/**
 *
 * @author imyousuf
 */
public class GroovyWicketServlet
        extends WicketServlet {

  private ClassLoader loader;

  @Override
  public void init()
          throws ServletException {
    loader = new GroovyClassLoader(
            Thread.currentThread().getContextClassLoader());
    Thread.currentThread().setContextClassLoader(loader);
    super.init();
  }
}
