/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.wicket.groovy.api

import org.apache.wicket.markup.html.link.Link

class GroovyWicketLink extends Link {
  
  def clickClosure;

  GroovyWicketLink(wicketId, onClickClosure) {
    super(wicketId)
    this.clickClosure = onClickClosure
  }

  public void onClick() {
    this.clickClosure.call(this);
  }
}