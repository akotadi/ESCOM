import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.io.File;
public class Espacio  implements LeeRed {
    Canvas3D canvas3d;
    BranchGroup group;
    BranchGroup bolas[]=new BranchGroup[3];
    SimpleUniverse universe;
    BoundingSphere bounds;
    TransformGroup tierra;
    BranchGroup tranGalbon1;
    Comida food;
    Personaje body; 
    Sphere pacman;
    Sphere albon1,albon2,albon3;
    Appearance app, apaPacman;
    TransformGroup t;
private static Texture texture;
private TransformGroup cartmanTransX;
private TransformGroup cartmanRotXformGroup;
BranchGroup grupo1, grupo2;

Movible movi;
Body b;
BodyZoey bz;
BodyBob bb;
Stan stan;
Esfera e;
int avatar=5;
    int cta=0;
JFrame jFrame;
private Red r;
private String nombres[][] = {
{"Arizona.jpg", "caraenfermo.jpg"},
{"carafeliz.jpg", "caraenfermo.jpg"},
{"carafeliz.jpg", "caraenfermo.jpg"},
{"bob-esponja.jpg", "bobEnf.jpg"},
{"cabeza.png", "cabezamal.png"},
{"carafeliz.png", "caraenfermo.jpg"},
};
   public void leeRed(Object obj){
      if(obj instanceof Icono){   
         Icono i=(Icono)obj;
         int turno=i.getTurno();  
         System.out.println("Recibi"+i.getTurno());  
         food.comer();
         movi.changeTextureCab(texture, nombres[avatar][turno]);      
      }
      /*if(obj instanceof Mensaje){ 
		System.out.println("Recibi "+(Mensaje)obj);
                microChat.recibir((Mensaje)obj);
      }*/
   }
public void comer(int turno){
   food.comer();
   movi.changeTextureCab(texture, nombres[avatar][turno]);
   r.escribeRed(new Icono("Tamagochi", turno)); 
}
   
