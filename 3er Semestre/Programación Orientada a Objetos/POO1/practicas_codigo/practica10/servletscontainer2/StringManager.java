import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.net.URLClassLoader;
public class StringManager { 
            private ResourceBundle bundle;       
            private static org.apache.commons.logging.Log log=
                org.apache.commons.logging.LogFactory.getLog( StringManager.class );
  
             private StringManager(String packageName) {
                String bundleName = packageName + ".LocalStrings";
                try {
                    bundle = ResourceBundle.getBundle(bundleName);
                    return;
                } catch( MissingResourceException ex ) {
                    // Try from the current loader ( that's the case for trusted apps )
                    ClassLoader cl=Thread.currentThread().getContextClassLoader();
                    if( cl != null ) {
                        try {
                             bundle=ResourceBundle.getBundle(bundleName, Locale.getDefault(), cl);
                            return;
                        } catch(MissingResourceException ex2 ) {
                        }
                    }
                    if( cl==null )
                        cl=this.getClass().getClassLoader();
        
                    if (log.isDebugEnabled())
                        log.debug("Can't find resource " + bundleName +
                             " " + cl);
                    if( cl instanceof URLClassLoader ) {
                        if (log.isDebugEnabled()) 
                            log.debug( ((URLClassLoader)cl).getURLs());
                    }
                }
            }
             public String getString(String key) {
                 return MessageFormat.format(getStringInternal(key), (Object [])null);
             }
             protected String getStringInternal(String key) {
                  if (key == null) {
                       String msg = "key is null";
          
                      throw new NullPointerException(msg);
                  }   
                  String str = null;
          
                  if( bundle==null )
                      return key;
                 try {
                      str = bundle.getString(key);
                 } catch (MissingResourceException mre) {
                     str = "Cannot find message associated with key '" + key + "'";
                 }
         
                 return str;
             }
             public String getString(String key, Object[] args) {
                 String iString = null;
                 String value = getStringInternal(key);
                 try {
                     // ensure the arguments are not null so pre   .  VM's don't barf
                     Object nonNullArgs[] = args;
                     for (int i= 0; i<args.length; i++) {
                         if (args[i] == null) {
                             if (nonNullArgs==args) nonNullArgs=(Object[])args.clone();
                              nonNullArgs[i] = "null";
                         }
                     }
         
                     iString = MessageFormat.format(value, nonNullArgs);
                 } catch (IllegalArgumentException iae) {
                     StringBuffer buf = new StringBuffer();
                     buf.append(value);
                     for (int i =  0; i < args.length; i++) {
                         buf.append(" arg[" + i + "]=" + args[i]);
                      }
                     iString = buf.toString();
                 }
                 return iString;
             }
             public String getString(String key, Object arg) {
                 Object[] args = new Object[] {arg};
                 return getString(key, args);
             }
              public String getString(String key, Object arg1  , Object arg2 ) {
                 Object[] args = new Object[] {arg1  , arg2 };
                 return getString(key, args);
             }
            public String getString(String key, Object arg1  , Object arg2 ,
                                    Object arg3 ) {
                Object[] args = new Object[] {arg1  , arg2 , arg3 };
                 return getString(key, args);
              }
            public String getString(String key, Object arg1  , Object arg2 ,
                                    Object arg3 , Object arg4 ) {
                Object[] args = new Object[] {arg1  , arg2 , arg3 , arg4 };
                return getString(key, args);
            }
            private static Hashtable managers = new Hashtable();   
            public synchronized static StringManager getManager(String packageName) {
                StringManager mgr = (StringManager)managers.get(packageName);
        
                if (mgr == null) {
                    mgr = new StringManager(packageName);
                    managers.put(packageName, mgr);
                }
                 return mgr;
            }
        }
