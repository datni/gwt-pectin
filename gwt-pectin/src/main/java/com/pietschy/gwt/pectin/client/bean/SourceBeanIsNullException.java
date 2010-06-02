package com.pietschy.gwt.pectin.client.bean;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: May 15, 2010
 * Time: 11:54:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SourceBeanIsNullException extends RuntimeException
{
   public SourceBeanIsNullException(PropertyKey propertyKey)
   {
      super("Can't write property: " + propertyKey.getFullPath() + " as property: " + propertyKey.getParentPath() + " is null");
   }
}
