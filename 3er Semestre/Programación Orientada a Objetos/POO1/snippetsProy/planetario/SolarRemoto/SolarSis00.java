import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;
public class SolarSis implements ActionListener {
	private JComboBox jcb;	
public SolarSis(){	 
	BranchGroup group = new BranchGroup();
	Appearance appsol = new Appearance();
	Appearance appearth = new Appearance();
        TextureLoader tex=new TextureLoader("TIERRA.JPG", null);
	appearth.setTexture(tex.getTexture());
	tex=new TextureLoader("SOL.JPG", null);
	appsol.setTexture(tex.getTexture());
Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
TransformGroup earthRotXformGroup = rotate(earth, new Alpha(-1, 1250));
TransformGroup solRotXformGroup = rotate(sol, new Alpha(-1, 1250));
TransformGroup earthTransXformGroup = translate(earthRotXformGroup, new Vector3f(0.0f, 0.0f, 0.7f));
TransformGroup earthRotGroupXformGroup = rotate(earthTransXformGroup, new Alpha(-1, 5000));
	group.addChild(earthRotGroupXformGroup);
	group.addChild(solRotXformGroup);
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas = new Canvas3D(config);
    canvas.setSize(400, 400);
    SimpleUniverse universe = new SimpleUniverse(canvas);
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(group);  
    Vector nomPlanet=new Vector();
    nomPlanet.addElement("Sol"); nomPlanet.addElement("Tierra");
    jcb=new JComboBox(nomPlanet);
    jcb.addActionListener(this);
    JPanel jp=new JPanel(); jp.add(jcb);   
    JFrame f = new JFrame("Planetario");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    f.setLayout(new BorderLayout());
    f.add("Center",canvas); f.add("South", jp);
    f.pack(); f.setVisible(true);
}
public static void main(String a[]) { new SolarSis();}
TransformGroup rotate(Node node, Alpha alpha) {
	TransformGroup xformGroup = new TransformGroup();
	xformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
RotationInterpolator interpolator =new RotationInterpolator(alpha, xformGroup);
interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));
	xformGroup.addChild(interpolator); xformGroup.addChild(node);
	return xformGroup;
}
TransformGroup translate(Node node, Vector3f vector) {
	Transform3D transform3D = new Transform3D();
	transform3D.setTranslation(vector);
	TransformGroup transformGroup =new TransformGroup();
	transformGroup.setTransform(transform3D); 
        transformGroup.addChild(node);
	return transformGroup;
}
 public void actionPerformed(ActionEvent e){
	  System.out.println((String)jcb.getSelectedItem());       
  }
}                                                                         
