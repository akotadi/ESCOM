import com.sun.j3d.utils.universe.*;
import java.net.URL;
import java.net.MalformedURLException;
import javax.media.j3d.*; 
import javax.vecmath.*;
import javax.swing.*;
import java.awt.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.lw3d.*;
import java.awt.event.*;
import java.util.*;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.picking.*;


public class GetModel {

    public static void add(String name, Group group, java.applet.Applet applet) {

       
        Scene s = get(name, applet);;


        group.addChild(s.getSceneGroup());
        Hashtable namedObjects = s.getNamedObjects();
        Enumeration e = namedObjects.keys();

        //Switch theSwitch = new Switch();
        //newBG.addChild(theSwitch);
        while (e.hasMoreElements()) {
            String el = (String) e.nextElement();
            System.out.print("name = "+ el);        
            //Object ob = namedObjects.get(el);
            //Class theClass =  ob.getClass();

        }
 
    }
    public static Scene get(String name, java.applet.Applet applet) {
        int flags = ObjectFile.RESIZE | ObjectFile.LOAD_ALL;
        ObjectFile f = new ObjectFile(flags);
        Scene s = null;
        //name = "C:\\homepc\\Lava\\converters\\dolphins2.obj";
        URL url = FindUrl.get(name, applet);
        System.out.println("Loading...");
        try {
            s = f.load(url);
            //s = l.load(url);
            System.out.println("Loaded!");
        }
        catch(Throwable t) {
            System.err.println("Error: "+t);
        }
        return s;
    }
    public static void showGroup(Group group) {
        Enumeration e = group.getAllChildren();
        while (e.hasMoreElements()) {
            Object ob = e.nextElement();
            System.out.print("    ");
            System.out.println(ob.getClass());
            if (ob instanceof Group) {
                System.out.print("(Group)");
                showGroup((Group)(ob));
            }
        }
    }
    public static void takeChildren(Group fromGroup, Group toGroup) {
        int numChildren = fromGroup.numChildren();
        Node repeatNode = null;
        Node firstNode = null;
        Node lastNode = null;
        for (int i=0; i < numChildren; i++) {
          Node dummyNode = new Group();
          Node child = fromGroup.getChild(i);

          //fromGroup.removeChild(i);
          fromGroup.setChild(dummyNode,i);
          if (i==0) {repeatNode = child.cloneNode(true);};
          if (i==1) firstNode= child;
          if (i==2) lastNode = child;
          //toGroup.addChild(child); //.cloneNode(true));
        }
        toGroup.addChild(firstNode);
        toGroup.addChild(repeatNode.cloneNode(true));
        toGroup.addChild(lastNode);
        toGroup.addChild(repeatNode);
     }
}