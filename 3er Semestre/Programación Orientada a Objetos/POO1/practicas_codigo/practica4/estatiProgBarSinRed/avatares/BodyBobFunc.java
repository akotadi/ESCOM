import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import java.awt.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.io.*;
public class BodyBob {
   Appearance app;
   TextureLoader tex;
   TransformGroup BobTG;
   BranchGroup group;
   Frame frame1;
   TransformGroup trans;
   public void giraTron(float angulo){
    try{
      if(angulo < Math.PI * 2){
          angulo+=0.2f;
          Transform3D t3d2=new Transform3D();
          t3d2.rotY(-angulo);
	  //t3d2.rotY(Math.PI   + nAnguloDoor);
          trans.setTransform(t3d2);
      }
    }catch(Exception e){
    }
  }
   public BranchGroup getBranchGroup() {
     return group;
   }
   public BodyBob(Frame frame) {
      frame1=frame;
      group = new BranchGroup();
      app = new Appearance();
      tex = new TextureLoader("bob-esponja.jpg", null);
      app.setTexture(tex.getTexture());
      Appearance Pant = new Appearance();
      TextureLoader Panta = new TextureLoader("traje2Bob.jpg", null);
      Pant.setTexture(Panta.getTexture());
      app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
      app.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
      app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
      com.sun.j3d.utils.geometry.Box earth = new com.sun.j3d.utils.geometry.Box(0.110f,.130f,.11f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, app);
      earth.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
      Transform3D tresD= new Transform3D();
      trans = new TransformGroup();
      BobTG = new TransformGroup();
      BobTG.setCapability(BobTG.ALLOW_TRANSFORM_READ);
      BobTG.setCapability(BobTG.ALLOW_TRANSFORM_WRITE);
      trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      tresD.setTranslation(new Vector3d(0,.1d,0));
      Transform3D a=new Transform3D();
      trans.setTransform(a);
      trans.setTransform(tresD);
      trans.addChild(earth);
      BobTG.addChild(trans);
      //////////////////////////////////////////////////////////////////////
      com.sun.j3d.utils.geometry.Box cone = new com.sun.j3d.utils.geometry.Box(0.110f,.10f,.11f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, Pant);
    cone.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
    Transform3D tresd= new Transform3D();
    TransformGroup tra = new TransformGroup();
    tra.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //tresd.setScale(new Vector3d(.2f,.2f,.2f));
    tresd.setTranslation(new Vector3d(0,-.1f,0));
    tra.setTransform(tresd);
    tra.addChild(cone);
    BobTG.addChild(tra);
    //////////////////////////////////////////////////////////////////////
    Cylinder PierI = new Cylinder(0.0040f,.05f, 25, app);
    Transform3D PI= new Transform3D();
    Transform3D Rotar= new Transform3D();
    TransformGroup PrI = new TransformGroup();
    TransformGroup Rota = new TransformGroup();
    PrI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //BI.setScale(new Vector3d(.2f,.2f,0));
    //BI.setNonUniformScale(.03f,.2f,.03f);
    PI.setTranslation(new Vector3d(.05f,-.23f,0));
    PrI.setTransform(PI);
    //Rotar.rotZ(Math.PI/4);
    Rota.setTransform(Rotar);
    PrI.addChild(PierI);
    Rota.addChild(PrI);
    BobTG.addChild(Rota);
    //////////////////////////////////////////////////////////////////////
    Cylinder PierD = new Cylinder(0.0040f,.05f, 25, app);
    Transform3D PD= new Transform3D();
    TransformGroup PrD = new TransformGroup();
    PrD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //BI.setScale(new Vector3d(.2f,.2f,0));
    //BD.setNonUniformScale(.03f,.2f,.03f);
    PD.setTranslation(new Vector3d(-.05f,-.23f,0));
  
    PrD.setTransform(PD);
    PrD.addChild(PierD);
    BobTG.addChild(PrD);
    //////////////////////////////////////////////////////////////////////
    Appearance app2=new Appearance();
    app2.setCapability(app2.ALLOW_COLORING_ATTRIBUTES_WRITE);
    app2.setColoringAttributes(new ColoringAttributes(/*0f,0f,200f,FASTEST*/));
    Sphere OjoI = new Sphere(0.300f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS , 25, Pant);
    Transform3D OI= new Transform3D();
    TransformGroup OjI = new TransformGroup();
    OjI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //BI.setScale(new Vector3d(.2f,.2f,0));
    OI.setNonUniformScale(.07f,.05f,.5f);
    OI.setTranslation(new Vector3d(.046f,-.245f,0));
    OjI.setTransform(OI);
    //OI.setRotation(new Quat4d(.1d,0,0,0));
    OjI.addChild(OjoI);
    //mal colocado y textura BobTG.addChild(OjI);
    //////////////////////////////////////////////////////////////////////
    Sphere OjoD = new Sphere(0.300f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS , 25, Pant);
    Transform3D OD= new Transform3D();
    TransformGroup OjD = new TransformGroup();
    OjD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //BI.setScale(new Vector3d(.2f,.2f,0));
    OD.setNonUniformScale(.07f,.05f,.5f);
    OD.setTranslation(new Vector3d(-.046f,-.245f,0));
    OjD.setTransform(OD);
    //OD.setRotation(new Quat4d(.1d,0,0,0));
    OjD.addChild(OjoD);
    //////////////////////////////////////////////////////////////////////
    Cylinder BrazoI = new Cylinder(0.0040f,.2f, 25, app);
    Transform3D BI= new Transform3D();
    Transform3D Rot= new Transform3D();
    TransformGroup BrI = new TransformGroup();
    TransformGroup Ro = new TransformGroup();
    BrI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    BI.setTranslation(new Vector3d(.009f,-.10f,0));
    BrI.setTransform(BI);
    Rot.rotZ(Math.PI/4);
    Ro.setTransform(Rot);
    BrI.addChild(BrazoI);
    Ro.addChild(BrI);
    BobTG.addChild(Ro);
    //////////////////////////////////////////////////////////////////////
    Cylinder BrazoD = new Cylinder(0.0040f,.2f, 25, app);
    Transform3D BD= new Transform3D();
    Transform3D R= new Transform3D();
    TransformGroup BrD = new TransformGroup();
    TransformGroup BrD2 = new TransformGroup();
    BrD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    BD.setTranslation(new Vector3d(-.009f,-.10f,0));
    R.rotZ(-Math.PI/4);
    BrD.setTransform(BD);
    BrD.addChild(BrazoD);
    BrD2.setTransform(R);
    BrD2.addChild(BrD);
    BobTG.addChild(BrD2);
      group.addChild(BobTG);
   }
   public void changeTextureCab(Texture texture, String image) {
        tex = new TextureLoader(image, "RGB",frame1);
    	texture = tex.getTexture();
	texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
	texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.REPLACE);
	app.setTextureAttributes(texAttr);
	app.setTexture(texture);
    }
}
