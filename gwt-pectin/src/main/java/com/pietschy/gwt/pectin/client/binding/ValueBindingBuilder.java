package com.pietschy.gwt.pectin.client.binding;

import com.google.gwt.user.client.ui.HasText;
import com.pietschy.gwt.pectin.client.channel.Destination;
import com.pietschy.gwt.pectin.client.command.ParameterisedCommand;
import com.pietschy.gwt.pectin.client.value.ValueModel;
import com.pietschy.gwt.pectin.client.value.ValueTarget;

/**
 * Created by IntelliJ IDEA.
 * User: andrew
 * Date: May 22, 2010
 * Time: 11:25:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class ValueBindingBuilder<T>
{
   private BindingBuilderCallback callback;
   private ValueModel<T> model;

   public ValueBindingBuilder(ValueModel<T> model, BindingBuilderCallback callback)
   {
      this.callback = callback;
      this.model = model;
   }

   protected ValueModel<T> getModel()
   {
      return model;
   }

   public void to(ValueTarget<T> widget)
   {
      getCallback().onBindingCreated(new ValueModelToValueTargetBinding<T>(model, widget),
                                     widget);
   }

   public void to(final ParameterisedCommand<T> command)
   {
      AbstractBinding binding = new ValueModelToValueTargetBinding<T>(model, new ValueTarget<T>()
      {
         public void setValue(T value)
         {
            command.execute(value);
         }
      });
      getCallback().onBindingCreated(binding, command);
   }

   public void to(final Destination<T> destination)
   {
      AbstractBinding binding = new ValueModelToValueTargetBinding<T>(model, new ValueTarget<T>()
      {
         public void setValue(T value)
         {
            destination.receive(value);
         }
      });
      getCallback().onBindingCreated(binding, destination);
   }

   public DisplayFormatBuilder<T> toLabel(HasText label)
   {
      ValueModelToHasTextBinding<T> binding = new ValueModelToHasTextBinding<T>(model, label);
      getCallback().onBindingCreated(binding, label);
      return new DisplayFormatBuilder<T>(binding);
   }

   protected BindingBuilderCallback getCallback()
   {
      return callback;
   }
}