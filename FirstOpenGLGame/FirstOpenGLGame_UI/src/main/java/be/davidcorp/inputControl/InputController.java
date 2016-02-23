package be.davidcorp.inputControl;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_0;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_3;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_5;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_6;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_7;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_8;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_9;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DELETE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_I;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import be.davidcorp.view.FrameBuffer;
import be.davidcorp.view.MousePosition;

public class InputController {

	protected boolean mousePushedDown;
	private GLFWKeyCallback keyCallback;
	private GLFWCursorPosCallback cursorPosCallback;
	private FrameBuffer framebuffer;
	private GLFWMouseButtonCallback mouseButtonCallback;

	public void registerInputCallbacks(long window, FrameBuffer framebuffer) {
		this.framebuffer = framebuffer;
		checkKeyboard(window);
		checkMouse(window);
	}

	protected void checkMouse(long window) {
		glfwSetMouseButtonCallback(window, (mouseButtonCallback = new GLFWMouseButtonCallback() {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				if(button == 0) {
					if(action == GLFW_PRESS) {
						onMouseLeftPressed();
						mousePushedDown = true;
					} else if(action == GLFW_RELEASE) {
						on_MOUSELEFT_released();
						mousePushedDown = false;
					}
				}
				if(button == 1) {
					if(action == GLFW_PRESS) {
						onMouseRightPressed();
					}
				}
			}

		}));

		glfwSetCursorPosCallback(window, (cursorPosCallback = new GLFWCursorPosCallback() {

			@Override
			public void invoke(long window, double xpos, double ypos) {
				MousePosition.X = (float) xpos;
				MousePosition.Y = (float) (framebuffer.height - ypos);
				onMouseMoved();
			}

		}));
	}

	protected void checkKeyboard(long window) {
		glfwSetKeyCallback(window, (keyCallback = new GLFWKeyCallback() {

			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (action == GLFW_RELEASE) {
					checkArrows(key);
					if (key == GLFW_KEY_SPACE) {
						onSpaceKey();
					}
					if (key == GLFW_KEY_DELETE) {
						onDeletePressed();
					}
					checkLetters(key);
					checkNumberKeys(key);

				}
			}

		}));

	}

	private void checkLetters(int key) {

		if (key == GLFW_KEY_I) {
			on_I_Key_pressed();
		}

		if (key == GLFW_KEY_O) {
			on_O_Key_pressed();
		}

		if (key == GLFW_KEY_G) {
			on_G_Key_pressed();
		}

		if (key == GLFW_KEY_E) {
			on_E_Key_pressed();
		}

		if (key == GLFW_KEY_F) {
			on_F_Key_pressed();
		}

		if (key == GLFW_KEY_P) {
			on_P_Key_pressed();
		}
	}

	private void checkNumberKeys(int key) {
		if (key == GLFW_KEY_0) {
			on_0_Key();
		}
		if (key == GLFW_KEY_1) {
			on_1_Key();
		}
		if (key == GLFW_KEY_2) {
			on_2_Key();
		}
		if (key == GLFW_KEY_3) {
			on_3_Key();
		}
		if (key == GLFW_KEY_4) {
			on_4_Key();
		}
		if (key == GLFW_KEY_5) {
			on_5_Key();
		}
		if (key == GLFW_KEY_6) {
			on_6_Key();
		}
		if (key == GLFW_KEY_7) {
			on_7_Key();
		}
		if (key == GLFW_KEY_8) {
			on_8_Key();
		}
		if (key == GLFW_KEY_9) {
			on_9_Key();
		}
	}

	private void checkArrows(int key) {
		if (key == GLFW_KEY_LEFT || key == GLFW_KEY_Q) {
			on_LEFT_Key();
		}

		if (key == GLFW_KEY_RIGHT || key == GLFW_KEY_D) {
			on_RIGHT_Key();
		}

		if (key == GLFW_KEY_UP || key == GLFW_KEY_Z) {
			on_UP_Key();
		}

		if (key == GLFW_KEY_DOWN || key == GLFW_KEY_S) {
			on_DOWN_Key();
		}
	}

	public void onSpaceKey() {
	}

	;

	public void on_0_Key() {
	}

	;

	public void on_1_Key() {
	}

	;

	public void on_2_Key() {
	}

	;

	public void on_3_Key() {
	}

	;

	public void on_4_Key() {
	}

	;

	public void on_5_Key() {
	}

	;

	public void on_6_Key() {
	}

	;

	public void on_7_Key() {
	}

	;

	public void on_8_Key() {
	}

	;

	public void on_9_Key() {
	}

	;

	public void on_I_Key_pressed() {
	}

	;

	public void on_O_Key_pressed() {
	}

	;

	public void on_G_Key_pressed() {
	}

	;

	public void on_E_Key_pressed() {
	}

	;

	public void on_F_Key_pressed() {
	}

	;

	public void on_P_Key_pressed() {
	}

	;

	//arrows
	public void on_LEFT_Key() {
	}

	;

	public void on_RIGHT_Key() {
	}

	;

	public void on_UP_Key() {
	}

	;

	public void on_DOWN_Key() {
	}

	;

	//MOUSE
	public void onMouseLeftPressed() {
	}

	;

	public void onMouseRightPressed() {
	}

	;

	public void on_MOUSELEFT_released() {
	}

	;

	public void on_MOUSERIGHT_released() {
	}

	;

	public void onMouseMoved() {
	}

	;

	//OTHER
	public void onDeletePressed() {
	}

	;

}
