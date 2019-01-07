import java.awt.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Cad extends Frame {
    VirtualUniverse miUniverso;
    Locale miLocale;
    BranchGroup objRoot, Primero;
    Canvas3D canvasPerspectiva, canvasAlzado;
    Canvas3D canvasPerfil, canvasPlanta;
    static Container principal;
public Cad(){
	super("MicroCAD");
 	miUniverso = new VirtualUniverse();
	miLocale = new Locale(miUniverso);
        objRoot = new BranchGroup(); Primero = new BranchGroup();
    	Primero.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
    	Primero.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
    	objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
    	objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
    	objRoot.addChild(Primero);
        principal = new Container();
   	principal.setLayout(new GridLayout(2,2));
   canvasAlzado = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		principal.add(canvasAlzado);
		canvasPerfil = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		principal.add(canvasPerfil);
		canvasPlanta = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		principal.add(canvasPlanta);
		canvasPerspectiva = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		principal.add(canvasPerspectiva);
	constructViewBranch(construirVista(canvasAlzado), 0);
	constructViewBranch(construirVista(canvasPerfil), 1);
	constructViewBranch(construirVista(canvasPlanta), 2);
	constructViewBranch(construirVista(canvasPerspectiva), 3);
        objRoot.removeAllChildren();
        Primero.addChild(new Raton()); objRoot.addChild(Primero);
	constructContentBranch();
	add ("Center", principal); setVisible(true);
}
private View construirVista(Canvas3D canvas){
		View miVista = new View(); miVista.addCanvas3D(canvas);
		PhysicalBody miCuerpo = new PhysicalBody();
		miVista.setPhysicalBody(miCuerpo);
		miVista.setPhysicalEnvironment(new PhysicalEnvironment());
		return miVista;
}
private void constructViewBranch(View miVista, int tipo){
		BranchGroup miBranchGroup = new BranchGroup();
		TransformGroup miTransformGroup = new TransformGroup();
		Transform3D t = new Transform3D(); Transform3D t1 = new Transform3D();
		Transform3D t2 = new Transform3D(); Transform3D t3 = new Transform3D();
		switch (tipo){
			case 0: //alzado
					t.setTranslation(new Vector3f(0.0f, 0.0f, 15.0f));
					break;
			case 1: t.rotY(Math.PI/2.0f);//perfilb
					t.setTranslation(new Vector3f(15.0f, 0.0f, 0.0f));
					break;
			case 2: t.rotX(-Math.PI/2.0f);//planta
					t.setTranslation(new Vector3f(0.0f, 15.0f, 0.0f));
					break;
			case 3: t.rotX(-Math.PI/4.0f);//perspectiva
					t1.rotY(Math.PI/4.0f); t.mul(t1);
					t2.rotZ(Math.PI/4.0f); t.mul(t2);
					t.setTranslation(new Vector3f(10.0f, 10.0f, 10.0f));
					break;
		}
		miTransformGroup.setTransform(t);
		ViewPlatform miViewPlatform = new ViewPlatform();
		miTransformGroup.addChild(miViewPlatform);
		miBranchGroup.addChild(miTransformGroup);
		miLocale.addBranchGraph(miBranchGroup);
		miVista.attachViewPlatform(miViewPlatform);
}
private void constructContentBranch(){
		miLocale.addBranchGraph(objRoot);
}
public static void main(String[] args) { Cad cad=new Cad();}
}
