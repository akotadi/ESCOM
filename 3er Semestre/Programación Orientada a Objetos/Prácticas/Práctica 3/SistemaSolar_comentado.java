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

	private JComboBox<String> combo;


	public SistemaSolar()
	{
/*BranchGroup tiene como objetivo agrupar los objetos de la escena gráfica para una mejor organización
Puede referenciar a varios objetos de la clase 'node'
*/
		BranchGroup group = new BranchGroup();
		Appearance ApTierra=new Appearance();
		TextureLoader textura=new TextureLoader("Tierra.JPG",null);//No se para que sirve el null
		ApTierra.setTexture(textura.getTexture());
		Sphere Tierra=new Sphere(0.045f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, ApTierra);
/*la primera parte del constructor deterina el radio de la esfera, la segunda PRIMITIVE FLAGS, la tercera las divisiones y la cuarta la apariencia*/

		TransformGroup RotacionXTierra = rotate(Tierra/*Objeto al que se le aplica*/, new Alpha(1, 1250));//Alpha determina el número de ciclo y la velocidad del ciclo

		//TransformGroup TraslacionXTierra = translate(RotacionXTierra, new Vector3f(0.0f, 0.0f, 0.7f));//



/*-------------------------Añadimos los objetos al Branch----------------------------------*/
	group.addChild(RotacionXTierra);


	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	/*eL CANVAS es encargado de contener todos los objetos 3D*/
	Canvas3D canvas = new Canvas3D(config);
    	canvas.setSize(900, 700);
	SimpleUniverse universo = new SimpleUniverse(canvas);
/*SimpleUniverse: Esta clase, como su nombre lo indica, nos hace más fácil el crear nuestro universo gráfico. Si no fuera por él, tendríamos que crear unas ramas en el árbol del escenario gráfico (ramas de vista gráfica), en el que se describiría entre otras cosas: el tipo de perspectiva, la situación de las cámaras, el volumen visible y en general todo aquello que nos dice como vamos a ver el escenario gráfico. Con este clase se reduce significativamente el tiempo y el esfuerzo para crear estas ramas.*/
	
	universo.getViewingPlatform().setNominalViewingTransform();
    	universo.addBranchGraph(group); 		
	

	JFrame f = new JFrame("Planetario");
   	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.setLayout(new BorderLayout());
    	f.add("Center",canvas); //f.add("South", jp);
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
