package be.davidcorp.view.game;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {

	private List<Integer> vaos = new ArrayList<>();
	private List<Integer> vbos = new ArrayList<>();
	private int vaoId;

	public RawModel loadToVao(float[] positions){
		GL30.glBindVertexArray(vaoId);
		storeDataInAttributesList(0, positions);
		unbindVao();
		return new RawModel(vaoId, positions.length/3);
	}

	public void cleanUp(){
		vaos.forEach(GL30::glDeleteVertexArrays);
		vbos.forEach(GL15::glDeleteBuffers);
	}

	public int createVao(){
		vaoId = GL30.glGenVertexArrays();
		vaos.add(vaoId);
		return vaoId;
	}

	private void storeDataInAttributesList(int attributeNumber, float data[]){
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = convertToFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private void unbindVao(){
		GL30.glBindVertexArray(0);
	}

	private FloatBuffer convertToFloatBuffer(float[] data){
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(data.length);
		floatBuffer.put(data);
		floatBuffer.flip();
		return floatBuffer;
	}
}
