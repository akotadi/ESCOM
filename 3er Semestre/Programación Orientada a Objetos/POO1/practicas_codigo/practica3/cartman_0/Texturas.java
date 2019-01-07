
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.ImageComponent2D;
import java.awt.Component;
import java.util.HashMap;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Texturas {
  private static Texturas t=new Texturas();
  public static Texturas getObjeto(){
    return t;
  }
  
  HashMap imagenes = new HashMap();
  public Texturas() {
  }

  public void cargaTextura(String fichero, String nombre, Component c){
    TextureLoader loader = new TextureLoader("./texturas/" + fichero, c);
    ImageComponent2D image = loader.getImage();
    if (imagenes.containsKey(nombre)){
        imagenes.remove(nombre);
    }
    imagenes.put(nombre,image);
  }

  public ImageComponent2D getImage(String nombre){
    return (ImageComponent2D)imagenes.get(nombre);
  }

  public Appearance getApariencia(String nombre){
      Appearance app = new Appearance();

      TexCoordGeneration texCoord = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
                                                        TexCoordGeneration.TEXTURE_COORDINATE_2,
                                                        new Vector4f(1,0,1,0.5f), new Vector4f(0,1,0,0.5f));
     
      app.setTexCoordGeneration(texCoord);

      ImageComponent2D image=getImage(nombre);
      if (nombre ==null) System.out.println(nombre + " es nulo");
      Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
                                      image.getWidth(), image.getHeight());

      texture.setImage(0, image);
      texture.setEnable(true);
                        
      

      app.setTexture(texture);

      return app;
    }
}
