import java.beans.*;
import java.lang.reflect.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;

public class PanelHojaProp extends Panel {
    PanelHojaProp(Frame frame) {
	this.frame = frame;
    }
    synchronized void setTarget(Object targ) {	
	frame.removeAll();	
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
                //editor.addPropertyChangeListener(adaptor);
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
	frame.add(this);
        frame.setSize(maxWidth,maxHeight);
	frame.add(this);
	setVisible(true);
    }
    private Frame frame;  
    private Object target;
    private PropertyDescriptor properties[];
    private PropertyEditor editors[];
    private Object values[];
    private Component views[];
    private Label labels[];
    private static int hPad = 4;
    private static int vPad = 4;
    private int maxHeight = 500;
    private int maxWidth = 300;
}
