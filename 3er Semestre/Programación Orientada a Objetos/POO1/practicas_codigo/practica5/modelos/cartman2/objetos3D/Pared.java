package objetos3D;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Pared{
    private TransformGroup tPrincipal=new TransformGroup();
    public Pared(Appearance apariencia, int x, int z){
      Transform3D t3d=new Transform3D();
      t3d.set(1,new Vector3d(x+0.5f,0,z+0.5f));
      tPrincipal=new TransformGroup(t3d);
      tPrincipal.addChild(new Box(0.5f,0.5f,0.5f,apariencia));
    }
    public TransformGroup getTransformGroup(){
      return tPrincipal;
    }
}