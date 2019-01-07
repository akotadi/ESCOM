import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.picking.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class SolarSis extends MouseAdapter implements ActionListener, Runnable {	
protected Transform3D viewTransform = new Transform3D();
protected Vector3f viewVector;
protected ViewingPlatform vp = null;
protected TransformGroup viewTG = null;
private TransformGroup earthRotX;
private TransformGroup solRotX;
private TransformGroup marteRotX;
private TransformGroup earthTransX;
SimpleUniverse universe;
private PickCanvas pickCanvas;
private JComboBox jcb;
final JEditorPane jt = new JEditorPane();
private Socket cliente;  
private ObjectInputStream oisNet;
private ObjectOutputStream oosNet;
private int puerto=5000;
private Thread hilo;
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
        marteRotX = rotate(marte, new Alpha(-1, 1250));
	TransformGroup marteTransX = translate(marteRotX, new Vector3f(0.0f, 0.0f, 0.5f));
TransformGroup marteRotGroupX = rotate(marteTransX, new Alpha(-1, 1000));
    	group.addChild(marteRotGroupX);
    	group.addChild(solRotX);
    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    	Canvas3D canvas = new Canvas3D(config); canvas.setSize(500, 500);
    	universe = new SimpleUniverse(canvas);
    	universe.getViewingPlatform().setNominalViewingTransform(); 
 	capacidad(solRotX); 
	capacidad(earthRotX);
	capacidad(marteRotX);
        vp = universe.getViewingPlatform();
        viewTG = vp.getViewPlatformTransform();  
        universe.addBranchGraph(group); 
    	JFrame f = new JFrame("Planetario");
        JPanel jp=new JPanel();
    	f.setLayout(new BorderLayout());
    	Vector nomPlanet=new Vector();
    	nomPlanet.addElement("Sol"); nomPlanet.addElement("Tierra");
    	nomPlanet.addElement("Marte");
        jt.setEditable(false);
        jt.addHyperlinkListener(new HyperlinkListener () {
              public void hyperlinkUpdate(final HyperlinkEvent e) {
        if (e.getEventType() == 
            HyperlinkEvent.EventType.ACTIVATED) {
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              Document doc = jt.getDocument();
              try {
                URL url = e.getURL();
                System.out.println("URL valido");
                jt.setPage(url);
              } catch (IOException io) {
                System.out.println("URL no valido");
                jt.setDocument (doc);
              }
            }
          });
        }
      }
    });
        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(jt);
    	jcb=new JComboBox(nomPlanet); jcb.addActionListener(this);
    	jp.add(jcb); jp.add(pane);
    	f.add("Center",canvas); f.add("South", jp);
    	pickCanvas = new PickCanvas(canvas, group);
    	pickCanvas.setMode(PickCanvas.BOUNDS);
    	canvas.addMouseListener(this);
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.pack(); f.setVisible(true); 
        int i=0;
   while(i==0)
   {
    	i=1;
    	System.out.println("Esperando por el servidor . . .");
    	try {
		cliente=new Socket("localhost", puerto);
    	} catch ( IOException e) {
            	System.out.println("Fallo creacion Socket");
            	i=0;
   	}
   }
   System.out.println("Connectado al servidor.");
   try {
            oisNet = getOISNet(cliente.getInputStream());
            oosNet = getOOSNet(cliente.getOutputStream()); 
   } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al crear los fujos de objeto"+e);
   }
   hilo= new Thread(this); hilo.start();
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
void capacidad(TransformGroup tg){
	tg.setCapability(Group.ALLOW_LOCAL_TO_VWORLD_READ);
        tg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        tg.setCapability(Group.ALLOW_CHILDREN_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); }
public void mouseClicked(MouseEvent e){
    pickCanvas.setShapeLocation(e);
    PickResult result = pickCanvas.pickClosest();
    if (result == null) {
       System.out.println("Nothing picked");
    } else {
       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
       if (p != null) {
        aviso((String)p.getUserData() ,p.getClass().getName());  
        envia((String)p.getUserData()); cambia((String)p.getUserData());
	carga((String)p.getUserData());
       } else if (s != null) {
	     aviso((String)s.getUserData() ,s.getClass().getName());
       } else{
          System.out.println("null");
       }
    }
}
 public void actionPerformed(ActionEvent e){
	String nombre=(String)jcb.getSelectedItem();
	envia(nombre); cambia(nombre); carga(nombre); }
 public void run(){
	Object c=null;
	int j,k=0;
   	for(;;){
    		j=0;
    		try { c=oisNet.readObject(); } 
		catch (IOException e) {
			System.out.println("E/S ex"+e);  j=1;
                } catch (ClassNotFoundException ex) {
                     	System.out.println("Class no found"+ex); j=1;
		} 
    		if (j==0) {
 		        if(c instanceof InfoPlanet){      
                           InfoPlanet i=(InfoPlanet)c;  
                                System.out.println("recibi "+i.getNombre());
				cambia(i.getNombre()); carga(i.getNombre());
			}         
                 }
        }
}
void cambia(String nombre){
	viewVector = new Vector3f(.0f,.0f, .65f);
        viewTransform.setTranslation(viewVector);   
        viewTG.setTransform(viewTransform);  vp.detach();
	if(nombre.equals("Sol"))	
        	solRotX.addChild(vp);       
	if(nombre.equals("Tierra"))
        	earthRotX.addChild(vp);
	if(nombre.equals("Marte"))
        	marteRotX.addChild(vp);      
}
void envia(String nombre){     
        try { oosNet.writeObject( new InfoPlanet(nombre) ); } 
	catch (IOException ex) { ex.printStackTrace(); }	
}
void carga(String nombre){
	try { jt.setPage ("http://localhost:8080/"+nombre+".html");} 
	catch (IOException ex) {System.out.println("URL no valido");}
}
void aviso(String nombre, String clase){
  	System.out.println( "UserData1: "+ nombre+" Clase "+clase );  }
ObjectOutputStream getOOSNet(OutputStream os) throws IOException {
	return new ObjectOutputStream(os); }
ObjectInputStream getOISNet(InputStream is) throws IOException {
	return new ObjectInputStream(is);  }
}                                                                         
