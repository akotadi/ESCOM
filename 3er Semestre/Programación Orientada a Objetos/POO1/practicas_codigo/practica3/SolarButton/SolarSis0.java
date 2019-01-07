import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.picking.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;


public class SolarSis0 extends MouseAdapter implements ActionListener 
{
	private PickCanvas pickCanvas;
	private JComboBox jcb;
	private JTextArea jta;

public SolarSis0(){

	JFrame frame = new JFrame("Planetario");

    GraphicsConfiguration config =           SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas = new Canvas3D(config);
    canvas.setSize(400, 400);
    SimpleUniverse universe = new SimpleUniverse(canvas);
BranchGroup group = new BranchGroup();
	Appearance appsol = new Appearance();
	Appearance appearth = new Appearance();
	TextureLoader tex = new TextureLoader("TIERRA.JPG", null);
	appearth.setTexture(tex.getTexture());
	tex = new TextureLoader("SOL.JPG", null);
	appsol.setTexture(tex.getTexture());
Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
earth.setUserData("Tierra");
Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
sol.setUserData("Sol");
TransformGroup earthRotXformGroup = rotate(earth, new Alpha(-1, 1250));
TransformGroup solRotXformGroup = rotate(sol, new Alpha(-1, 1250));

TransformGroup earthTransXformGroup = translate(earthRotXformGroup, new Vector3f(0.0f, 0.0f, 0.7f));
TransformGroup earthRotGroupXformGroup = rotate(earthTransXformGroup, new Alpha(-1, 5000));

	group.addChild(earthRotGroupXformGroup);
	group.addChild(solRotXformGroup);
universe.getViewingPlatform().setNominalViewingTransform();
universe.addBranchGraph(group);

frame.addWindowListener(new WindowAdapter() {

       public void windowClosing(WindowEvent winEvent) {

          System.exit(0);

       }

   	 });
    JPanel jp=new JPanel();
    frame.setLayout(new BorderLayout());
    Vector nomPlanet=new Vector();
    nomPlanet.addElement("Sol");
    nomPlanet.addElement("Tierra");
    nomPlanet.addElement("Marte");
    jcb=new JComboBox(nomPlanet);
    jcb.addActionListener(this);
    jta=new JTextArea("informacion",5,20);
    
    jp.add(jcb);
    jp.add(jta);
    frame.add("Center",canvas);
    frame.add("South", jp);
    pickCanvas = new PickCanvas(canvas, group);

    pickCanvas.setMode(PickCanvas.BOUNDS);

    canvas.addMouseListener(this);

    frame.pack();

    frame.setVisible(true);

}

public static void main(String[] args) {
new SolarSis0();
}

TransformGroup rotate(Node node, Alpha alpha) {

TransformGroup xformGroup = new TransformGroup();
xformGroup.setCapability(
TransformGroup.ALLOW_TRANSFORM_WRITE);

//Create an interpolator for rotating the node.
RotationInterpolator interpolator =
new RotationInterpolator(alpha, xformGroup);

//Establish the animation region for this
// interpolator.
interpolator.setSchedulingBounds(new BoundingSphere(
new Point3d(0.0, 0.0, 0.0), 1.0));

//Populate the xform group.
xformGroup.addChild(interpolator);
xformGroup.addChild(node);

return xformGroup;

}

TransformGroup translate(Node node, Vector3f vector) {

Transform3D transform3D = new Transform3D();
transform3D.setTranslation(vector);
TransformGroup transformGroup =
new TransformGroup();
transformGroup.setTransform(transform3D);

transformGroup.addChild(node);
return transformGroup;
}//end translate

public void mouseClicked(MouseEvent e)

{

    pickCanvas.setShapeLocation(e);

    PickResult result = pickCanvas.pickClosest();

    if (result == null) {

       System.out.println("Nothing picked");

    } else {

       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

       if (p != null) {
          System.out.println( "UserData1: "+p.getUserData() );
          System.out.println(p.getClass().getName());
	     jta.setText(""+p.getUserData());

       } else if (s != null) {
		//if(s==(Shape3D)yellowSph)
		//   System.out.println("Sol :::::");
           System.out.println( "UserData2: "+s.getUserData() );
             System.out.println(s.getClass().getName());

	     jta.setText(s.getClass().getName());

       } else{

          System.out.println("null");

       }

    }

}
 public void actionPerformed(ActionEvent e){
	  String nombre=(String)jcb.getSelectedItem();
          jta.append(nombre);
          
  }

}
