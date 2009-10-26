/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.wicket.groovy.api
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.AjaxRequestTarget

class GroovyWicketAjaxLink extends AjaxLink {
  def clickClosure;

  GroovyWicketAjaxLink(wicketId, onClickClosure) {
    super(wicketId)
    this.clickClosure = onClickClosure
  }
  void onClick(AjaxRequestTarget target) {
    clickClosure.call([this, target]);
    
  }
}