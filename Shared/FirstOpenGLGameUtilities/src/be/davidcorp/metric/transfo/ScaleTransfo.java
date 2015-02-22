package be.davidcorp.metric.transfo;

import be.davidcorp.metric.Matrix;

public class ScaleTransfo extends Transfo{
	
	
	public ScaleTransfo(float sx, float sy, float sz){
		mat = new Matrix();
		float[][] ma = new float[4][4];
		ma[0][0]=sx;
		ma[1][1]=sy;
		ma[2][2]=sz;
		ma[3][3]=1;
		mat.m=ma;
		
		float[][] maInv = new float[4][4];
		maInv[0][0]=1/sx;
		maInv[1][1]=1/sy;
		maInv[2][2]=1/sz;
		maInv[3][3]=1;
		invMat = new Matrix();
		invMat.m=maInv;
	}


}
