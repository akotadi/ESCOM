package objetos3D;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;

public class Pieza {
  private TransformGroup tg;
  public Pieza(Node figura, float dx, float dy, float dz, float rx, float ry, float rz){
    Transform3D resultado=new Transform3D();
    Transform3D modificar;
    if (rx!=0){
      modificar=new Transform3D();
      modificar.rotX(rx);
      resultado.mul(modificar);
    }
    if (ry!=0){
      modificar=new Transform3D();
      modificar.rotY(ry);
      resultado.mul(modificar);
    }
    if (rz!=0){
      modificar=new Transform3D();
      modificar.rotZ(rz);
      resultado.mul(modificar);
    }
    if (dx!=0 || dy!=0 || dz!=0){
      modificar=new Transform3D();
      modificar.set(new Vector3f(dx,dy,dz));
      resultado.mul(modificar);
    }
    tg=new TransformGroup(resultado);
    tg.addChild(figura);
  }
  TransformGroup getTransformGroup(){
    return tg;
  }
}