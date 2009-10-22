package com.smartitengineering.demo.wicket.groovy

import org.apache.wicket.application.IClassResolver
import org.apache.wicket.application.DefaultClassResolver
import java.util.Iterator;
import java.net.URL;

/**
 *
 * @author imyousuf
 */
class GroovyClassResolver implements IClassResolver {

  private IClassResolver impl = new DefaultClassResolver();

  public Class resolveClass(String className) {
    return Class.forName(className, true, Thread.currentThread().getContextClassLoader())
  }

  public Iterator<URL> getResources(String name) {
    return impl.getResources(name);
  }
	
}

