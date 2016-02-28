package be.davidcorp.view.game;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec3;
import com.hackoeur.jglm.Vec4;

import be.davidcorp.metric.Matrix;

public class MVPManager {
	private static MVPManager instance = new MVPManager();

	public Projection projection;
	private Mat4 model = new Mat4(1.0f);
	private int mvpLocation;

	private MVPManager(){}

	public static MVPManager getInstance(){
		return instance;
	}

	public void init(int programId){
		mvpLocation = glGetUniformLocation(programId, "mvp");
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public void mvpToShader(){
		glUniformMatrix4fv(mvpLocation, false, createMvpMatrix());
	}

	public void setTranslation(Matrix mat){
		model = matrixToMat4(mat);
	}

	private FloatBuffer createMvpMatrix(){
		if(projection == null){
			throw new RuntimeException("Projection matrix is not initialized");
		}

		FloatBuffer mvpMatrix = BufferUtils.createFloatBuffer(16);

//		Mat4 projectionMat = Matrices.ortho2d(projection.left, projection.right, projection.bottom, projection.top);
		Mat4 projectionMat = Matrices.perspective(45.0f, 4.0f / 3.0f, 0.1f, 100.0f);
		Mat4 view = Matrices.lookAt(new Vec3(0, 0, 4), new Vec3(0, 0, 0), new Vec3(0, 1, 0));
		Mat4 MVP = projectionMat.multiply(view).multiply(model);

		mvpMatrix.put(MVP.getBuffer());
		mvpMatrix.flip();
		return mvpMatrix;
	}

	private Mat4 matrixToMat4(Matrix mat){
		return new Mat4(
				new Vec4(mat.m[0][0], mat.m[0][1], mat.m[0][2], mat.m[0][3]),
				new Vec4(mat.m[1][0], mat.m[1][1], mat.m[1][2], mat.m[1][3]),
				new Vec4(mat.m[2][0], mat.m[2][1], mat.m[2][2], mat.m[2][3]),
				new Vec4(mat.m[3][0], mat.m[3][1], mat.m[3][2], mat.m[3][3])
		);
	}
}
