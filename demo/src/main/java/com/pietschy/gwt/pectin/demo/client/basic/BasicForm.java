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

package com.pietschy.gwt.pectin.demo.client.basic;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.pietschy.gwt.pectin.client.binding.WidgetBinder;
import com.pietschy.gwt.pectin.client.components.EnhancedTextBox;
import com.pietschy.gwt.pectin.demo.client.domain.Gender;
import com.pietschy.gwt.pectin.demo.client.domain.Wine;
import com.pietschy.gwt.pectin.demo.client.misc.VerySimpleForm;

/**
 * 
 */
public class BasicForm extends VerySimpleForm
{
   // we'll use an EnhancedTextBox as it fires value change events as
   // you type, much more exciting for the demo (c:
   private TextBox givenName = new EnhancedTextBox();
   private TextBox surname = new EnhancedTextBox();
   private TextBox lettersInName = new TextBox();
   
   private TextBox age = new EnhancedTextBox();
   private TextBox ageInDogYears = new EnhancedTextBox();
   
   private String buttonGroupId = DOM.createUniqueId();
   private RadioButton maleRadio = new RadioButton(buttonGroupId, "Male");
   private RadioButton femaleRadio = new RadioButton(buttonGroupId, "Female");

   private CheckBox cabSavCheckBox = new CheckBox("Cab Sav");
   private CheckBox merlotCheckBox = new CheckBox("Merlot");
   private CheckBox shirazCheckBox = new CheckBox("Shiraz");
   
   private WidgetBinder widgets = new WidgetBinder();


   public BasicForm(BasicFormModel model)
   {
      // see the metadata demo to see how you can control
      // enabledness in the model.
      lettersInName.setEnabled(false);
      age.setVisibleLength(5);
      
      // bind our widgets to our model.  In normal practice I'd combine the
      // binding, wiget creation and form layout into some nice reusable methods.
      widgets.bind(model.givenName).to(givenName);
      widgets.bind(model.surname).to(surname);
      widgets.bind(model.age).to(age);
      widgets.bind(model.ageInDogYears).to(ageInDogYears);
      
      // here we're binding field to a staic display (HasText).  We can also use
      // a DisplayFormat here if we need to.  In this case the default ToStringFormat
      // will be used.
      widgets.bind(model.lettersInName).toLabel(lettersInName);

      // now lets bind a value using radio buttons
      widgets.bind(model.gender).withValue(Gender.MALE).to(maleRadio);
      widgets.bind(model.gender).withValue(Gender.FEMALE).to(femaleRadio);

      // and a list model to a collection of check boxes
      widgets.bind(model.favoriteWines).containingValue(Wine.CAB_SAV).to(cabSavCheckBox);
      widgets.bind(model.favoriteWines).containingValue(Wine.MERLOT).to(merlotCheckBox);
      widgets.bind(model.favoriteWines).containingValue(Wine.SHIRAZ).to(shirazCheckBox);

      // now layout the form
      addRow("Given Name", givenName);
      addRow("Surname", surname);
      addRow("Letters in name", lettersInName);
      addGap();
      addRow("Gender", maleRadio, femaleRadio);
      addRow("Age", age);
      addRow("Age (in dog years)", ageInDogYears);
      addGap();
      addRow("Favorite Wines", cabSavCheckBox, merlotCheckBox, shirazCheckBox);
   }
}