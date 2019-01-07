
import com.sun.j3d.utils.geometry.Cylinder;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Triangulator;

import java.awt.Color;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.TexCoord2f;

public class CarBuilder {
    
    static Node Rueda = null;

    public static Node getRueda() {
        
        if( Rueda != null )       {
            return Rueda.cloneTree(true);
        }
        
        Group g = new Group();

        Appearance app =
            new Appearance();
        Appearance appC =
            new Appearance();

        Appearance appR1 = Tools.cargarTextura("tiret12.png");

        Appearance appR2 = Tools.cargarTextura("tire9b.png");

        Appearance appR3 = Tools.cargarTextura("tire19.png");
        
        Appearance appN = Tools.generarApariencia( Color.BLACK );
              
        Cylinder rueda =
            new Cylinder( 0.2413f, 0.18f, Cylinder.GENERATE_TEXTURE_COORDS |
                         Cylinder.GENERATE_NORMALS, 30, 30, app);
        rueda.setPickable( false );
        rueda.setCollidable(true);
        
        Shape3D externa = rueda.getShape(Cylinder.BODY);        

        externa.setAppearance(appR1);

        Cylinder aro =
            new Cylinder( 0.1605f, 0.181f, Cylinder.GENERATE_TEXTURE_COORDS |
                         Cylinder.GENERATE_NORMALS, 30, 30, appC);
        
        aro.setPickable( false );
        aro.setCollidable(true);;

        externa = aro.getShape(Cylinder.TOP);

        externa.setAppearance(appR2);
        
        externa = aro.getShape(Cylinder.BODY);

        externa.setAppearance(appN);

        externa = aro.getShape(Cylinder.BOTTOM);

        externa.setAppearance(appR2);

        externa = rueda.getShape(Cylinder.TOP);

        externa.setAppearance(appR3);

        externa = rueda.getShape(Cylinder.BOTTOM);

        externa.setAppearance(appR3);

        g.addChild(rueda);
        g.addChild(aro);        

        Rueda = Tools.rotarX(Math.PI / 2, g);

        return Rueda;
        
    }
    
    
    public static Node getBody() {
        
        Point3d p0 = new Point3d(0, 0.20, 0);
        Point3d p1 = new Point3d(0.04, 0.09, 0);
        Point3d p2 = new Point3d(0.68, 0.13, 0);
        Point3d p3 = new Point3d(2.64, 0.01, 0);
        Point3d p4 = new Point3d(5.25, 0.06, 0);

        Point3d p41 = new Point3d(5.55, 0.58, 0);
        Point3d p42 = new Point3d(5.86, 0.91, 0);
        Point3d p43 = new Point3d(6.69, 0.92, 0);
        Point3d p44 = new Point3d(6.92, 0.75, 0);

        Point3d p5 = new Point3d(7.18, 0.31, 0);
        Point3d p6 = new Point3d(7.51, 0.28, 0);
        Point3d p7 = new Point3d(7.53, 0.33, 0);
        Point3d p8 = new Point3d(7.60, 0.33, 0);

        Point3d p9 = new Point3d(7.60, 0.61, 0);
        Point3d p10 = new Point3d(7.50, 0.62, 0);
        Point3d p11 = new Point3d(7.50, 0.77, 0);
        Point3d p12 = new Point3d(7.33, 0.77, 0);
        Point3d p13 = new Point3d(7.32, 0.80, 0);
        Point3d p14 = new Point3d(7.27, 0.81, 0);
        Point3d p15 = new Point3d(7.22, 1.15, 0);
        Point3d p16 = new Point3d(7.17, 1.23, 0);
        Point3d p17 = new Point3d(5.12, 1.44, -0.10);

        Point3d p18 = new Point3d(4.27, 2.63, -0.25);
        Point3d p19 = new Point3d(2.81, 2.72, -0.25);
        Point3d p20 = new Point3d(0.88, 2.70, -0.25);
        Point3d p21 = new Point3d(0.75, 2.59, -0.25);
        Point3d p22 = new Point3d(0.38, 1.26, 0);
        Point3d p23 = new Point3d(0.34, 0.52, 0);
        Point3d p24 = new Point3d(0.17, 0.52, 0);
        Point3d p25 = new Point3d(0.17, 0.40, 0);
        Point3d p26 = new Point3d(0.0, 0.40, 0);


        Point3d pm0 = new Point3d(5.25, 0.06, -0.5);
        Point3d pm1 = new Point3d(5.25, 0.06, -2.0);
        Point3d pm2 = new Point3d(7.18, 0.31, -2.0);
        Point3d pm3 = new Point3d(7.18, 0.31, -0.5);

        Point3d pm4 = new Point3d(5.25, 1.1, -0.5);
        Point3d pm5 = new Point3d(5.25, 1.1, -2.0);
        Point3d pm6 = new Point3d(7.18, 1.1, -2.0);
        Point3d pm7 = new Point3d(7.18, 1.1, -0.5);


        Point3d[] ladoA =
        { p0, p1, p2, p3, p4, p41, p42, p43, p44, p5, p6, p7, p8, p9, p10, p11,
          p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25,
          p26, };

        Point3d[] ladoB = new Point3d[ladoA.length];

        int largo = ladoA.length;
        int l = largo - 1;

        double distancia = -2.5;

        for (int i = 0; i < largo; i++) {
            Point3d temp = (Point3d)ladoA[i].clone();
            temp.setZ(distancia + (temp.getZ() * -1));
            ladoB[largo - i - 1] = temp;
        }


        TexCoord2f[] textCoordLadoA = new TexCoord2f[largo];
        TexCoord2f[] textCoordLadoB = new TexCoord2f[largo];

        Color3f[] colores = new Color3f[largo];

        for (int i = 0; i < largo; i++) {

            Point3d temp = ladoA[i];
            TexCoord2f tc =
                new TexCoord2f((float)temp.getX() / 7.61f, (float)temp.getY() /
                               2.78f);

            textCoordLadoA[i] = tc;

        }

        for (int i = 0; i < largo; i++) {

            Point3d temp = ladoB[i];
            TexCoord2f tc =
                new TexCoord2f((float)temp.getX() / 7.61f, (float)temp.getY() /
                               2.78f);

            textCoordLadoB[i] = tc;

        }

        Color3f blanco = new Color3f();
        blanco.set(Color.GRAY);

        for (int i = 0; i < largo; i++) {
            colores[i] = blanco;
        }

        double z = -2;

        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);

