// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

import javax.media.j3d.*; 
import javax.vecmath.*;

public class MyLights {
    MyLights(Group group) {
        BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);

        Color3f light1Color = new Color3f(.7f, .7f, .7f);
        Vector3f light1Direction  = new Vector3f(4.0f, -7.0f, -12.0f);
            DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
            light1.setInfluencingBounds(bounds);
            group.addChild(light1);
         
            // Set up the ambient light
            Color3f ambientColor = new Color3f(.4f, .4f, .4f);
            AmbientLight ambientLightNode = new AmbientLight(ambientColor);
            ambientLightNode.setInfluencingBounds(bounds);
            group.addChild(ambientLightNode);
    }
}