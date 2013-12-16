package be.davidcorp.metric.transfo;

import be.davidcorp.metric.Matrix;
import be.davidcorp.metric.Vector;

public class RotateTransfo extends Transfo{

	public RotateTransfo(float angle, float sx, float sy, float sz){
		Vector v = new Vector(sx, sy, sz);
		v.normalize();
		sx=v.x;
		sy=v.y;
		sz=v.z;
		angle = (float) Math.toRadians(angle);
		mat = new Matrix();
		mat.m[0][0] = (float) (Math.cos(angle)+Math.pow(sx, 2)*(1-Math.cos(angle)));
		mat.m[0][1] = (float) (sx*sy*(1-Math.cos(angle))-sz*Math.sin(angle));
		mat.m[0][2] = (float) (sx*sz*(1-Math.cos(angle))-sy*Math.sin(angle));
		
		mat.m[1][0] = (float) (sx*sy*(1-Math.cos(angle))+sz*Math.sin(angle));
		mat.m[1][1] = (float) (Math.cos(angle)+Math.pow(sy, 2)*(1-Math.cos(angle)));
		mat.m[1][2] = (float) (sy*sz*(1-Math.cos(angle))-sx*Math.sin(angle));
		
		mat.m[2][0] = (float) (sx*sz*(1-Math.cos(angle))-sy*Math.sin(angle));
		mat.m[2][1] = (float) (sy*sz*(1-Math.cos(angle))+sx*Math.sin(angle));
		mat.m[2][2] = (float) (Math.cos(angle)+Math.pow(sz, 2)*(1-Math.cos(angle)));
		
		invMat = new Matrix();
		
		invMat.m[0][0] = (float) (Math.cos(angle)+Math.pow(sx, 2)*(1-Math.cos(angle)));
		invMat.m[0][1] = (float) (sx*sy*(1-Math.cos(angle))+sz*Math.sin(angle));
		invMat.m[0][2] = (float) (sx*sz*(1-Math.cos(angle))+sy*Math.sin(angle));
		
		invMat.m[1][0] = (float) (sx*sy*(1-Math.cos(angle))-sz*Math.sin(angle));
		invMat.m[1][1] = (float) (Math.cos(angle)+Math.pow(sy, 2)*(1-Math.cos(angle)));
		invMat.m[1][2] = (float) (sy*sz*(1-Math.cos(angle))+sx*Math.sin(angle));
		
		invMat.m[2][0] = (float) (sx*sz*(1-Math.cos(angle))+sy*Math.sin(angle));
		invMat.m[2][1] = (float) (sy*sz*(1-Math.cos(angle))-sx*Math.sin(angle));
		invMat.m[2][2] = (float) (Math.cos(angle)+Math.pow(sz, 2)*(1-Math.cos(angle)));
	}
}
