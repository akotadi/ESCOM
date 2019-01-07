package objetos3D;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.net.*;

public class Puerta implements Runnable, SeActivaAlAcercarse{
  private TransformGroup tPrincipal=new TransformGroup();
  Thread hilo;
  private boolean cerrada=true;
  private TransformGroup tg2;
  private double nAnguloPuerta=0;
  int x, z, orientacion;
  boolean haCambiado=false;
  private void abrir(){
    cerrada=false;
  }
  private void cerrar(){
    cerrada=true;
  }
  public Puerta(Appearance appPuerta, Appearance appMarco, int x, int z, int orientacion){
    this.x=x; this.z=z; this.orientacion=orientacion;
    Transform3D t3d3=new Transform3D();
    t3d3.set(1,new Vector3d(x-0.4f*orientacion+0.5f,0,z+0.49f*orientacion+0.5f));
    TransformGroup tg3=new TransformGroup(t3d3);

    Transform3D t3dMarco=new Transform3D();
    t3dMarco.set(1,new Vector3d(0,0,-0.90*orientacion));
    TransformGroup tgMarco=new TransformGroup(t3dMarco);
    tgMarco.addChild(new Box(0.01f,0.5f,0.1f,appMarco));
    tg3.addChild(tgMarco);

    Transform3D t3d2=new Transform3D();
    t3d2.rotY(nAnguloPuerta);
    tg2=new TransformGroup(t3d2);
    tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    Transform3D t3d1=new Transform3D();
    t3d1.set(1,new Vector3d(+0.4f,0,0));
    TransformGroup tg1=new TransformGroup(t3d1);
    tg1.addChild(new Box(0.4f,0.5f,0.02f,appPuerta));

    tPrincipal.addChild(tg3);
    tg3.addChild(tg2);
    tg2.addChild(tg1);

    hilo=new Thread(this);
    hilo.start();
  }
  public TransformGroup getTransformGroup(){
    return tPrincipal;
  }
  public void run(){
    boolean mover=false;
    try{
      while(true){
        Thread.sleep(100);
        mover=false;
        if (cerrada && nAnguloPuerta < Math.PI / 2){
          nAnguloPuerta+=0.2f;
          mover=true;
          if (nAnguloPuerta>Math.PI/2) nAnguloPuerta=Math.PI/2 ;
        }else if(!cerrada && nAnguloPuerta > 0){
          nAnguloPuerta-=0.2f;
          mover=true;
          if (nAnguloPuerta<0) nAnguloPuerta=0;
        }
        if (mover){
          Transform3D t3d2=new Transform3D();
          if (orientacion==1) t3d2.rotY(nAnguloPuerta);
          else if (orientacion==-1) t3d2.rotY(Math.PI   + nAnguloPuerta);
          tg2.setTransform(t3d2);
          haCambiado=true;
        }
      }
    }catch(Exception e){
    }
  }
  
  public void moverNavegador(float xMio, float zMio) {
     if (Math.abs(x-xMio) < 2 && Math.abs(z-zMio)<2) abrir();
     else cerrar();
  }
  
  public boolean reset(){
      boolean hc=haCambiado;
      haCambiado=false;
      return hc;
  }
  
}