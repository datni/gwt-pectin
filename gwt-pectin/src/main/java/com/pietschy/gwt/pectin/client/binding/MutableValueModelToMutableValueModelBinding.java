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

package com.pietschy.gwt.pectin.client.binding;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.pietschy.gwt.pectin.client.value.GuardedValueChangeHandler;
import com.pietschy.gwt.pectin.client.value.MutableValueModel;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Jul 1, 2009
 * Time: 4:35:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MutableValueModelToMutableValueModelBinding<T>
extends AbstractMutableValueBinding<T>
{
   private MutableValueModel<T> target;
   private TargetMonitor targetValueChangeMonitor = new TargetMonitor();

   public MutableValueModelToMutableValueModelBinding(MutableValueModel<T> source, MutableValueModel<T> target)
   {
      super(source);
      this.target = target;
      registerDisposable(target.addValueChangeHandler(targetValueChangeMonitor));
   }

   protected void updateTarget(final T value)
   {
      targetValueChangeMonitor.whileIgnoringEvents(new Runnable()
      {
         public void run()
         {
            target.setValue(value);
         }
      });
   }

   public MutableValueModel<T> getTarget()
   {
      return target;
   }

   private class TargetMonitor extends GuardedValueChangeHandler<T>
   {
      @Override
      public void onGuardedValueChanged(ValueChangeEvent<T> event)
      {
         updateModel(event.getValue());
      }
   }
}