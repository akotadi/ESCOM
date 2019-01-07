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
public class Espacio{
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

Body b;
BodyZoey bz;
BodyBob bb;
Esfera e;
int avatar=4;
    int cta=0;
JFrame jFrame;

public void comer(boolean bool){
        food.comer();
        if(bool){
           if(avatar== 0) e.changeTextureCab(texture, "triste.jpg"); 
           if(avatar== 1) e.changeTextureCab(texture, "caraenfermo.jpg"); 
	   if(avatar== 2) b.changeTextureCab(texture, "caraenfermo.jpg"); 
           if(avatar== 3) bb.changeTextureCab(texture, "bobEnf.jpg"); 
           if(avatar== 4) bz.changeTextureCab(texture, "cabeza.png");
        }    
	else { 
           if(avatar== 0) e.changeTextureCab(texture, "Arizona.jpg");  
           if(avatar== 1) e.changeTextureCab(texture, "carafeliz.jpg");  
           if(avatar== 2) b.changeTextureCab(texture, "carafeliz.jpg");
           if(avatar== 3) bb.changeTextureCab(texture, "bob-esponja.jpg");
           if(avatar== 4) bz.changeTextureCab(texture, "cabezamal.png");
        }
}
    public Espacio(JFrame jFrame) {
        this.jFrame = jFrame;
        GraphicsConfiguration config =SimpleUniverse.getPreferredConfiguration();
        canvas3d = new Canvas3D(config);
        universe = new SimpleUniverse(canvas3d);
        group=create();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(group);
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
	e=new Esfera(jFrame);
        e.changeTextureCab(texture, "Arizona.jpg");
        group = e.getBranchGroup();
   }
   if(avatar== 1){
	e=new Esfera(jFrame);
        e.changeTextureCab(texture, "carafeliz.jpg");
        group = e.getBranchGroup();
   }
   if(avatar== 2){
      b=new Body(-0.4f,0.0f,0.0f,"",true, jFrame, "Avatar1");
      b.changeTextureCab(texture, "carafeliz.jpg");
      group = b.mybody();
   }
   if(avatar== 3){
      bb=new BodyBob(-0.4f,0.0f,0.0f,"",true, jFrame, "bob-esponja.jpg");
      group = bb.mybody();
   }
   if(avatar== 4){
      bz=new BodyZoey(-0.4f,0.0f,0.0f,"",true, jFrame, "cabeza.png");
      //bz.changeTextureCab(texture, "carafeliz.jpg");
      group = bz.myBody();
   }
    tierra=createBehaviors(group);
    //tierra.addChild(group);
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
  return Desliza.desliza(node, ejeX, xAlpha, -0.7f, -0.0f);
 }
//MÉTODO PARA DAR MOVIMIENTO AGREGANDO UN INTERPOLADOR DE POSICIÓN Y UNO DE ESCALA
        TransformGroup move(){
                TransformGroup gr= new TransformGroup();
                gr.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
                gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
                Transform3D x=new Transform3D();
                Alpha xAlpha = new Alpha( -1,Alpha.DECREASING_ENABLE | Alpha.INCREASING_ENABLE,0,0,3000,400,0,3000,400,0);
                PositionInterpolator interpolator =new PositionInterpolator(xAlpha,gr,x,-1.0f,1.0f);
                interpolator.setSchedulingBounds(bounds);
                Alpha alp=new Alpha();
                ScaleInterpolator scala=new ScaleInterpolator(alp,gr);
                gr.addChild(scala);
                gr.addChild(interpolator);
        return gr;
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
