import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Tamagochi extends Frame  {
private Canvas3D canvas3D;
private Appearance ap;
private Texture feliz, enfermo;
private static Texture texture;
private TextureLoader loader;

private JPanel jp;
private JButton bcomer;
private JLabel jl;
private JProgressBar progressBar = new JProgressBar();
private EventHandler eh; 
static Sphere sphere;

int val;
public void changeTexture(Texture texture, String image) {
     	loader = new TextureLoader(image, "RGB",this);
    	texture = loader.getTexture();
	texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.REPLACE);	
	ap.setTextureAttributes(texAttr);
	ap.setTexture(texture);
}

public Tamagochi(){
   super("Tamagochi 3D");
   val=0;
   setResizable(false); setSize(600, 700);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   eh = new EventHandler();  
   bcomer=new JButton("Comer");
   bcomer.addActionListener(eh);
   jl=new JLabel("Hambre");
   progressBar.setValue(0);
   jp=new JPanel();
   jp.add(jl); jp.add(progressBar);
   jp.add(bcomer);
   add("North", jp); add("Center",canvas3D);
   addWindowListener(eh);
   setup3DGraphics();
   setVisible(true);
}
void setup3DGraphics(){
   DirectionalLight light1;
   SimpleUniverse universe = new SimpleUniverse(canvas3D);
   BranchGroup group = new BranchGroup();
   int primflags = Primitive.GENERATE_NORMALS +
   Primitive.GENERATE_TEXTURE_COORDS/*+Primitive.ENABLE_APPEARANCE_MODIFY*/ ; 

    //ap.setTextureUnitState(textureUnitState);  
   sphere = new Sphere(0.5f, primflags, ap);
   sphere.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY); 
   ap = sphere.getAppearance();
   ap.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
    ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
    ap.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    group.addChild(sphere);
   universe.getViewingPlatform().setNominalViewingTransform();
   universe.addBranchGraph(group);
}
public static void main(String[] args) { 
	new Tamagochi().changeTexture(texture, "carafeliz.jpg"); 
}
class EventHandler extends WindowAdapter implements ActionListener {  
  public void actionPerformed(ActionEvent e) {  
     JButton btn=(JButton)e.getSource();
     if(btn==bcomer){ 
        val=100-val;
        System.out.println("Tamagochi come 2#### "+val); 
        if(val == 100)
		changeTexture(texture, "caraenfermo.jpg");       
	else    changeTexture(texture, "carafeliz.jpg");
        progressBar.setValue(val);
     }
  }
  public void windowClosing(WindowEvent e){ System.exit(0); }
}
}

