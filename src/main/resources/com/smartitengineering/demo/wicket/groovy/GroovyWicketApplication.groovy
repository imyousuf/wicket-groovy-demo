package com.smartitengineering.demo.wicket.groovy

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.util.lang.Classes

/**
 *
 * @author imyousuf
 */
public class GroovyWicketApplication extends WebApplication {

  public void init() {
    getApplicationSettings().setClassResolver(new GroovyClassResolver())
    super.init()
  }

	public Class getHomePage() {
    try {
      return Classes.resolveClass("com.smartitengineering.demo.wicket.groovy.HomePage")
    }
    catch(Exception ex) {
      ex.printStackTrace()
      return null
    }
  }
}

