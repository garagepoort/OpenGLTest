package be.davidcorp.transfo;

import be.davidcorp.metric.Matrix;

public class Transfo {
	public Matrix mat;
	public Matrix invMat;

	public Transfo() {
		mat = new Matrix();
		invMat = new Matrix();
	}
	
	public Transfo(Transfo transfo){
		mat = new Matrix(transfo.mat);
		invMat = new Matrix(transfo.invMat);
	}

	public void transform(Transfo transfo) {
		mat.postMult(transfo.mat);
		invMat.preMult(transfo.invMat);
		
	}
}