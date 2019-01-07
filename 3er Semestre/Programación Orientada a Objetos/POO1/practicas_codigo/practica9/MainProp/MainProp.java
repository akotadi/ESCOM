import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;
import java.io.Serializable;
import java.lang.reflect.*;
import java.awt.*;
public class MainProp {
  public static Object getProperty(Object o, String propertyName)
{
   if (o == null ||
       propertyName == null ||
       propertyName.length() < 1)
   {
      return null;
   }
   // --- Based on the property name build the getter method name ---
   String methodName = "get" +
                      propertyName.substring(0,1).toUpperCase() +
                      propertyName.substring(1);
   Object property = null;
   try {
      java.lang.Class c = o.getClass();
      java.lang.reflect.Method m = c.getMethod(methodName, null);
      property = m.invoke(o, null);
   } catch (NoSuchMethodException e) {
     // --- Handle exception --
   }  catch (SecurityException e) {
     // --- No permission; Handle exception --
   }
    catch (IllegalAccessException e) {
     // --- No permission; Handle exception --
   }
   catch (InvocationTargetException e) {
     // --- No permission; Handle exception --
   }
return property;
}
  public static void main(String[] argv) throws Exception {
    BeanInfo beanInfo = Introspector.getBeanInfo(Button.class);
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    Fruit f=new Fruit();
    Button b=new Button("Prueba");
    for (PropertyDescriptor pd : pds) {
      String propertyName = pd.getName();
      System.out.println("propertyName = " + propertyName);
      System.out.println("obj prop "+getProperty(b,propertyName));
    }
     try {
            //PropertyUtils.setProperty(f, "id", Long.valueOf(10));
            PropertyUtils.setProperty(b, "name", "Requiem");
            PropertyUtils.setProperty(f, "price", Double.valueOf(18.0));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
 
        System.out.println("Track = " + b);
     
  }
  public static class Fruit implements Serializable {
  private Long id;

  private String name;

  private double price;

  public Fruit() {
	name="limon";
	price=3.0;
	id=1000L;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
//setField(Object target, String name,Object value)
}

}

