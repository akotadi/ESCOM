// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.applet.Applet;

public class SkyBackground {
public static final BoundingSphere infiniteBounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
public SkyBackground(Group group, Applet applet) {

  // set up the image
  java.net.URL bgImageURL = null;
  // mount3.jpg is an upside-down version for java 3d buggy version
  bgImageURL = FindUrl.get("mount3.jpg",applet);

  try {
	TextureLoader bgTexture = new TextureLoader(bgImageURL, null);
  /*
	// Create a background with the static image
	Background bgImage = new Background(bgTexture.getImage());
	bgImage.setApplicationBounds(infiniteBounds);
	//bgSwitch.addChild(bgImage);
  group.addChild(bgImage);
  */
	// create a background with the image mapped onto a sphere which
	// will enclose the world
	Background bgGeo = new Background();
	bgGeo.setApplicationBounds(infiniteBounds);
	BranchGroup bgGeoBG = new BranchGroup();
	Appearance bgGeoApp = new Appearance();
	bgGeoApp.setTexture(bgTexture.getTexture());
        Sphere sphereObj = new Sphere(1.0f,
				  Sphere.GENERATE_NORMALS |
                                  Sphere.GENERATE_NORMALS_INWARD |
                                  Sphere.GENERATE_TEXTURE_COORDS,
				  12, bgGeoApp);
        bgGeoBG.addChild(sphereObj);
        bgGeo.setGeometry(bgGeoBG);

  group.addChild(bgGeo);
  }
  catch(Throwable thing){
  	// set up the blue BG color node
        // For  a clear blue sky
  	Background bgBlue = new Background(new Color3f(Color.blue));
  	bgBlue.setApplicationBounds(infiniteBounds);
  	group.addChild(bgBlue);
  }
}
public SkyBackground(Group group, Applet applet, Color3f color) {
  Background bg= new Background(color);
  bg.setApplicationBounds(infiniteBounds);
  group.addChild(bg);
}
} // end of class