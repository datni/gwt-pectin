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

package com.pietschy.gwt.pectin.client.style;

import com.google.gwt.user.client.ui.UIObject;
import com.pietschy.gwt.pectin.client.binding.BindingContainer;
import com.pietschy.gwt.pectin.client.value.ValueModel;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Oct 27, 2009
 * Time: 10:37:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class WidgetStyleBindingBuilder
{
   private BindingContainer builder;
   private String styleName;
   private UIObject widget;

   public WidgetStyleBindingBuilder(BindingContainer builder, UIObject widget, String styleName)
   {
      this.builder = builder;
      this.styleName = styleName;
      this.widget = widget;
   }

   public void when(ValueModel<Boolean> condition)
   {
      builder.registerBinding(new StyleBinding(condition, widget, styleName));
   }
}
