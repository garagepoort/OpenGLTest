package be.davidcorp.metric;


public class Matrix {

	public float[][] m = new float[4][4];

	public Matrix(){
		for(int i=0; i<4;i++){
			m[i][i]=1;
			for(int j=i+1;j<4;j++){
				m[i][j] = 0;
				m[j][i] = 0;
			}		
		}
	}

	public Matrix(Matrix a){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				m[i][j] = a.m[i][j];
			}
		}
	}

	public Point mult(Point p){
		float[][] pm = new float[4][1];
		pm[0][0]=p.x;
		pm[1][0]=p.y;
		pm[2][0]=p.z;
		pm[3][0]=1;
		float[][] result = new float[4][1];
		for(int i=0; i<4;i++){
			result[i][0] = m[i][0]*pm[0][0] + m[i][1]*pm[1][0] + m[i][2]*pm[2][0] + m[i][3]*pm[3][0];
		}
		Point pointResult = new Point(result[0][0], result[1][0], result[2][0]);
		return pointResult;
	}

	public Vector mult(Vector v){
		float[][] pm = new float[4][1];
		pm[0][0]=v.x;
		pm[1][0]=v.y;
		pm[2][0]=v.z;
		pm[3][0]=0;
		float[][] result = new float[4][1];
		for(int i=0; i<4;i++){
			result[i][0] = m[i][0]*pm[0][0] + m[i][1]*pm[1][0] + m[i][2]*pm[2][0] + m[i][3]*pm[3][0];
		}
		Vector vector = new Vector(result[0][0], result[1][0], result[2][0]);
		return vector;
	}

	public Matrix getTranspose(){
		Matrix res = new Matrix();
		for(int i=0;i<4;i++){
			res.m[i][i]=m[i][i];
			for(int j=i;j<4;j++){
				res.m[i][j]=m[j][i];
				res.m[j][i]=m[i][j];
			}
		}
		return res;
	}

	public void preMult ( Matrix a){
		float sum = 0;
		Matrix tmp = new Matrix ( this );
		for(int r=0; r <4; r ++){
			for (int c=0; c <4; c ++){
				for(int k=0; k <4; k ++){
					sum +=a.m[r][k]* tmp.m[k][c];
				}
				m[r][c] = sum;
				sum=0;
			}
		}
	}
	
	public void postMult(Matrix a){
		float sum = 0;
		Matrix tmp = new Matrix ( this );
		for(int r=0; r<4; r++){
			for (int c=0; c<4; c++){
				for(int k=0; k<4; k++){
					sum += tmp.m[r][k]*a.m[k][c];
				}
				m[r][c] = sum;
				sum = 0;
			}
		}
	}

}
