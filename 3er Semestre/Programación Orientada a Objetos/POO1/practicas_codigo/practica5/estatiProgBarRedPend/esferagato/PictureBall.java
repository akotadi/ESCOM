
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Container;

public class PictureBall extends Frame {
Canvas3D canvas3D;
Appearance ap;
Texture feliz;
Texture enfermo;
private static Texture texture;
private Appearance appearance;
private TextureLoader loader;
static Sphere sphere;

Panel p;
Button bfeliz;
Button benfer;
EventHandler eh; 


public void changeTexture(Texture texture, String image, Sphere shape) {
     loader = new TextureLoader(image, "RGB",this);
    	texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
		// Set up the texture attributes
		// could be REPLACE, BLEND or DECAL instead of MODULATE
		
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f red = new Color3f(0.7f, .15f, .15f);
		//ap.setMaterial(new Material(red, black, red, white, 1.0f));		 
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.REPLACE);	
		ap.setTextureAttributes(texAttr);
		ap.setTexture(texture);
		//shape.setAppearance(appearance);error si se quita comentario
}

public PictureBall() {
   super("Texturas");
   
   setResizable(false);
   setSize(500, 400);
   // Create the universe
   GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   eh = new EventHandler();  
   bfeliz=new Button("Feliz");
   bfeliz.addActionListener(eh);
   benfer=new Button("Enfermo");
   benfer.addActionListener(eh);
   p=new Panel();
   p.add(bfeliz);
   p.add(benfer);
   add("North", p);
   add("Center",canvas3D);
   addWindowListener(eh);
   setup3DGraphics();
   setVisible(true);
}
void setup3DGraphics(){
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

class EventHandler extends WindowAdapter implements ActionListener {  
  public void actionPerformed(ActionEvent e) {  
     Button b=(Button)e.getSource();
     if(b==bfeliz){
      changeTexture(texture, "carafeliz.jpg", sphere);
     }
     if(b==benfer){
       changeTexture(texture, "caraenfermo.jpg", sphere);
     }
  }
  public void windowClosing(WindowEvent e){
   System.exit(0);
  }
 }

public static void main(String[] args) {
    new PictureBall().changeTexture(texture, "carafeliz.jpg", sphere);
}
}

