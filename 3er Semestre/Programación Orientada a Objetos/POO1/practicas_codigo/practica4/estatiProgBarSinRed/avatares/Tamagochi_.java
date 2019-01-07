import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Tamagochi extends JFrame implements CambioInt {
private Canvas3D canvas3D;
private Appearance ap;
private Texture feliz, enfermo;
private static Texture texture;

private JPanel jp;
private JButton bcomer;
private JLabel jl;
private JProgressBar progressBar = new JProgressBar();
private EventHandler eh; 
private PalSliders pals;

BranchGroup c=new BranchGroup();
Appearance app;
BranchGroup bolas[]=new BranchGroup[3];
Sphere albon1,albon2,albon3;
Body b;
BodyZoey bz;
BodyBob bb;
Esfera e;
int val;
int avatar=0;
int cta=0;
public Tamagochi(){
   super("Tamagochi 3D");
   
   val=0;
   setResizable(true); setSize(600, 700);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   canvas3D.setBounds(0,0, getWidth(), getHeight());
   eh = new EventHandler();
   pals=new PalSliders(3, new GridLayout(3, 1), this);  
   bcomer=new JButton("Comer");
   bcomer.addActionListener(eh);
   jl=new JLabel("Hambre");
   progressBar.setValue(0);
   jp=new JPanel();
   jp.add(jl); jp.add(progressBar);
   jp.add(bcomer); jp.add(pals);
   add("North", jp); //add("Center", pals);
   add("South", canvas3D);
   setup3DGraphics();
   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
   setVisible(true);
}
void setup3DGraphics(){
   BranchGroup group=null;
   
   DirectionalLight light1;
   String ava = JOptionPane.showInputDialog("Escriba num avatar (0,1,2 0 3)", "3");
   avatar=Integer.parseInt(ava);
   SimpleUniverse universe = new SimpleUniverse(canvas3D);
   universe.getViewingPlatform().setNominalViewingTransform();
   if(avatar== 0){
	e=new Esfera(this);
        e.changeTextureCab(texture, "carafeliz.jpg");
        group = e.getBranchGroup();
   }
   if(avatar== 1){
      b=new Body(-0.4f,0.0f,0.0f,"",true, this, "Avatar1");
      b.changeTextureCab(texture, "carafeliz.jpg");
      group = b.mybody();
   }
   if(avatar== 2){
      bb=new BodyBob(-0.4f,0.0f,0.0f,"",true, this, "bob-esponja.jpg");
      group = bb.mybody();
   }
   if(avatar== 3){
      bz=new BodyZoey(-0.4f,0.0f,0.0f,"",true, this, "cabeza.png");
      //bz.changeTextureCab(texture, "carafeliz.jpg");
      group = bz.myBody();
   }
   c.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
   c.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
   c.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
   c.setCapability(BranchGroup.ALLOW_DETACH);
   c.addChild(group);
   TextureLoader tex = new TextureLoader("cangreburguer2.jpg", null);
        app=new Appearance();
        app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        app.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        app.setTexture(tex.getTexture());
        albon1=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon1.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[0]=translate1(albon1,new Vector3f(0.0f, 0.0f, 1.1f));
        c.addChild(bolas[0]); 
        albon2=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon2.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[1]=translate1(albon2,new Vector3f(0.3f, 0.0f, 1.1f));
        c.addChild(bolas[1]);    
        albon3=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon3.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[2]=translate1(albon3,new Vector3f(-0.3f, 0.0f, 1.1f));
        c.addChild(bolas[2]);

   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(c);
}
public static void main(String[] args) { new Tamagochi(); }
public void comer(){
   //group.detach();   
   bolas[cta].detach();
   cta=(cta+1)%3;
   if(cta==0){
      try {
         Thread.sleep(5000);
      } catch(Exception e){}
      c.addChild(bolas[0]); 
      c.addChild(bolas[1]);
      c.addChild(bolas[2]);
       
    }           
}
BranchGroup translate1(Node node, Vector3f vector) {
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(vector);
		TransformGroup transformGroup =new TransformGroup();
		transformGroup.setTransform(transform3D);
		transformGroup.addChild(node);
		BranchGroup branchGroup=new BranchGroup();
	 		
		//branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		//branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		branchGroup.setCapability(BranchGroup.ALLOW_DETACH );

		branchGroup.addChild(transformGroup);
	return branchGroup;
	}
public void cambio(int n, float sval){
        if(n==0){
           ///if(avatar== 0) e.giraTron(sval); 
	   if(avatar== 1) b.giraTron(sval); 
           if(avatar== 2) bb.giraTron(sval);
           if(avatar== 3) bz.giraTron(sval); 
        }
	if(n==1){
           //if(avatar== 0) e.giraHI(sval); 
	   if(avatar== 1) b.giraHI(sval); 
           if(avatar== 2) bb.giraHI(sval);
           if(avatar== 3) bz.giraHI(sval);
        }
        if(n==2){
           //if(avatar== 0) e.giraHD(sval); 
	   if(avatar== 1) b.giraHD(sval); 
           if(avatar== 2) bb.giraHD(sval); 
           if(avatar== 3) bz.giraHD(sval);
        }
}
class EventHandler implements ActionListener {  
  public void actionPerformed(ActionEvent e1) {  
     JButton btn=(JButton)e1.getSource();
     if(btn==bcomer){ 
	val=100-val;
        System.out.println("Tamagochi come 2#### "+val); 
        
        comer();
        if(val == 100){
           if(avatar== 0) e.changeTextureCab(texture, "caraenfermo.jpg"); 
	   if(avatar== 1) b.changeTextureCab(texture, "caraenfermo.jpg"); 
           if(avatar== 2) bb.changeTextureCab(texture, "bobEnf.jpg"); 
           if(avatar== 3) bz.changeTextureCab(texture, "cabeza.png");
        }    
	else { 
           if(avatar== 0) e.changeTextureCab(texture, "carafeliz.jpg");  
           if(avatar== 1) b.changeTextureCab(texture, "carafeliz.jpg");
           if(avatar== 2) bb.changeTextureCab(texture, "bob-esponja.jpg");
           if(avatar== 3) bz.changeTextureCab(texture, "cabezamal.png");
        }
        progressBar.setValue(val);
     }
  }
}
}

