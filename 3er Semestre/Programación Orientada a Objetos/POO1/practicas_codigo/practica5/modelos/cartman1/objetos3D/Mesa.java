package objetos3D;

import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Mesa {
  TransformGroup tPrincipal;
  public Mesa(Appearance apariencia, int x, int z){
    Transform3D t3d=new Transform3D();
    t3d.set(1,new Vector3d(x+1,0,z+1));
    tPrincipal=new TransformGroup(t3d);      
    Pieza figuras[]={
      new Pieza(new Cylinder(0.03f,0.4f,apariencia),-0.4f,-0.30f,-0.4f ,0,0,0),     //Pata1
      new Pieza(new Cylinder(0.03f,0.4f,apariencia),-0.4f,-0.30f,0.4f,0,0,0),		//Pata2
      new Pieza(new Cylinder(0.03f,0.4f,apariencia),0.4f,-0.30f,-0.4f,0,0,0),		//Pata3
      new Pieza(new Cylinder(0.03f,0.4f,apariencia),0.4f,-0.30f,0.4f,0,0,0),		//Pata4
      new Pieza(new Cylinder(0.9f,0.03f,apariencia),0f,-0.1f,0f,0,0,0)			//Tablon
    };
    
    for (int n=0;n<figuras.length;n++){
      tPrincipal.addChild(figuras[n].getTransformGroup());
    }
      /*Transform3D t3d=new Transform3D();
      t3d.set(1,new Vector3d(x+0.5f,0,z+0.5f));
      tPrincipal=new TransformGroup(t3d);
      tPrincipal.addChild(new Cylinder(1,1,apariencia));*/
  }
  public TransformGroup getTransformGroup(){
    return tPrincipal;
  }
}