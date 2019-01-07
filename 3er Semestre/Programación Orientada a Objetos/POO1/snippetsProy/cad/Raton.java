import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;



public class Raton extends BranchGroup {

public Raton(){
 
    Vector3f vector = new Vector3f(0.0f, 0.0f, 0.0f);

    Transform3D transform = new Transform3D();

    transform.setTranslation(vector);

    Vector3d escala = new Vector3d(1.0f, 1.0f, 1.2f);

    transform.setScale(escala);

    TransformGroup transformGroup = new TransformGroup(transform);
  
Material yellowSphMaterial = new Material();
yellowSphMaterial.setDiffuseColor(0.85f, 0.85f, 0.85f);
Appearance yellowSphAppearance = new Appearance();
yellowSphAppearance.setMaterial(yellowSphMaterial);
Sphere yellowSph = new Sphere(0.6f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);
   
    transformGroup.addChild(yellowSph);


    addChild(transformGroup);


    Vector3f vector2 = new Vector3f(0.40f, -0.55f, 0.15f);

    Transform3D transform2 = new Transform3D();

    transform2.setTranslation(vector2);

    Vector3d escala2 = new Vector3d(1.3f, 0.45f, 2.2f);

    transform2.setScale(escala2);

    TransformGroup transformGroup2 = new TransformGroup(transform2);

    Sphere pata1 = new Sphere(0.2f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup2.addChild(pata1);

    addChild(transformGroup2);

Vector3f vector3 = new Vector3f(-0.40f, -0.55f, 0.15f);

    Transform3D transform3 = new Transform3D();

    transform3.setTranslation(vector3);

    Vector3d escala3 = new Vector3d(1.3f, 0.45f, 2.2f);

    transform3.setScale(escala3);

    TransformGroup transformGroup3 = new TransformGroup(transform3);

    Sphere pata2 = new Sphere(0.2f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup3.addChild(pata2);

    addChild(transformGroup3);

Vector3f vector4 = new Vector3f(-0.40f, 0.52f, 0.15f);

    Transform3D transform4 = new Transform3D();

    transform4.setTranslation(vector4);

    Vector3d escala4 = new Vector3d(1.0f, 1.0f, 0.05f);

    transform4.setScale(escala4);
    //transform4.rotZ(Math.PI/2.0d);

    TransformGroup transformGroup4 = new TransformGroup(transform4);

    Sphere oreja = new Sphere(0.35f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup4.addChild(oreja);

    addChild(transformGroup4);


Vector3f vector5 = new Vector3f(0.40f, 0.52f, 0.15f);

    Transform3D transform5 = new Transform3D();

    transform5.setTranslation(vector5);
    Vector3d escala5 = new Vector3d(1.0f, 1.0f, 0.05f);

    transform5.setScale(escala5);

    TransformGroup transformGroup5 = new TransformGroup(transform5);

    Sphere oreja2 = new Sphere(0.35f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup5.addChild(oreja2);

    addChild(transformGroup5);

Material blackMaterial = new Material();
blackMaterial.setDiffuseColor(1.0f, 1.0f, 0.0f);
Appearance blackAppearance = new Appearance();
blackAppearance.setMaterial(blackMaterial);


Vector3f vector6 = new Vector3f(0.1f, 0.2f, 0.6f);

Transform3D transform6 = new Transform3D();

    transform6.setTranslation(vector6);

    TransformGroup transformGroup6 = new TransformGroup(transform6);

    Sphere ojo = new Sphere(0.07f,
Primitive.GENERATE_NORMALS, 32,
blackAppearance);

    transformGroup6.addChild(ojo);

    addChild(transformGroup6);

Vector3f vector7 = new Vector3f(-0.1f, 0.2f, 0.6f);
Transform3D transform7 = new Transform3D();
    transform7.setTranslation(vector7);
    TransformGroup transformGroup7 = new TransformGroup(transform7);
    Sphere ojo1 = new Sphere(0.07f,
Primitive.GENERATE_NORMALS, 32,
blackAppearance);

    transformGroup7.addChild(ojo1);

    addChild(transformGroup7);

Vector3f vector8 = new Vector3f(-0.36f, 0.0f, 0.55f);
Transform3D transform8 = new Transform3D();
    transform8.rotZ(Math.PI/1.5d);
    transform8.setTranslation(vector8);
    
    TransformGroup transformGroup8 = new TransformGroup(transform8);
    Cylinder bigo1 = new Cylinder(0.01f,0.3f ,
blackAppearance);

    transformGroup8.addChild(bigo1);

    addChild(transformGroup8);


Vector3f vector9 = new Vector3f(-0.36f, 0.15f, 0.55f);
Transform3D transform9 = new Transform3D();
Transform3D transform9_1 = new Transform3D();

    transform9.rotZ(Math.PI/3.0d);
    //transform9_1.setIdentity();
    transform9_1.rotY(Math.PI*0.75);
    //transform9.mul(transform9_1);
    transform9.setTranslation(vector9);
    
    TransformGroup transformGroup9 = new TransformGroup(transform9);
    Cylinder bigo2 = new Cylinder(0.01f,0.3f ,
blackAppearance);

    transformGroup9.addChild(bigo2);

    addChild(transformGroup9);
    
Vector3f vector10 = new Vector3f(0.36f, 0.0f, 0.55f);
Transform3D transform10 = new Transform3D();
    transform10.rotZ(Math.PI/3.0d);
    transform10.setTranslation(vector10);
    
    TransformGroup transformGroup10 = new TransformGroup(transform10);
    Cylinder bigo3 = new Cylinder(0.01f,0.3f ,
blackAppearance);

    transformGroup10.addChild(bigo3);

    addChild(transformGroup10);

Vector3f vector11 = new Vector3f(0.36f, 0.15f, 0.55f);
Transform3D transform11 = new Transform3D();
    transform11.rotZ(Math.PI/1.5d);
    transform11.setTranslation(vector11);
    
    TransformGroup transformGroup11 = new TransformGroup(transform11);
    Cylinder bigo4 = new Cylinder(0.01f,0.3f ,
blackAppearance);

    transformGroup11.addChild(bigo4);

    addChild(transformGroup11);

    Vector3f vector12 = new Vector3f(-0.40f, 0.52f, 0.16f);
    
    Transform3D transform12 = new Transform3D();

    transform12.setTranslation(vector12);
 
     Vector3d escala12 = new Vector3d(0.35f, 0.35f, 0.05f);

    transform12.setScale(escala12);

    TransformGroup transformGroup12 = new TransformGroup(transform12);

    Sphere oido1 = new Sphere(0.35f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup12.addChild(oido1);

    addChild(transformGroup12);

Vector3f vector13 = new Vector3f(0.40f, 0.52f, 0.16f);
    
    Transform3D transform13 = new Transform3D();

    transform13.setTranslation(vector13);
 
     Vector3d escala13 = new Vector3d(0.35f, 0.35f, 0.05f);

    transform13.setScale(escala13);

    TransformGroup transformGroup13 = new TransformGroup(transform13);

    Sphere oido2 = new Sphere(0.35f,
Primitive.GENERATE_NORMALS, 32,
yellowSphAppearance);

    transformGroup13.addChild(oido2);

    addChild(transformGroup13);
    // Create lights

   Color3f light1Color = new Color3f(1f, 1f, 1f);

    BoundingSphere bounds =

    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

   DirectionalLight light1

       = new DirectionalLight(light1Color, light1Direction);

    light1.setInfluencingBounds(bounds);

   addChild(light1);

   AmbientLight ambientLight =

      new AmbientLight(new Color3f(.5f,.5f,.5f));

   ambientLight.setInfluencingBounds(bounds);

   addChild(ambientLight);
}


} // end of class Raton
