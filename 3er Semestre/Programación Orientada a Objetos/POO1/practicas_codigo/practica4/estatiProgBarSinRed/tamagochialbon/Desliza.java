import javax.media.j3d.*;
import javax.vecmath.*;
public class Desliza {
     	public static TransformGroup desliza(Node node, Transform3D xAxis, Alpha alpha, float a, float b) {
	TransformGroup xformGroup = new TransformGroup();
	xformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
PositionInterpolator interpolator = new PositionInterpolator( alpha,
                 xformGroup, xAxis, a, b );
interpolator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));
	xformGroup.addChild(interpolator); xformGroup.addChild(node);
        return xformGroup;
} 
}
