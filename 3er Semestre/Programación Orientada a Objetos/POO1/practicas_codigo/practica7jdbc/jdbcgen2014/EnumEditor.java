import java.beans.*;

/**
   A property editor for enumerated types.
*/
public class EnumEditor extends PropertyEditorSupport
{
   /**
      Constructs a property editor for an enumerated type
      @param cl the class object for the enumerated type
   */
   public EnumEditor(Class cl)
   {
      this.cl = cl;
   }

   public String[] getTags()
   {
      try
      {
         Object[] values = (Object[]) cl.getMethod("values").invoke(null);
         String[] result = new String[values.length];
         for (int i = 0; i < values.length; i++)
            result[i] = values[i].toString();
         return result;
      }
      catch (Exception ex)
      {
         return null;
      }
   }

   public String getAsText()
   {
      return getValue().toString();
   }

   public void setAsText(String s)
   {
      setValue(Enum.valueOf(cl, s));
   }

   private Class cl;
}
