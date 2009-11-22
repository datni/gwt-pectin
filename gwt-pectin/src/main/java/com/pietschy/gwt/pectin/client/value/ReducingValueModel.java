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

package com.pietschy.gwt.pectin.client.value;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * ValueModelFunction is a value model whose value is derived from collection of source
 * {@link ValueModel}s and a {@link Reduce}.  Changes in any of the source models result in
 * the funcion being re-evaluated and the value updating.
 */
public class ReducingValueModel<T, S>
extends AbstractValueModel<T>
{
   private Reduce<T,S> function;
   private ArrayList<ValueModel<S>> sourceModels = new ArrayList<ValueModel<S>>();
   private ValueChangeHandler<S> changeMonitor = new ValueChangeHandler<S>()
   {
      public void onValueChange(ValueChangeEvent<S> event)
      {
         recompute();
      }
   };
   
   private T computedValue = null;

   private ReducingValueModel(Reduce<T, S> function, boolean compute)
   {
      if (function == null)
      {
         throw new NullPointerException("function is null");
      }

      this.function = function;

      if (compute)
      {
         recompute();
      }
   }

   public ReducingValueModel(Reduce<T, S> function)
   {
      this(function, true);
   }

   public ReducingValueModel(Reduce<T, S> function, ValueModel<S> a, ValueModel<S> b)
   {
      this(function, Arrays.asList(a, b));
   }

   public ReducingValueModel(Reduce<T, S> function, Collection<ValueModel<S>> models)
   {
      this(function, false);

      for (ValueModel<S> model : models)
      {
         addSourceModel(model, false);
      }

      recompute();
   }

   public void addSourceModel(ValueModel<S> model)
   {
      addSourceModel(model, true);
   }

   private void addSourceModel(ValueModel<S> model, boolean recompute)
   {
      if (model == null)
      {
         throw new NullPointerException("source model is null");
      }

      model.addValueChangeHandler(changeMonitor);
      sourceModels.add(model);

      if (recompute)
      {
         recompute();
      }
   }


   private void recompute()
   {
      ArrayList<S> values = new ArrayList<S>();
      for (ValueModel<S> model : sourceModels)
      {
         values.add(model.getValue()); 
      }
      
      computedValue = function.compute(values);
      
      fireValueChangeEvent(computedValue);
   }

   public T getValue()
   {
      return computedValue;
   }
}