    public Espacio(JFrame jFrame) {
        this.jFrame = jFrame;
        GraphicsConfiguration config =SimpleUniverse.getPreferredConfiguration();
        canvas3d = new Canvas3D(config);
        universe = new SimpleUniverse(canvas3d);
        group=create();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(group);
        r=new Red(this);
    }

//MÉTODO PARA OBTENER EL CANVAS QUE CONTIENE TODO EL ESCENARIO
    public Canvas3D getCanvas(){
        return canvas3d;
    }


//MÉTODO PARA CREAR TODOS LOS COMPONENTES DEL ESCENARIO
    private BranchGroup create(){
        
        BranchGroup c=new BranchGroup();
        c.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
	c.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
	c.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
	c.setCapability(BranchGroup.ALLOW_DETACH);
        food=new Comida(c);
	//c.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
        t=new TransformGroup();
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        

//SE CREAN LAS FRONTERAS DEL ESPACIO
        bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

//SE CREA LA PLATAFORMA SOBRE LA CUAL ESTARÁN LOS OBJETOS
	Appearance app3=new Appearance();
	TextureLoader tex2=new TextureLoader("land.jpg", null);
	app3.setTexture(tex2.getTexture());
        ColorCube cubo=new ColorCube(0.3);
        cubo.setAppearance(app3);
        Vector3f vector2 = new Vector3f(0.0f, -0.26f, 0.0f);
	Transform3D transform2 = new Transform3D();
        transform2.setTranslation(vector2);
        Vector3d escala2 = new Vector3d(30.0f, 0.1f, 30.0f);
        transform2.setScale(escala2);
        TransformGroup transformGroup2 = new TransformGroup(transform2);
	transformGroup2.addChild(cubo);
	t.addChild(transformGroup2);
     
//SE CREA LA ESFERA PRINCIPAL, EL TAMAGOTCHI CON LA APARIENCIA DE PACMAN
        //tierra=move();
       
        TextureLoader tex = new TextureLoader("Arizona.jpg", null);
        apaPacman=new Appearance();
        apaPacman.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        apaPacman.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        apaPacman.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        apaPacman.setTexture(tex.getTexture());
	pacman = new Sphere(0.25f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, apaPacman);
        pacman.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
       
        //String ava = JOptionPane.showInputDialog("Escriba num avatar (0,1,2,3 o 4)", "4");
   //avatar=Integer.parseInt(ava);
   if(avatar== 0){
	movi=new Esfera(jFrame);
        movi.changeTextureCab(texture, "Arizona.jpg");
        group = movi.myBody();
   }
   if(avatar== 1){
	movi=new Esfera(jFrame);
        movi.changeTextureCab(texture, "carafeliz.jpg");
        group = movi.myBody();
   }
   if(avatar== 2){
      movi=new Body(-0.4f,0.0f,0.0f,"",true, jFrame, "Avatar1");
      movi.changeTextureCab(texture, "carafeliz.jpg");
      group = movi.myBody();
   }
   if(avatar== 3){
      movi=new BodyBob(-0.4f,0.0f,0.0f,"",true, jFrame, "bob-esponja.jpg");
      group = movi.myBody();
   }
   if(avatar== 4){
      movi=new BodyZoey(-0.4f,0.0f,0.0f,"",true, jFrame, "cabeza.png");
      group = movi.myBody();
   }
   if(avatar== 5){
      movi=new Stan(-0.4f,0.0f,0.0f,"",true, jFrame, "Avatar1", null);
      group= movi.myBody();
   }
   tierra=createBehaviors(group);
   t.addChild(tierra);
//SE CREA UN FANTASMA CON EL CUAL JUGARÁ

body= new Personaje( "Cartman2.ms3d", 0f, 0f, 0f);
   grupo2 = escatrans1(
                         body.mybody(), new Vector3f(0.0f, -0.5f, 1.0f),
                       	new Vector3d(0.25f, 0.25f, 0.25f));
   cartmanTransX = Posi.translate(grupo2, new Vector3f(0.3f, 0.0f, 0.7f));
   cartmanRotXformGroup = Posi.rotate(cartmanTransX, new Alpha(-1, 5000));
   t.addChild(cartmanRotXformGroup);

//SE OFRECE LA CAPACIDAD DE ROTAR, TRASLADAR O DAR ZOOM AL ESPACIO
        MouseRotate r=new MouseRotate(t);
        r.setSchedulingBounds(bounds);
        c.addChild(r);
        MouseTranslate l=new MouseTranslate(t);
        l.setSchedulingBounds(bounds);
        c.addChild(l);
        MouseZoom z=new MouseZoom(t);
        z.setSchedulingBounds(bounds);
        c.addChild(z);

//SE PINTA EL FONDO DE BLANCO
        Background fondo=new Background();
        fondo.setColor(1.0f,1.0f,1.0f);
        fondo.setApplicationBounds(bounds);
        t.addChild(fondo);

//SE DA LUZ A TODO EL ESCENARIO
        AmbientLight luz= new AmbientLight();
        luz.setInfluencingBounds(bounds);
        t.addChild(luz);

//SE AGREGA EL GROUP PRINCIPAL A EL CANVAS
        c.addChild(t);
        return c;
    }    
public TransformGroup createBehaviors( Node node ){
  Alpha xAlpha = new Alpha(-1,Alpha.DECREASING_ENABLE |Alpha.INCREASING_ENABLE,
                    0,0,3000,400,0,3000,400,0);
  Transform3D ejeX=new Transform3D();
  return Desliza.desliza(node, ejeX, xAlpha, -0.7f, 0.7f);
 }
   BranchGroup escatrans1(Node node, Vector3f vector, Vector3d escala) {
      Transform3D transform3D = new Transform3D();
      transform3D.setTranslation(vector);
      transform3D.setScale(escala);
      TransformGroup transformGroup =new TransformGroup();
      transformGroup.setTransform(transform3D); 
      transformGroup.addChild(node);
      BranchGroup branchGroup=new BranchGroup();	
      branchGroup.setCapability(BranchGroup.ALLOW_DETACH );
      branchGroup.addChild(transformGroup);
      return branchGroup;
   }
   public void comer(){
      food.comer();
   }
}
