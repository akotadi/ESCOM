import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Comida {
BranchGroup c;
Appearance app;
BranchGroup bolas[]=new BranchGroup[3];
Sphere albon1,albon2,albon3;
int cta=0;
public Comida(BranchGroup c) {
   this.c=c;
   TextureLoader tex = new TextureLoader("cangreburguer2.jpg", null);
        app=new Appearance();
        app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        app.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        app.setTexture(tex.getTexture());
        albon1=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon1.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[0]=Posi.translate1(albon1,new Vector3f(0.0f, 0.0f, 1.1f));
        c.addChild(bolas[0]); 
        albon2=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon2.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[1]=Posi.translate1(albon2,new Vector3f(0.3f, 0.0f, 1.1f));
        c.addChild(bolas[1]);    
        albon3=new Sphere(0.05f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS | Primitive.ENABLE_APPEARANCE_MODIFY, 32, app);
        albon3.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
	bolas[2]=Posi.translate1(albon3,new Vector3f(-0.3f, 0.0f, 1.1f));
        c.addChild(bolas[2]);
}
public void comer(){
   //group.detach();   
   bolas[cta].detach();
   cta=(cta+1)%3;
   if(cta==0){
      try {
         Thread.sleep(500);
      } catch(Exception e){}
      c.addChild(bolas[0]); 
      c.addChild(bolas[1]);
      c.addChild(bolas[2]);
       
    }           
}
}
