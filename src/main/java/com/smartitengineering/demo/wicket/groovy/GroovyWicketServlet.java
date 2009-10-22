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

  @Override
  public void init()
          throws ServletException {
    final ClassLoader parentClassLoader = Thread.currentThread().
            getContextClassLoader();
    ClassLoader loader = new GroovyClassLoader(parentClassLoader);
    Thread.currentThread().setContextClassLoader(loader);
    super.init();
    Thread.currentThread().setContextClassLoader(parentClassLoader);
  }
}
