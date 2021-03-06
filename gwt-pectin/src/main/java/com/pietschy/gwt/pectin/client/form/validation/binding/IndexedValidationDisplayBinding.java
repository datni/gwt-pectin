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

package com.pietschy.gwt.pectin.client.form.validation.binding;

import com.pietschy.gwt.pectin.client.binding.AbstractBinding;
import com.pietschy.gwt.pectin.client.form.validation.HasIndexedValidationResult;
import com.pietschy.gwt.pectin.client.form.validation.IndexedValidationEvent;
import com.pietschy.gwt.pectin.client.form.validation.IndexedValidationHandler;
import com.pietschy.gwt.pectin.client.form.validation.IndexedValidationResult;
import com.pietschy.gwt.pectin.client.form.validation.component.IndexedValidationDisplay;


/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: Jul 17, 2009
 * Time: 1:06:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class IndexedValidationDisplayBinding
extends AbstractBinding
implements IndexedValidationHandler
{
   private IndexedValidationDisplay display;
   private HasIndexedValidationResult fieldValidator;

   public IndexedValidationDisplayBinding(HasIndexedValidationResult fieldValidator, IndexedValidationDisplay display)
   {
      this.display = display;
      this.fieldValidator = fieldValidator;
      registerDisposable(this.fieldValidator.addValidationHandler(this));
   }

   public void updateTarget()
   {
      updateDisplay(fieldValidator.getValidationResult());
   }

   public IndexedValidationDisplay getTarget()
   {
      return display;
   }

   private void updateDisplay(IndexedValidationResult result)
   {
      display.setValidationResult(result);
   }

   public void onValidate(IndexedValidationEvent event)
   {
      updateDisplay(event.getValidationResult());
   }
}