
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Container;

public class Pared {
	
	Appearance appa=new Appearance();
	TextureLoader tex;
	Box pard;
	int paratextura;
	
	public Pared (int tipo, String textura)
	{
		switch (tipo)
		{
			case 0:
                            CreaPared(0.5f, 0.5f, 0.5f, textura);
                        break;
			case 1://Pared de 2 pinturas
				CreaPared(6f, 1f, .05f, textura);
			break;
			case 2://Pared de 3 pinturas
			    CreaPared(8f, 1f, .01f, textura);
			break;
			case 3://Pared de 4 pinturas
			    CreaPared(.01f, 1f, 12f, textura);
			break;
			default:
				System.out.println("Tipo de pared no especificado. Pruebe introducir comandos manualmente");
			break;
		}
	}
	
	public Pared (float x, float y, float z, String textura)
	{
		CreaPared(x,y,z,textura);
	}
	
    private void CreaPared(float x, float y, float z, String textura)
    {
    	paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
    	TextureLoader loader=null;
        Texture texture=null;
		Appearance ap=null;
    	loader = new TextureLoader(textura,"INTENSITY", new Container());
    	texture = loader.getTexture();
    	ap = new Appearance();
    	ap.setTexture(texture);
    	pard=new Box(x, y, z, paratextura,ap);
    }
    
    
    
    public Box getBox ()
    {	return pard;}
    
    public TransformGroup translate(Node node, Vector3f vector) 
    {
    	Transform3D transform3D = new Transform3D();
    	transform3D.setTranslation(vector);
    	TransformGroup transformGroup =new TransformGroup();
    	transformGroup.setTransform(transform3D); 
    	transformGroup.addChild(node);
    	return transformGroup; 
   } 
    
    public TransformGroup MuevePared (int x, int y, int z)
    {
    	TransformGroup tg = translate(pard, new Vector3f(x, y, z));
    	return tg;
    }
    
}
