package be.davidcorp.engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec3;

import be.davidcorp.metric.Point;

public class Window {

	private ShaderLoader shaderLoader = new ShaderLoader();
	private VaoCreator vaoCreator = new VaoCreator();
	private VboCreator vboCreator = new VboCreator();
	private ModelContainer modelContainer = new ModelContainer();
	private ModelDrawer modelDrawer;

	public Window(int width, int height, GameLoop gameLoop) {
		modelDrawer = new ModelDrawer(modelContainer);

		NativeLoader.loadNatives();
		long window = Display.setup(width, height);

		int programId = shaderLoader.loadShaders("shaders/SimpleVertexShader.vertexshader", "shaders/SimpleFragmentShader.fragmentshader");

		vaoCreator.initVao();
		vboCreator.initVbo();
		vboCreator.bindBuffer();

		modelDrawer.drawTriangle(new Point(-1f,-1f,0f), new Point(1f,-1f,0f), new Point(0f,1f,0f));
		int mvpId = glGetUniformLocation(programId, "MVP");

		while (glfwWindowShouldClose(window) == GLFW_FALSE) {
			Display.clear();
			useShaders(programId);
			glUniformMatrix4fv(mvpId, false, createMvpMatrix());

			drawModels();
			gameLoop.execute();

			Display.updateDisplay();
		}
		Display.stopOpenGl();
	}

	private void drawModels(){
		glEnableVertexAttribArray(0);
		vboCreator.bindBuffer();

		glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

		// Draw the triangle !
		int totalNumberOfVertices = modelContainer.getModels().stream().mapToInt(Model::getNumberOfVertices).sum();
		List<Float> vertices = new ArrayList<>();
		for (Model model : modelContainer.getModels()) {
			for (Point point : model.getPoints()) {
				vertices.add(point.x);
				vertices.add(point.y);
				vertices.add(point.z);
			}
		}
		glBufferData(GL_ARRAY_BUFFER, convertToFloatBuffer(vertices), GL_STATIC_DRAW);
		glDrawArrays(GL_TRIANGLES, 0, 3);
		glDisableVertexAttribArray(0);
	}

	private static void useShaders(int programId) {
		glUseProgram(programId);
	}

	private static FloatBuffer createMvpMatrix(){
		Mat4 model = new Mat4(1.0f);
		FloatBuffer mvpMatrix = BufferUtils.createFloatBuffer(16);

		//		Mat4 projectionMat = Matrices.ortho2d(projection.left, projection.right, projection.bottom, projection.top);
		Mat4 projectionMat = Matrices.perspective(45.0f, 4.0f / 3.0f, 0.1f, 100.0f);
		Mat4 view = Matrices.lookAt(new Vec3(0, 0, 4), new Vec3(0, 0, 0), new Vec3(0, 1, 0));
		Mat4 MVP = projectionMat.multiply(view).multiply(model);

		mvpMatrix.put(MVP.getBuffer());
		mvpMatrix.flip();
		return mvpMatrix;
	}

	private FloatBuffer convertToFloatBuffer(List<Float> data){
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(data.size());
		floatBuffer.put(convertFloats(data));
		floatBuffer.flip();
		return floatBuffer;
	}

	public static float[] convertFloats(List<Float> floats)
	{
		float[] ret = new float[floats.size()];
		Iterator<Float> iterator = floats.iterator();
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = iterator.next().intValue();
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		new Window(800, 600, new GameLoop());
	}
}
