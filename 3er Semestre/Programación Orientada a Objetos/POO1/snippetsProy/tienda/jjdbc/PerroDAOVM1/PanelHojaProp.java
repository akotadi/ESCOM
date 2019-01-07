import java.beans.*;
import java.lang.reflect.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.*;
public class PanelHojaProp extends JPanel {
    PanelHojaProp(JFrame frame) {
	this.frame = frame;
        pnlRaiz = (JPanel)frame.getContentPane();
    }
    synchronized void setTarget(Object targ) {	
	pnlRaiz.removeAll();	
	removeAll();
	if (target != null) {
	    setVisible(false);
	}
	target = targ;
        try {
	    BeanInfo bi = Introspector.getBeanInfo(target.getClass());
	    properties = bi.getPropertyDescriptors();
	} catch (IntrospectionException ex) {
	    System.out.println("PropertySheet: No puedo introspect"+ex);
	    return;
	}
System.out.println("tama "+properties.length+"nomclase ("+ target.getClass()+")");
        setLayout(new GridLayout(properties.length,2));
	editors = new PropertyEditor[properties.length];
	values = new Object[properties.length];
	views = new Component[properties.length];
	labels = new Label[properties.length];
        EditedAdaptor adaptor = new EditedAdaptor(this);
	for (int i = 0; i < properties.length; i++) {
	    if (properties[i].isHidden() || properties[i].isExpert()) {
		continue;
	    }
	    String name = properties[i].getDisplayName();
	    Class type = properties[i].getPropertyType();
	    Method getter = properties[i].getReadMethod();
	    Method setter = properties[i].getWriteMethod();
		 System.out.println("Nombre for ("+i+")"+name);
	    if (getter == null || setter == null) {
		continue;
	    }
	    Component view = null;
	    try {
		Object args[] = { };
		Object value = getter.invoke(target, args);
	        values[i] = value;
	        PropertyEditor editor = null;
	        Class pec = properties[i].getPropertyEditorClass();
		if (pec != null) {
		    try {
			editor = (PropertyEditor)pec.newInstance();
		    } catch (Exception ex) {
			// Drop through.
		    }
		}
		if (editor == null) {
		    editor = PropertyEditorManager.findEditor(type);
		}
	        editors[i] = editor;
	        if (editor == null) {
		    String getterClass = properties[i].getReadMethod().getDeclaringClass().getName();
		    if (getterClass.indexOf("java.") != 0) {
		        System.err.println("Warning: Can't find public property editor for property \""
				 + name + "\".  Skipping.");
		    }
		    continue;
	        }
		if (value == null) {
		    String getterClass = properties[i].getReadMethod().getDeclaringClass().getName();
		    if (getterClass.indexOf("java.") != 0) {
		        System.err.println("Warning: Property \"" + name 
				+ "\" has null initial value.  Saltando.");	
		    }
		    continue;
		}
	        editor.setValue(value);     
                editor.addPropertyChangeListener(adaptor);
		if (editor.isPaintable() && editor.supportsCustomEditor()) {
		    view = new PropertyCanvas(frame, editor);
		} else if (editor.getTags() != null) {
		    view = new PropertySelector(editor);
		} else if (editor.getAsText() != null) {
		    String init = editor.getAsText();
		    view = new PropertyText(editor);
		} else {
		    System.err.println("Warning: Property \"" + name 
				+ "\" has non-displayabale editor.  Saltando.");
		    continue;
		}
	    } catch (InvocationTargetException ex) {
		System.err.println("Saltando propiedad " + name + " ; excepcion on target: " + ex.getTargetException());
		ex.getTargetException().printStackTrace();
		continue;
	    } catch (Exception ex) {
		System.err.println("Skipping property " + name + " ; exception: " + ex);
		ex.printStackTrace();
		continue;
	    }
	    labels[i] = new Label(name, Label.RIGHT);
	    add(labels[i]);
	    views[i] = view;
	    add(views[i]);
	}
        //add(new Button("inserta"))
	//frame.add("Center",this);
        processEvents = true;
        frame.setSize(maxWidth,maxHeight);
	pnlRaiz.add(this);
	setVisible(true);
    }
    synchronized void wasModified(PropertyChangeEvent evt) {

	if (!processEvents) {
	    return;
	}

	if (evt.getSource() instanceof PropertyEditor) {
	    PropertyEditor editor = (PropertyEditor) evt.getSource();
	    for (int i = 0 ; i < editors.length; i++) {
	        if (editors[i] == editor) {
		    PropertyDescriptor property = properties[i];
		    Object value = editor.getValue();
		    values[i] = value;
		    Method setter = property.getWriteMethod();
		    try {
		        Object args[] = { value };
		        args[0] = value;
		        setter.invoke(target, args);

		    } catch (InvocationTargetException ex) {
		      if (ex.getTargetException()
		                instanceof PropertyVetoException) {
			//warning("Vetoed; reason is: " 
			//        + ex.getTargetException().getMessage());
			// temp dealock fix...I need to remove the deadlock.
			System.err.println("WARNING: Vetoed; reason is: " 
					+ ex.getTargetException().getMessage());
		      }
		      else
		        error("InvocationTargetException while updating " 
		                + property.getName(), ex.getTargetException());
		    } catch (Exception ex) {
		        error("Unexpected exception while updating " 
		                + property.getName(), ex);
	            }
	            if (views[i] != null && views[i] instanceof PropertyCanvas) {
		        views[i].repaint();
	            }
		    break;
		}
	    }
	}

	// Now re-read all the properties and update the editors
	// for any other properties that have changed.
	for (int i = 0; i < properties.length; i++) {
	    Object o;
	    try {
	        Method getter = properties[i].getReadMethod();
	        Object args[] = { };
	        o = getter.invoke(target, args);
	    } catch (Exception ex) {
		o = null;
	    }
	    if (o == values[i] || (o != null && o.equals(values[i]))) {
	        // The property is equal to its old value.
		continue;
	    }
	    values[i] = o;
	    // Make sure we have an editor for this property...
	    if (editors[i] == null) {
		continue;
	    }
	    // The property has changed!  Update the editor.
	    editors[i].setValue(o);
	    if (views[i] != null) {
		views[i].repaint();
	    }
	}
        System.out.println("modifi"+target.toString());
	// Make sure the target bean gets repainted.
	if (Beans.isInstanceOf(target, Component.class)) {
	    ((Component)(Beans.getInstanceOf(target, Component.class))).repaint();
	}
    }
    private void error(String message, Throwable th) {
	String mess = message + ":\n" + th;
	System.err.println(message);
	th.printStackTrace();
	// Popup an ErrorDialog with the given error message.
	new ErrorDialog(frame, mess);

    }
    private JPanel pnlRaiz;  
    private JFrame frame;  
    private Object target;
    private PropertyDescriptor properties[];
    private PropertyEditor editors[];
    private Object values[];
    private Component views[];
    private Label labels[];
    private boolean processEvents;
    private static int hPad = 4;
    private static int vPad = 4;
    private int maxHeight = 500;
    private int maxWidth = 300;
}
