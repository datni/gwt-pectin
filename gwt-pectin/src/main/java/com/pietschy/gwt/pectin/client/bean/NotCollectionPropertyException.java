/*
 * Copyright 2009 Andrew Pietsch 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at 
 *      
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing permissions 
 * and limitations under the License. 
 */

package com.pietschy.gwt.pectin.client.bean;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Sep 25, 2009
 * Time: 10:07:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotCollectionPropertyException extends RuntimeException
{
   public NotCollectionPropertyException(PropertyDescriptor descriptor)
   {
      this(descriptor, descriptor.getValueType());
   }

   public NotCollectionPropertyException(Path path, Class<?> actualType)
   {
      super("Expected " + path + " property to be a collection type, but found: " + actualType);
   }
}