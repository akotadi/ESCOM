// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

// The Palette class is for storing Appearances
// including colors, materials and
// textures so that they can be shared between objects.

import javax.media.j3d.*;
import java.applet.Applet;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.vecmath.*;
import java.awt.Color;

public class Palette {
public static final Appearance GREEN = new StandardAppearance(Color.green);
public static final Appearance ORANGE = new StandardAppearance(Color.orange);
public static final Appearance CYAN = new StandardAppearance(Color.cyan);
public static final Appearance RED = new StandardAppearance(Color.red);
public static final Appearance BLUE = new StandardAppearance(Color.blue);
public static final Appearance WHITE = new StandardAppearance(Color.white);
public static final Appearance YELLOW = new StandardAppearance(Color.yellow);
public static final Appearance BLACK = new StandardAppearance(Color.black);
public static final Appearance MAGENTA = new StandardAppearance(Color.magenta);
public static final Appearance HORSE =
    new StandardAppearance(new Color3f(new Color(.31f,.24f,.15f)));

public static void addTexture(String fileName, Appearance ap, Applet applet){
  java.net.URL imageURL = FindUrl.get(fileName,applet);
  TextureLoader loader = new TextureLoader(imageURL,
                              "RGB", new Container());
  Texture texture = loader.getTexture();
  if (texture != null) {
    texture.setBoundaryModeS(Texture.WRAP);
    texture.setBoundaryModeT(Texture.WRAP);
    texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );
    ap.setTexture(texture);
  }
  // Set up the texture attributes
  //could be REPLACE, BLEND or DECAL instead of MODULATE
  TextureAttributes texAttr = new TextureAttributes();
  texAttr.setTextureMode(TextureAttributes.REPLACE);

  ap.setTextureAttributes(texAttr);
  TexCoordGeneration tcg = new TexCoordGeneration(
      TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2,
      new Vector4f(1f, 0f, 0f, 0f),
      new Vector4f(0f, 1f, 0f, 0f));

      /*TexCoordGeneration tcg = new
      TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
      TexCoordGeneration.TEXTURE_COORDINATE_2,
      new Vector4f(0f, 0f, 0f, 1f),
      new Vector4f(0f, 0f, 0f, 0f));   */
  tcg.setEnable(true);
  //tcg.setPlaneS(new Vector4f(1f, 0f, 0f, 0f));
  //tcg.setPlaneT(new Vector4f(0f, 1f, 0f, 0f));

  ap.setTexCoordGeneration(tcg);
  //set up the material
  //ap.setMaterial(new Material(C.red, C.black, C.red, C.black, 1.0f));
}
} // end of class Palette
