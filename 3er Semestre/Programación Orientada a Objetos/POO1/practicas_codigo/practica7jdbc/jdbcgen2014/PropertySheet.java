import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


/**
   A component filled with editors for all editable properties 
   of an object.
*/
public class PropertySheet extends JPanel
{
   /**
      Constructs a property sheet that shows the editable
      properties of a given object.
      @param object the object whose properties are being edited
   */
   public PropertySheet(Object bean)
   {
      try
      {
         BeanInfo info 
            = Introspector.getBeanInfo(bean.getClass());
         PropertyDescriptor[] descriptors 
            = (PropertyDescriptor[])info.getPropertyDescriptors().clone();      
         setLayout(new FormLayout());
         for (int i = 0; i < descriptors.length; i++)
         {
            PropertyEditor editor 
               = getEditor(bean, descriptors[i]);
            if (editor != null)
            {
               add(new JLabel(descriptors[i].getName()));
               add(getEditorComponent(editor));
            }
         }
      }
      catch (IntrospectionException exception)
      {
         exception.printStackTrace();
      }
   }

   /**
      Gets the property editor for a given property,
      and wires it so that it updates the given object.
      @param bean the object whose properties are being edited
      @param descriptor the descriptor of the property to
      be edited
      @return a property editor that edits the property
      with the given descriptor and updates the given object
   */
   public PropertyEditor getEditor(final Object bean,
      PropertyDescriptor descriptor)
   {
      try
      {
         Method getter = descriptor.getReadMethod();
         if (getter == null) return null;
         final Method setter = descriptor.getWriteMethod();
         if (setter == null) return null;
         Class type = descriptor.getPropertyType();
         PropertyEditor ed = null;
         Class editorClass = descriptor.getPropertyEditorClass();
         if (editorClass != null)            
            ed = (PropertyEditor) editorClass.newInstance();
         else
            ed = PropertyEditorManager.findEditor(type);
         if (ed == null && Enum.class.isAssignableFrom(type))
            ed = new EnumEditor(type);
         if (ed == null) return null;

         final PropertyEditor editor = ed;

         Object value = getter.invoke(bean, new Object[] {});
         editor.setValue(value);
         editor.addPropertyChangeListener(new
            PropertyChangeListener()
            {
               public void propertyChange(PropertyChangeEvent event)
               {
                  try
                  {
                     setter.invoke(bean, 
                        new Object[] { editor.getValue() });
                     fireStateChanged(null);
                  }
                  catch (IllegalAccessException exception)
                  {
                     exception.printStackTrace();
                  }
                  catch (InvocationTargetException exception)
                  {
                     exception.printStackTrace();
                  }
               }
            });
         return editor;
      }
      catch (InstantiationException exception)
      {
         exception.printStackTrace();
         return null;
      }
      catch (IllegalAccessException exception)
      {
         exception.printStackTrace();
         return null;
      }
      catch (InvocationTargetException exception)
      {
         exception.printStackTrace();
         return null;
      }
   }

   /**
      Wraps a property editor into a component.
      @param editor the editor to wrap
      @return a button (if there is a custom editor), 
      combo box (if the editor has tags), or text field (otherwise)
   */      
   public Component getEditorComponent(final PropertyEditor editor)   
   {      
      String[] tags = editor.getTags();
      String text = editor.getAsText();
      if (editor.supportsCustomEditor())
         return editor.getCustomEditor();         
      else if (tags != null)
      {
         // make a combo box that shows all tags
         final JComboBox comboBox = new JComboBox(tags);
         comboBox.setSelectedItem(text);
         comboBox.addItemListener(new
            ItemListener()
            {
               public void itemStateChanged(ItemEvent event)
               {
                  if (event.getStateChange() == ItemEvent.SELECTED)
                     editor.setAsText(
                        (String)comboBox.getSelectedItem());
               }
            });
         return comboBox;
      }
      else 
      {
         final JTextField textField = new JTextField(text, 10);
         textField.getDocument().addDocumentListener(new
            DocumentListener()
            {
               public void insertUpdate(DocumentEvent e) 
               {
                  try
                  {
                     editor.setAsText(textField.getText());
                  }
                  catch (IllegalArgumentException exception)
                  {
                  }
               }
               public void removeUpdate(DocumentEvent e) 
               {
                  try
                  {
                     editor.setAsText(textField.getText());
                  }
                  catch (IllegalArgumentException exception)
                  {
                  }
               }
               public void changedUpdate(DocumentEvent e) 
               {
               }
            });
         return textField;
      }
   }

   /**
      Adds a change listener to the list of listeners.
      @param listener the listener to add
   */
   public void addChangeListener(ChangeListener listener)
   {
      changeListeners.add(listener);
   }

   /**
      Notifies all listeners of a state change.
      @param event the event to propagate
   */
   private void fireStateChanged(ChangeEvent event)
   {
      for (ChangeListener listener : changeListeners)
         listener.stateChanged(event);
   }
   
   private ArrayList<ChangeListener> changeListeners 
         = new ArrayList<ChangeListener>();
}

