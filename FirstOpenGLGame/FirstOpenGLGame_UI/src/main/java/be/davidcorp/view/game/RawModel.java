package be.davidcorp.view.game;

public class RawModel {

	private int vaoID;
	private int vertexCount;

	public RawModel(int vertexCount, int vaoID) {
		this.vertexCount = vertexCount;
		this.vaoID = vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}
}
