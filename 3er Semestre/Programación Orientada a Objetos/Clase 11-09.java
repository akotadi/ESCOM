/*
*/

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;

public class Hola{
	public Hola{
		SimpleUniverse u = new SimpleUniverse(); // crea un universo simple
		BranchGroup group = new BranchGroup(); // es el grafo
		group.addChild(new ColorCube(0.3)); // crea un cubo
		u.getViewingPlatform()setNominalViewingTransform(); // coloca la cámara y las luces donde crea conveniente
		u.addBrandGraph(group); // le pasamos el grafo
	}
	public static void main(String[] args) {
		new Hola();
	}
}