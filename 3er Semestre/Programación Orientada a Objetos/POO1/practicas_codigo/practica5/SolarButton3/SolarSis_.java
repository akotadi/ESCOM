import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.picking.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
public class SolarSis extends MouseAdapter implements ActionListener {	
protected Transform3D viewTransform = new Transform3D();
protected Vector3f viewVector;
protected ViewingPlatform vp = null;
protected TransformGroup viewTG = null;
private TransformGroup riderPos;
private TransformGroup earthRotX;
private TransformGroup solRotX;
private TransformGroup earthTransX;

SimpleUniverse universe;
private PickCanvas pickCanvas;
private JComboBox jcb;
private JTextArea jta;
final JEditorPane jt = new JEditorPane();
public SolarSis(){	 
	BranchGroup group = new BranchGroup();
	Appearance appsol = new Appearance();
	Appearance appearth = new Appearance();
        Appearance appluna = new Appearance();
        Appearance appmarte = new Appearance();
        TextureLoader tex=new TextureLoader("TIERRA.JPG", null);
	appearth.setTexture(tex.getTexture());
        tex=new TextureLoader("MARTE.JPG", null);
	appmarte.setTexture(tex.getTexture());
	tex=new TextureLoader("SOL.JPG", null);
        appsol.setTexture(tex.getTexture());
	tex=new TextureLoader("LUNA.JPG", null);
	appluna.setTexture(tex.getTexture());
	Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | 		
	Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
        earth.setUserData("Tierra");
        Sphere luna = new Sphere(0.019f, Primitive.GENERATE_NORMALS | 		
	Primitive.GENERATE_TEXTURE_COORDS, 32, appluna);   
        luna.setUserData("Luna");
        Sphere marte = new Sphere(0.045f, Primitive.GENERATE_NORMALS |		
	Primitive.GENERATE_TEXTURE_COORDS, 32, appmarte);
        marte.setUserData("Marte");
	Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | 
	Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
        sol.setUserData("Sol");
	earthRotX = rotate(earth, new Alpha(-1, 1250));
	solRotX = rotate(sol, new Alpha(-1, 1250));
	earthTransX = translate(earthRotX, new Vector3f(0.0f, 0.0f, 0.7f));
TransformGroup earthRotGroupX = rotate(earthTransX, new Alpha(-1, 5000));
    	group.addChild(earthRotGroupX);

TransformGroup lunaRotX = rotate(luna, new Alpha(-1, 1250));
TransformGroup lunaTransX = translate(lunaRotX, new Vector3f(0.0f, 0.0f, 0.1f));
TransformGroup lunaRotGroupX = rotate(lunaTransX, new Alpha(-1, 1000));
    	earthTransX.addChild(lunaRotGroupX);
	//group.addChild(lunaRotGroupX);

        TransformGroup marteRotX = rotate(marte, new Alpha(-1, 1250));
	TransformGroup marteTransX = translate(marteRotX, new Vector3f(0.0f, 0.0f, 0.5f));
TransformGroup marteRotGroupX = rotate(marteTransX, new Alpha(-1, 1000));
    	group.addChild(marteRotGroupX);

    	group.addChild(solRotX);
        //group.addChild(sol);
    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    	Canvas3D canvas = new Canvas3D(config); canvas.setSize(400, 400);
    	universe = new SimpleUniverse(canvas);
    	universe.getViewingPlatform().setNominalViewingTransform();
    	 
	solRotX.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);
        solRotX.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        solRotX.setCapability(Group.ALLOW_CHILDREN_WRITE);
        solRotX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        earthRotX.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);
        earthRotX.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        earthRotX.setCapability(Group.ALLOW_CHILDREN_WRITE);
        earthRotX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        vp = universe.getViewingPlatform();
        viewTG = vp.getViewPlatformTransform();
        /*earthTransX.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);
        earthTransX.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        earthTransX.setCapability(Group.ALLOW_CHILDREN_WRITE);
        earthTransX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        earthTransX.addChild(vp);*/
       
        universe.addBranchGraph(group); 
    	JFrame f = new JFrame("Planetario");
        JPanel jp=new JPanel();
    	f.setLayout(new BorderLayout());
    	Vector nomPlanet=new Vector();
    	nomPlanet.addElement("Sol");
    	nomPlanet.addElement("Tierra");
    	nomPlanet.addElement("Marte");
    	jcb=new JComboBox(nomPlanet);
    	jcb.addActionListener(this);
    	jta=new JTextArea("informacion",5,20);
    
    	jp.add(jcb);
    	jp.add(jta);
    	f.add("Center",canvas);
    	f.add("South", jp);
    	pickCanvas = new PickCanvas(canvas, group);

    	pickCanvas.setMode(PickCanvas.BOUNDS);

    	canvas.addMouseListener(this);
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.pack(); f.setVisible(true); 
}
public static void main(String a[]) { new SolarSis();}
TransformGroup rotate(Node node, Alpha alpha) {
	TransformGroup xformGroup = new TransformGroup();
	xformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
RotationInterpolator interpolator =new RotationInterpolator(alpha, xformGroup);
interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));
	xformGroup.addChild(interpolator); xformGroup.addChild(node);
	return xformGroup; }
TransformGroup translate(Node node, Vector3f vector) {
	Transform3D transform3D = new Transform3D();
	transform3D.setTranslation(vector);
	TransformGroup transformGroup =new TransformGroup();
	transformGroup.setTransform(transform3D); 
        transformGroup.addChild(node);
	return transformGroup; }
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
          if(p.getUserData().equals("Sol")){
        	viewVector = new Vector3f(.0f,.0f, .65f);
        	viewTransform.setTranslation(viewVector);
        
        	viewTG.setTransform(viewTransform);
        	vp.detach();
 	
        	solRotX.addChild(vp);
	}
	if(p.getUserData().equals("Tierra")){
        	viewVector = new Vector3f(.0f,.0f, .65f);
        	viewTransform.setTranslation(viewVector);
        
        	viewTG.setTransform(viewTransform);
        	vp.detach();
 	
        	earthRotX.addChild(vp);
	}

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
         if(nombre.equals("Sol")){
        	viewVector = new Vector3f(.0f,.0f, .65f);
        	viewTransform.setTranslation(viewVector);
        
        	viewTG.setTransform(viewTransform);
        	vp.detach();
 	
        	solRotX.addChild(vp);
                
	}
	if(nombre.equals("Tierra")){
        	viewVector = new Vector3f(.0f,.0f, .65f);
        	viewTransform.setTranslation(viewVector);
        
        	viewTG.setTransform(viewTransform);
        	vp.detach();
 	
        	earthRotX.addChild(vp);
	}
 }
}                                                                         
