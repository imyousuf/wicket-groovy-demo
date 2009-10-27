/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.wicket.groovy
import org.apache.wicket.markup.html.link.Link
import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.CallbackFilter
import org.apache.wicket.ajax.markup.html.AjaxLink

/**
 *
 * @author imyousuf
 */
class WicketClosureFactory {
	static Link getWicketLink(wicketId, onClickClosure) {
    return getInstance(Link.class, wicketId, onClickClosure)
  }
	static AjaxLink getWicketAjaxLink(wicketId, onClickClosure) {
    return getInstance(AjaxLink.class, wicketId, onClickClosure)
  }

  static Object getInstance(clazz, wicketId, onClickClosure) {
    Enhancer enhancer = new Enhancer();
    enhancer.setCallback(new AbstractMethodHandler(onClickClosure))
    enhancer.setSuperclass(clazz)
    return enhancer.create([String.class].toArray(new Class[0]),
      [wicketId].toArray(new String[0]));
  }
}

