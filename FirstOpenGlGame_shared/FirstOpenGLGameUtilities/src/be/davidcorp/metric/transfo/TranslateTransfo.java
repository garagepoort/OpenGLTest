package be.davidcorp.metric.transfo;

import be.davidcorp.metric.Matrix;

public class TranslateTransfo extends Transfo{

	public TranslateTransfo(float sx, float sy, float sz){
		mat = new Matrix();
		mat.m[0][3]=sx;
		mat.m[1][3]=sy;
		mat.m[2][3]=sz;
		
		invMat = new Matrix();
		invMat.m[0][3]=-sx;
		invMat.m[1][3]=-sy;
		invMat.m[2][3]=-sz;
	}
}
