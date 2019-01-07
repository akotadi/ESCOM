// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

import javax.media.j3d.*;
import javax.vecmath.*;
public class Pos {
     public static TransformGroup at(float x, float y, float z) {
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3f(x,y,z));
        return new TransformGroup(transform3D);
     }
}
