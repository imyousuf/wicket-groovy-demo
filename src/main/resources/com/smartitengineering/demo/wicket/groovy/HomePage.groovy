package com.smartitengineering.demo.wicket.groovy
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.PageParameters
import com.smartitengineering.demo.wicket.groovy.api.GroovyWicketLink
import org.apache.wicket.markup.html.link.Link

/**
 *
 * @author imyousuf
 */
public class HomePage extends WebPage{
  public HomePage(final PageParameters parameters) {
    add(new Label("message", "If you see this message wicket is properly configured and running!"));
    add(new GroovyWicketLink("link", {link -> link.setResponsePage(HomePage.class)}));
  }
	public HomePage() {
    this(null);
  }
}

