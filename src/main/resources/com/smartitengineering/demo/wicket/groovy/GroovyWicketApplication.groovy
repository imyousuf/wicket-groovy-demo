package com.smartitengineering.demo.wicket.groovy

import org.apache.wicket.protocol.http.WebApplication

/**
 *
 * @author imyousuf
 */
public class GroovyWicketApplication extends WebApplication {
	public Class getHomePage() {
    try {
      return Class.forName("com.smartitengineering.demo.wicket.groovy.HomePage"
        ,true, Thread.currentThread().getContextClassLoader()
      )
    }
    catch(Exception ex) {
      ex.printStackTrace()
      return null
    }
  }
}

