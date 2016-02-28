package be.davidcorp.view.game;

public class StaticShaderProgram extends ShaderProgram {

	private static final String VERTEX_SHADER_FILE = "shaders/SimpleVertexShader.vertexshader";
	private static final String FRAGMENT_SHADER_FILE = "shaders/SimpleFragmentShader.fragmentshader";

	public StaticShaderProgram() {
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
		bindAttribute(1, "vertexColor");
	}

}
