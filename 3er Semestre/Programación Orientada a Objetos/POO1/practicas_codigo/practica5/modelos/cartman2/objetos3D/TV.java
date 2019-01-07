package objetos3D;

import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class TV {

  TransformGroup tPrincipal;
  public TV (Appearance apCaja,Appearance apCristal, Appearance apBoton, int x, int z ){
    Transform3D t3d=new Transform3D();
    t3d.set(1,new Vector3d(x+0.5f,0,z+0.5f));
    tPrincipal=new TransformGroup(t3d);    
    float giro90=(float)(Math.PI/2);
    Pieza figuras[]={
      new Pieza(new Cylinder(0.02f,0.3f,apCaja),-0.2f,-0.35f,-0.2f ,0,0,0),	//Pata1
      new Pieza(new Cylinder(0.02f,0.3f,apCaja),-0.2f,-0.35f,0.2f,0,0,0),		//Pata2
      new Pieza(new Cylinder(0.02f,0.3f,apCaja),0.2f,-0.35f,-0.2f,0,0,0),		//Pata3
      new Pieza(new Cylinder(0.02f,0.3f,apCaja),0.2f,-0.35f,0.2f,0,0,0),		//Pata4
      new Pieza(new Box(0.3f,0.25f,0.25f,apCaja),0,0.0f,0,0,0,0),			//Caja
      new Pieza(new Box(0.20f,0.20f,0.01f,apCristal),-0.05f,0.00f,0.25f,0,0,0),	//Cristal
      new Pieza(new Box(0.02f,0.02f,0.01f,apBoton),0.25f,-0.13f,0.25f,0,0,0), 	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,-0.08f,0.25f,0,0,0), 	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,-0.04f,0.25f,0,0,0), 	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,0.00f,0.25f,0,0,0), 	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,0.04f,0.25f,0,0,0),      //Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,0.08f,0.25f,0,0,0),	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,0.12f,0.25f,0,0,0), 	//Boton
      new Pieza(new Box(0.01f,0.01f,0.01f,apBoton),0.25f,0.16f,0.25f,0,0,0) 	//Boton
    };
    for (int n=0;n<figuras.length;n++){
      tPrincipal.addChild(figuras[n].getTransformGroup());
    }
  }
  public TransformGroup getTransformGroup(){
    return tPrincipal;
  }
}
