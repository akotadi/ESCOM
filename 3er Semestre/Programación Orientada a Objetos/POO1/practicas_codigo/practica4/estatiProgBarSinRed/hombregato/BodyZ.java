import com.sun.j3d.utils.picking.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;

public class BodyZ{
    private BranchGroup group;
    private static Texture texture;
    private Appearance appearance;
    private TextureLoader loader;
    TransformGroup rotacion= new TransformGroup();
    Appearance ap;
    Frame frame1;
    //Sphere cab;
    com.sun.j3d.utils.geometry.Box cab;
    
    //public Sphere getCab(){
    public com.sun.j3d.utils.geometry.Box getCab(){
	return cab;
    }
    public BodyZ(float x, float y, float z,String nombre_mostrar,boolean movimiento,Frame frame,String img){
         group=new BranchGroup();
         Appearance apa2 = new Appearance();
         TextureLoader loader2=new TextureLoader("fondo.jpg",frame);
         Texture texture2=loader2.getTexture();
         apa2.setTexture(texture2);
         Background backg = new Background(1.0f, 1.0f, 1.0f);
         backg.setImage(loader2.getImage());
         group.addChild(backg);
         frame1=frame;
         Transform3D cabeza=new Transform3D();
         Transform3D cuello=new Transform3D();
         Transform3D tronco=new Transform3D();
         Transform3D homizq=new Transform3D();
         Transform3D homder=new Transform3D();
         Transform3D braizq=new Transform3D();
         Transform3D brader=new Transform3D();
         Transform3D pieizq=new Transform3D();
         Transform3D pieder=new Transform3D();
         Transform3D nombre=new Transform3D();
         Vector3f poscabeza =new Vector3f(x,y+0.44f,z);
         Vector3f poscuello =new Vector3f(x,y+0.15f,z);
         Vector3f postronco =new Vector3f(x,y,z);
         Vector3f poshizq = new Vector3f(x-0.14f,y+0.16f,z);
         Vector3f poshder = new Vector3f(x+0.15f,y+0.2f,z);
         Vector3f posbizq = new Vector3f(x-0.14f,y+.05f,z);
         Vector3f posbder = new Vector3f(x+0.15f,y+.05f,z);
         Vector3f pospizq = new Vector3f(x-0.05f,y-0.4f,z);
         Vector3f pospder = new Vector3f(x+0.05f,y-0.4f,z);
         Vector3f nom =new Vector3f(x-0.085f,y-0.33f,z);
         //Material
         Material yellowSphMaterial = new Material();
         yellowSphMaterial.setDiffuseColor(0.85f, 0.85f, 0.85f);
         Appearance yellowSphAppearance = new Appearance();
         yellowSphAppearance.setMaterial(yellowSphMaterial);
         Appearance apa = new Appearance();
         System.out.println("archi ima = ( "+img+".jpg )");
         loader=new TextureLoader(img,frame1);
         texture=loader.getTexture();
         apa.setTexture(texture);
         apa.setMaterial(yellowSphMaterial);
         Appearance apa1 = new Appearance();
         TextureLoader loader1=new TextureLoader("torsofal.png",frame1);
         Texture texture1=loader1.getTexture();
         apa1.setTexture(texture1);
         Appearance apaleg = new Appearance();
         loader1=new TextureLoader("legaba.png",frame1);
         texture1=loader1.getTexture();
         apaleg.setTexture(texture1);
         Appearance aparleg = new Appearance();
         loader1=new TextureLoader("rlegaba.png",frame1);
         texture1=loader1.getTexture();
         aparleg.setTexture(texture1);
         apa.setMaterial(yellowSphMaterial);
         //Cabeza
         
         BoundingSphere bounds = new BoundingSphere(new Point3d(),0.0);
         cabeza.setTranslation(poscabeza);
         TransformGroup transformacion1= new TransformGroup(cabeza);
         TransformGroup tran= new TransformGroup();
         if(movimiento){
         tran.setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
         tran.setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
         transformacion1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         
         MouseRotate rotator=new MouseRotate(transformacion1);
         rotator.setSchedulingBounds(bounds);
         MouseTranslate t=new MouseTranslate(transformacion1);
         t.setSchedulingBounds(bounds);
         tran.addChild(t);
         tran.addChild(rotator);
         }
         int primflags = Primitive.GENERATE_NORMALS
        + Primitive.GENERATE_TEXTURE_COORDS;
         //cab=new Sphere(0.1f,primflags, 32,apa);
         cab=new com.sun.j3d.utils.geometry.Box(0.095f,.130f, 0.11f, primflags, apa);
         cab.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
         
         ap = cab.getAppearance();
         ap.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
         ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
         ap.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
         transformacion1.addChild(cab);
         tran.addChild(transformacion1);
         
         //Cuello
         cuello.setTranslation(poscuello);
         TransformGroup transformacion2= new TransformGroup(cuello);
         Cylinder cue=new Cylinder(0.025f,0.05f ,yellowSphAppearance);
         transformacion2.addChild(cue);
         tran.addChild(transformacion2);
         //Tronco
         tronco.setTranslation(postronco);
         TransformGroup transformacion3= new TransformGroup(tronco);
         Cylinder tron=new Cylinder(0.118f,0.6f,primflags ,apa1);
         transformacion3.addChild(tron);
         tran.addChild(transformacion3);
         //Hombro Derecho
         homder.setTranslation(poshder);
         TransformGroup transformacion4= new TransformGroup(homder);
         Sphere hder=new Sphere(.03f,Primitive.GENERATE_NORMALS, 32,yellowSphAppearance);
         transformacion4.addChild(hder);
         tran.addChild(transformacion4);
         //Hombro Izquiedo
         homizq.setTranslation(poshizq);
         TransformGroup transformacion5= new TransformGroup(homizq);
         Sphere hizq=new Sphere(.03f,Primitive.GENERATE_NORMALS, 32,yellowSphAppearance);
         transformacion5.addChild(hizq);
         tran.addChild(transformacion5);
         //Brazo Derecho
         /*brader.setTranslation(posbder);
         TransformGroup transformacion6= new TransformGroup(brader);
         Cylinder bder=new Cylinder(0.0125f,0.2f ,yellowSphAppearance);
         transformacion6.addChild(bder);
         tran.addChild(transformacion6);*/

    Transform3D R= new Transform3D();  
    TransformGroup BrD2 = new TransformGroup();
    
    brader.setTranslation(/*posbder new Vector3d(x+0.5f,y+0.1f,z)*/new Vector3d(-.009f,-.05f,0));//BD ya
    TransformGroup transformacion6 = new TransformGroup(brader);//BrD ya
    transformacion6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Cylinder bder=new Cylinder(0.0125f,0.2f ,yellowSphAppearance);
    R.rotZ(Math.PI/4);
    //BrD.setTransform(brader);
    transformacion6.addChild(bder);//BrD,BrazoD 
    BrD2.setTransform(R);
    BrD2.addChild(transformacion6);
    tran.addChild(BrD2);//tran
         //Brazo Izquierdo
         braizq.setTranslation(posbizq);
         TransformGroup transformacion7= new TransformGroup(braizq);
         Cylinder bizq=new Cylinder(0.0125f,0.15f ,yellowSphAppearance);
         transformacion7.addChild(bizq);
         tran.addChild(transformacion7);
         //Pierna Derecha
         pieder.setTranslation(pospder);
         TransformGroup transformacion8= new TransformGroup(pieder);
         //Cylinder pder=new Cylinder(0.0125f,0.225f , apaleg);
         com.sun.j3d.utils.geometry.Box pder=new 
         com.sun.j3d.utils.geometry.Box(0.05f,.2f,.05f, primflags, aparleg);
         transformacion8.addChild(pder);
         tran.addChild(transformacion8);
         //Pierna Izquierda
         pieizq.setTranslation(pospizq);
         TransformGroup transformacion9= new TransformGroup(pieizq);
         //Cylinder pizq=new Cylinder(0.0125f,0.225f ,yellowSphAppearance);
         com.sun.j3d.utils.geometry.Box pizq=new 
         com.sun.j3d.utils.geometry.Box(0.05f,.2f,.05f, primflags, apaleg);
         transformacion9.addChild(pizq);
         tran.addChild(transformacion9);
         //nombbre
         nombre.setTranslation(nom);
         TransformGroup transformacion10= new TransformGroup(nombre);
         Text2D text2D = new Text2D(nombre_mostrar,new Color3f(0.9f, 1.0f, 1.0f),"Helvetica", 12, Font.ITALIC);
         transformacion10.addChild(text2D);
         tran.addChild(transformacion10);
         group.addChild(tran);
         if(movimiento){
         transformacion2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion5.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion6.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion7.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion8.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion9.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         transformacion9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         transformacion10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         MouseTranslate t2=new MouseTranslate(transformacion2);
         MouseTranslate t3=new MouseTranslate(transformacion3);
         MouseTranslate t4=new MouseTranslate(transformacion4);
         MouseTranslate t5=new MouseTranslate(transformacion5);
         MouseTranslate t6=new MouseTranslate(transformacion6);
         MouseTranslate t7=new MouseTranslate(transformacion7);
         MouseTranslate t8=new MouseTranslate(transformacion8);
         MouseTranslate t9=new MouseTranslate(transformacion9);
         MouseTranslate t10=new MouseTranslate(transformacion10);
         t2.setSchedulingBounds(bounds);
         tran.addChild(t2);
         t3.setSchedulingBounds(bounds);
         tran.addChild(t3);
         t4.setSchedulingBounds(bounds);
         tran.addChild(t4);
         t5.setSchedulingBounds(bounds);
         tran.addChild(t5);
         t6.setSchedulingBounds(bounds);
         tran.addChild(t6);
         t7.setSchedulingBounds(bounds);
         tran.addChild(t7);
         t8.setSchedulingBounds(bounds);
         tran.addChild(t8);
         t9.setSchedulingBounds(bounds);
         tran.addChild(t9);
         t10.setSchedulingBounds(bounds);
         tran.addChild(t10); 
        }
    }
    public BranchGroup myBody(){
        return group;
    }
    public void changeTextureCab(Texture texture, String image) {
        loader = new TextureLoader(image, "RGB",frame1);
    	texture = loader.getTexture();
	texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.REPLACE);
	ap.setTextureAttributes(texAttr);
	ap.setTexture(texture);
    }
}