        GeometryInfo gi2 = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);

        gi.setTextureCoordinateParams(1, 2);
        //gi.setCoordinates(m_VertexArray);
        gi.setCoordinates(ladoA);
        gi.setTextureCoordinates(0, textCoordLadoA);
        gi.setColors(colores);

        gi2.setTextureCoordinateParams(1, 2);
        //gi2.setCoordinates(m_VertexArray2);
        gi2.setCoordinates(ladoB);
        gi2.setTextureCoordinates(0, textCoordLadoB);
        gi2.setColors(colores);

        int[] stripCountArray = { largo };

        int[] countourCountArray = { stripCountArray.length };

        gi.setContourCounts(countourCountArray);
        gi.setStripCounts(stripCountArray);

        gi2.setContourCounts(countourCountArray);
        gi2.setStripCounts(stripCountArray);

        Triangulator triangulator = new Triangulator();
        triangulator.triangulate(gi);
        triangulator.triangulate(gi2);


        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(gi);
        normalGenerator.generateNormals(gi2);

        Appearance app = Tools.cargarTextura("perfil.png");

        Shape3D shape1 = new Shape3D(gi.getGeometryArray(), app);
        shape1.setPickable( false );        
        Shape3D shape2 = new Shape3D(gi2.getGeometryArray(), app);
        shape2.setPickable( false );

        Group g = new Group();

        g.addChild(shape1);
        g.addChild(shape2);

        // back

        Appearance appB = Tools.cargarTextura("back.png");

        Appearance appU = Tools.cargarTextura("up.png");

        Appearance appF = Tools.cargarTextura("front.png");

        Appearance appA = Tools.generarApariencia(Color.BLACK);

        Appearance appBA = Tools.generarApariencia(Color.GRAY);

        int numCaras = 3;

        QuadArray data =
            new QuadArray(numCaras * 4, QuadArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 |
                          QuadArray.NORMALS);


        data.setCoordinate(0, ladoA[27]);
        data.setCoordinate(1, ladoA[26]);
        data.setCoordinate(2, ladoB[l - 26]);
        data.setCoordinate(3, ladoB[l - 27]);

        data.setCoordinate(4, ladoA[26]);
        data.setCoordinate(5, ladoA[25]);
        data.setCoordinate(6, ladoB[l - 25]);
        data.setCoordinate(7, ladoB[l - 26]);

        data.setCoordinate(8, ladoA[25]);
        data.setCoordinate(9, ladoA[24]);
        data.setCoordinate(10, ladoB[l - 24]);
        data.setCoordinate(11, ladoB[l - 25]);

        data.setTextureCoordinate(0, 0, new TexCoord2f(0.92f, 0.188f));
        data.setTextureCoordinate(0, 1, new TexCoord2f(0.92f, 0.47f));
        data.setTextureCoordinate(0, 2, new TexCoord2f(0.08f, 0.465f));
        data.setTextureCoordinate(0, 3, new TexCoord2f(0.08f, 0.188f));

        data.setTextureCoordinate(0, 4, new TexCoord2f(0.943f, 0.43f));
        data.setTextureCoordinate(0, 5, new TexCoord2f(0.84f, 0.96f));
        data.setTextureCoordinate(0, 6, new TexCoord2f(0.16f, 0.96f));
        data.setTextureCoordinate(0, 7, new TexCoord2f(0.056f, 0.43f));

        data.setTextureCoordinate(0, 8, new TexCoord2f(0.9f, 0.48f));
        data.setTextureCoordinate(0, 9, new TexCoord2f(0.9f, 0.53f));
        data.setTextureCoordinate(0, 10, new TexCoord2f(0.1f, 0.53f));
        data.setTextureCoordinate(0, 11, new TexCoord2f(0.1f, 0.48f));

        GeometryInfo info = new GeometryInfo(GeometryInfo.QUAD_ARRAY);

        info.reset(data);

        NormalGenerator ng = new NormalGenerator();

        ng.generateNormals(info);

        data.setNormals(0, info.getNormals());

        Shape3D forma = new Shape3D(data, appB);

        forma.setPickable( false );
        
        g.addChild(forma);


        // techo, vidrio, capo

        numCaras = 4;

        data =
        new QuadArray(numCaras * 4, QuadArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 |
              QuadArray.NORMALS);


        data.setCoordinate(0, ladoA[24]);
        data.setCoordinate(1, ladoA[23]);
        data.setCoordinate(2, ladoB[l - 23]);
        data.setCoordinate(3, ladoB[l - 24]);

        data.setCoordinate(4, ladoA[23]);
        data.setCoordinate(5, ladoA[22]);
        data.setCoordinate(6, ladoB[l - 22]);
        data.setCoordinate(7, ladoB[l - 23]);

        data.setCoordinate(8, ladoA[22]);
        data.setCoordinate(9, ladoA[21]);
        data.setCoordinate(10, ladoB[l - 21]);
        data.setCoordinate(11, ladoB[l - 22]);

        data.setCoordinate(12, ladoA[21]);
        data.setCoordinate(13, ladoA[20]);
        data.setCoordinate(14, ladoB[l - 20]);
        data.setCoordinate(15, ladoB[l - 21]);

        data.setTextureCoordinate(0, 0, new TexCoord2f(0.975f, 0.24f));
        data.setTextureCoordinate(0, 1, new TexCoord2f(0.975f, 0.359f));
        data.setTextureCoordinate(0, 2, new TexCoord2f(0.59f, 0.359f));
        data.setTextureCoordinate(0, 3, new TexCoord2f(0.59f, 0.24f));

        data.setTextureCoordinate(0, 4, new TexCoord2f(0.981f, 0.359f));
        data.setTextureCoordinate(0, 5, new TexCoord2f(0.981f, 0.598f));
        data.setTextureCoordinate(0, 6, new TexCoord2f(0.59f, 0.598f));
        data.setTextureCoordinate(0, 7, new TexCoord2f(0.59f, 0.359f));

        data.setTextureCoordinate(0, 8, new TexCoord2f(0.088f, 0.982f));
        data.setTextureCoordinate(0, 9, new TexCoord2f(0.010f, 0.643f));
        data.setTextureCoordinate(0, 10, new TexCoord2f(0.863f, 0.643f));
        data.setTextureCoordinate(0, 11, new TexCoord2f(0.783f, 0.982f));

        data.setTextureCoordinate(0, 12, new TexCoord2f(0.537f, 0.158f));
        data.setTextureCoordinate(0, 13, new TexCoord2f(0.526f, 0.483f));
        data.setTextureCoordinate(0, 14, new TexCoord2f(0.037f, 0.483f));
        data.setTextureCoordinate(0, 15, new TexCoord2f(0.026f, 0.156f));

        info = new GeometryInfo(GeometryInfo.QUAD_ARRAY);

        info.reset(data);

        ng.generateNormals(info);

        data.setNormals(0, info.getNormals());

        forma = new Shape3D(data, appU);

        forma.setPickable( false );
        
        g.addChild(forma);


        // frente

        numCaras = 10;

        data =
        new QuadArray(numCaras * 4, QuadArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 |
              QuadArray.NORMALS);


        data.setCoordinate(0, ladoA[20]);
        data.setCoordinate(1, ladoA[19]);
        data.setCoordinate(2, ladoB[l - 19]);
        data.setCoordinate(3, ladoB[l - 20]);

        data.setCoordinate(4, ladoA[19]);
        data.setCoordinate(5, ladoA[18]);
        data.setCoordinate(6, ladoB[l - 18]);
        data.setCoordinate(7, ladoB[l - 19]);

        data.setCoordinate(8, ladoA[18]);
        data.setCoordinate(9, ladoA[17]);
        data.setCoordinate(10, ladoB[l - 17]);
        data.setCoordinate(11, ladoB[l - 18]);

        data.setCoordinate(12, ladoA[17]);
        data.setCoordinate(13, ladoA[16]);
        data.setCoordinate(14, ladoB[l - 16]);
        data.setCoordinate(15, ladoB[l - 17]);

        data.setCoordinate(16, ladoA[16]);
        data.setCoordinate(17, ladoA[15]);
        data.setCoordinate(18, ladoB[l - 15]);
        data.setCoordinate(19, ladoB[l - 16]);

        data.setCoordinate(20, ladoA[15]);
        data.setCoordinate(21, ladoA[14]);
        data.setCoordinate(22, ladoB[l - 14]);
        data.setCoordinate(23, ladoB[l - 15]);

        data.setCoordinate(24, ladoA[14]);
        data.setCoordinate(25, ladoA[13]);
        data.setCoordinate(26, ladoB[l - 13]);
        data.setCoordinate(27, ladoB[l - 14]);

        data.setCoordinate(28, ladoA[13]);
        data.setCoordinate(29, ladoA[12]);
        data.setCoordinate(30, ladoB[l - 12]);
        data.setCoordinate(31, ladoB[l - 13]);

        data.setCoordinate(32, ladoA[12]);
        data.setCoordinate(33, ladoA[11]);
        data.setCoordinate(34, ladoB[l - 11]);
        data.setCoordinate(35, ladoB[l - 12]);

        data.setCoordinate(36, ladoA[11]);
        data.setCoordinate(37, ladoA[10]);
        data.setCoordinate(38, ladoB[l - 10]);
        data.setCoordinate(39, ladoB[l - 11]);        

        data.setTextureCoordinate(0, 0, new TexCoord2f(1f, 0.93f));
        data.setTextureCoordinate(0, 1, new TexCoord2f(1f, 0.86f));
        data.setTextureCoordinate(0, 2, new TexCoord2f(0f, 0.86f));
        data.setTextureCoordinate(0, 3, new TexCoord2f(0f, 0.91f));

        data.setTextureCoordinate(0, 4, new TexCoord2f(0.995f, 0.86f));
        data.setTextureCoordinate(0, 5, new TexCoord2f(0.995f, 0.54f));
        data.setTextureCoordinate(0, 6, new TexCoord2f(0.005f, 0.51f));
        data.setTextureCoordinate(0, 7, new TexCoord2f(0.005f, 0.86f));

        data.setTextureCoordinate(0, 8, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 9, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 10, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 11, new TexCoord2f(1f, 0.05f));

        data.setTextureCoordinate(0, 12, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 13, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 14, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 15, new TexCoord2f(1f, 0.05f));

        data.setTextureCoordinate(0, 16, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 17, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 18, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 19, new TexCoord2f(1f, 0.05f));

        data.setTextureCoordinate(0, 20, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 21, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 22, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 23, new TexCoord2f(1f, 0.05f));

        data.setTextureCoordinate(0, 24, new TexCoord2f(0.1f, 0.29f));
        data.setTextureCoordinate(0, 25, new TexCoord2f(0.1f, 0.08f));
        data.setTextureCoordinate(0, 26, new TexCoord2f(0.9f, 0.08f));
        data.setTextureCoordinate(0, 27, new TexCoord2f(0.9f, 0.29f));

        data.setTextureCoordinate(0, 28, new TexCoord2f(0.1f, 0.29f));
        data.setTextureCoordinate(0, 29, new TexCoord2f(0.1f, 0.08f));
        data.setTextureCoordinate(0, 30, new TexCoord2f(0.9f, 0.08f));
        data.setTextureCoordinate(0, 31, new TexCoord2f(0.9f, 0.29f));

        data.setTextureCoordinate(0, 32, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 33, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 34, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 35, new TexCoord2f(1f, 0.05f));

        data.setTextureCoordinate(0, 36, new TexCoord2f(0f, 0.05f));
        data.setTextureCoordinate(0, 37, new TexCoord2f(0f, 0f));
        data.setTextureCoordinate(0, 38, new TexCoord2f(1f, 0f));
        data.setTextureCoordinate(0, 39, new TexCoord2f(1f, 0.05f));

        info = new GeometryInfo(GeometryInfo.QUAD_ARRAY);

        info.reset(data);

        ng.generateNormals(info);

        data.setNormals(0, info.getNormals());

        forma = new Shape3D(data, appF);

        forma.setPickable( false );
        
        g.addChild(forma);

        // abajo

        numCaras = 11;

        data =
        new QuadArray(numCaras * 4, QuadArray.COORDINATES | QuadArray.NORMALS);

        data.setCoordinate(0, ladoA[3]);
        data.setCoordinate(1, ladoA[2]);
        data.setCoordinate(2, ladoB[l - 2]);
        data.setCoordinate(3, ladoB[l - 3]);

        data.setCoordinate(4, ladoA[4]);
        data.setCoordinate(5, ladoA[3]);
        data.setCoordinate(6, ladoB[l - 3]);
        data.setCoordinate(7, ladoB[l - 4]);

        data.setCoordinate(8, ladoA[5]);
        data.setCoordinate(9, ladoA[4]);
        data.setCoordinate(10, ladoB[l - 4]);
        data.setCoordinate(11, ladoB[l - 5]);

        data.setCoordinate(12, ladoA[6]);
        data.setCoordinate(13, ladoA[5]);
        data.setCoordinate(14, ladoB[l - 5]);
        data.setCoordinate(15, ladoB[l - 6]);

        data.setCoordinate(16, ladoA[7]);
        data.setCoordinate(17, ladoA[6]);
        data.setCoordinate(18, ladoB[l - 6]);
        data.setCoordinate(19, ladoB[l - 7]);

        data.setCoordinate(20, ladoA[8]);
        data.setCoordinate(21, ladoA[7]);
        data.setCoordinate(22, ladoB[l - 7]);
        data.setCoordinate(23, ladoB[l - 8]);

        data.setCoordinate(24, ladoA[9]);
        data.setCoordinate(25, ladoA[8]);
        data.setCoordinate(26, ladoB[l - 8]);
        data.setCoordinate(27, ladoB[l - 9]);

        data.setCoordinate(28, ladoA[10]);
        data.setCoordinate(29, ladoA[9]);
        data.setCoordinate(30, ladoB[l - 9]);
        data.setCoordinate(31, ladoB[l - 10]);

        data.setCoordinate(32, pm0);
        data.setCoordinate(33, pm1);
        data.setCoordinate(34, pm2);
        data.setCoordinate(35, pm3);

        data.setCoordinate(36, pm0);
        data.setCoordinate(37, pm3);
        data.setCoordinate(38, pm7);
        data.setCoordinate(39, pm4);

        data.setCoordinate(40, pm2);
        data.setCoordinate(41, pm1);
        data.setCoordinate(42, pm5);
        data.setCoordinate(43, pm6);

        info = new GeometryInfo(GeometryInfo.QUAD_ARRAY);

        info.reset(data);

        ng.generateNormals(info);

        data.setNormals(0, info.getNormals());

        forma = new Shape3D(data, appA);

        forma.setPickable( false );
        
        g.addChild(forma);

        numCaras = 6;

        data =
        new QuadArray(numCaras * 4, QuadArray.COORDINATES | QuadArray.NORMALS);

        data.setCoordinate(0, ladoA[2]);
        data.setCoordinate(1, ladoA[1]);
        data.setCoordinate(2, ladoB[l - 1]);
        data.setCoordinate(3, ladoB[l - 2]);

        data.setCoordinate(4, ladoA[1]);
        data.setCoordinate(5, ladoA[0]);
        data.setCoordinate(6, ladoB[l - 0]);
        data.setCoordinate(7, ladoB[l - 1]);

        data.setCoordinate(8, ladoA[0]);
        data.setCoordinate(9, ladoA[l]);
        data.setCoordinate(10, ladoB[l - l]);
        data.setCoordinate(11, ladoB[l - 0]);

        data.setCoordinate(12, ladoA[29]);
        data.setCoordinate(13, ladoA[28]);
        data.setCoordinate(14, ladoB[l - 28]);
        data.setCoordinate(15, ladoB[l - 29]);

        data.setCoordinate(16, ladoA[28]);
        data.setCoordinate(17, ladoA[27]);
        data.setCoordinate(18, ladoB[l - 27]);
        data.setCoordinate(19, ladoB[l - 28]);

        data.setCoordinate(20, ladoA[30]);
        data.setCoordinate(21, ladoA[29]);
        data.setCoordinate(22, ladoB[l - 29]);
        data.setCoordinate(23, ladoB[l - 30]);

        info = new GeometryInfo(GeometryInfo.QUAD_ARRAY);

        info.reset(data);

        ng.generateNormals(info);

        data.setNormals(0, info.getNormals());

        forma = new Shape3D(data, appBA);

        forma.setPickable( false );
        
        g.addChild( forma );
        
        TransformGroup tg = new TransformGroup();
        Transform3D ts = new Transform3D();
        ts.setScale( 0.4 );
        tg.setTransform( ts );
        tg.addChild( Tools.trasladar( -1.7f, -0.1f, 1.25f , g) );        
        
        return tg;
        
    }
    
}
