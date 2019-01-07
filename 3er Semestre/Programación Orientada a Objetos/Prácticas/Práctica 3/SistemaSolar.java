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

public class SistemaSolar implements ActionListener{

	private JComboBox<String> jcb;	


	public SistemaSolar()
	{

		BranchGroup group = new BranchGroup();
		Appearance ApTierra=new Appearance();
		Appearance AppSol = new Appearance();
		Appearance ApMarte = new Appearance();
		Appearance ApVenus = new Appearance();

		//TEXTURAS
		TextureLoader textura=new TextureLoader("Tierra.JPG",null);
		ApTierra.setTexture(textura.getTexture());
		
		textura=new TextureLoader("SOL.JPG",null);
		AppSol.setTexture(textura.getTexture());
	
		textura=new TextureLoader("MARTE.JPG",null);
		ApMarte.setTexture(textura.getTexture());

		textura=new TextureLoader("VENUS.JPG",null);
		ApVenus.setTexture(textura.getTexture());

		//ESFERAS
		Sphere Sol = new Sphere(0.2f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, AppSol);

		Sphere Tierra=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, ApTierra);
		
		Sphere Marte = new Sphere(0.0266f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, ApMarte);

		Sphere Venus=new Sphere(0.04749f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, ApVenus);
		
		//MOVIMIENTO DEL SOL
		TransformGroup RotacionXSol = rotate(Sol, new Alpha(-1, 1200));


		//MOVIMIENTO DE VENUS
		TransformGroup RotacionXVenus = rotate(Venus, new Alpha(-1, 20));//Durante una vuelta al solda 585 sobre el

		TransformGroup TraslacionXVenus = translate(RotacionXVenus, new Vector3f(0.0f, 0.0f, 0.6074f));
		TransformGroup RotacionXSistemaV = rotate(TraslacionXVenus, new Alpha(-1, 11900));


		//MOVIMIENTO DE LA TIERRA
		TransformGroup RotacionXTierra = rotate(Tierra, new Alpha(-1, 27));

		TransformGroup TraslacionXTierra = translate(RotacionXTierra, new Vector3f(0.0f, 0.0f, 0.75f));
		TransformGroup RotacionXSistemaT = rotate(TraslacionXTierra, new Alpha(-1, 10000));
	
		//MOVIMIENTO DE MARTE
		TransformGroup RotacionXMarte = rotate(Marte, new Alpha(-1, 10));//Gira aprox 780 veces en una vuelta al rededor del sol

		TransformGroup TraslacionXMarte = translate(RotacionXMarte, new Vector3f(0.0f, 0.0f, 0.9866f));
		TransformGroup RotacionXSistemaM = rotate(TraslacionXMarte, new Alpha(-1, 8200));




	group.addChild(RotacionXSol);
	group.addChild(RotacionXSistemaT);
	group.addChild(RotacionXSistemaV);
	group.addChild(RotacionXSistemaM);


	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	
	Canvas3D canvas = new Canvas3D(config);
    	canvas.setSize(500, 350);
	SimpleUniverse universo = new SimpleUniverse(canvas);

	
	universo.getViewingPlatform().setNominalViewingTransform();
    	universo.addBranchGraph(group); 	//Esto podria ser una razon	
	



	Vector<String> nomPlanet=new Vector<String>();//ERROR
    	nomPlanet.addElement("Sol"); 
    	nomPlanet.addElement("Venus");
	nomPlanet.addElement("Tierra");
	nomPlanet.addElement("Marte");
    	jcb=new JComboBox<String>(nomPlanet);//ERROR
    	jcb.addActionListener(this);
    	JPanel jp=new JPanel(); 
	jp.add(jcb);
	JFrame f = new JFrame("Planetario");
   	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.setLayout(new BorderLayout());
    	f.add("Center",canvas); 
	f.add("South",jp);
    	f.pack(); 
	f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{

	}





	TransformGroup rotate(Node node, Alpha alpha) 
	{
		TransformGroup xformGroup = new TransformGroup();
		xformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		RotationInterpolator interpolator =new RotationInterpolator(alpha, xformGroup);
		interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));
		xformGroup.addChild(interpolator); xformGroup.addChild(node);
		return xformGroup;
	}


	TransformGroup translate(Node node, Vector3f vector) 
	{
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(vector);
		TransformGroup transformGroup =new TransformGroup();
		transformGroup.setTransform(transform3D); 
		transformGroup.addChild(node);
		return transformGroup;
	}

	public static void main(String[] s)
	{

		new SistemaSolar();
	}
}
