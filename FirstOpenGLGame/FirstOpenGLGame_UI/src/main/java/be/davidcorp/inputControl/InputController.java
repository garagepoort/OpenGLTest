package main.java.be.davidcorp.inputControl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InputController {

	protected boolean mousePushedDown;
	
	public void checkInput(){
		checkKeyboard();
		checkMouse();
	}

	protected void checkMouse(){
		if (Mouse.next()) {
			if (!Mouse.isButtonDown(0)) {
				on_MOUSELEFT_released();
				mousePushedDown = false;
			}else{
				onMouseLeftPressed();
				mousePushedDown = true;
			}
			if(Mouse.isButtonDown(1)){
				onMouseRightPressed();
			}
			onMouseMoved();
//			//if(Mouse.getEventButtonState()){
//				if (Mouse.isButtonDown(0)) {
//					on_MOUSELEFT_pressed();
//				}else if (Mouse.isButtonDown(1)){
//					on_MOUSERIGHT_pressed();
//				}
//			//}else{
//			if(!Mouse.getEventButtonState()){
//				if (Mouse.isButtonDown(0)) {
//					on_MOUSELEFT_released();
//				}else if (Mouse.isButtonDown(1)){
//					on_MOUSERIGHT_released();
//				}
//			}
		}
	}

	protected void checkKeyboard() {
		checkArrows();
		if (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {

			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					onSpaceKey();
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_DELETE){
					onDeletePressed();
				}
				checkLetters();
				checkNumberKeys();
			}
		}

	}

	private void checkLetters() {
		if (Keyboard.getEventKey() == Keyboard.KEY_I) {
			on_I_Key_pressed();
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_O) {
			on_O_Key_pressed();
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_G) {
			on_G_Key_pressed();
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_E) {
			on_E_Key_pressed();
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_F) {
			on_F_Key_pressed();
		}

		if (Keyboard.getEventKey() == Keyboard.KEY_P) {
			on_P_Key_pressed();
		}
	}

	private void checkNumberKeys() {
		if (Keyboard.getEventKey() == Keyboard.KEY_0) {
			on_0_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_1) {
			on_1_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_2) {
			on_2_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_3) {
			on_3_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_4) {
			on_4_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_5) {
			on_5_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_6) {
			on_6_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_7) {
			on_7_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_8) {
			on_8_Key();
		}
		if (Keyboard.getEventKey() == Keyboard.KEY_9) {
			on_9_Key();
		}
	}

	private void checkArrows() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			on_LEFT_Key();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
				|| Keyboard.isKeyDown(Keyboard.KEY_D)) {
			on_RIGHT_Key();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)
				|| Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			on_UP_Key();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)
				|| Keyboard.isKeyDown(Keyboard.KEY_S)) {
			on_DOWN_Key();
		}
	}

	public  void onSpaceKey(){};

	public void on_0_Key(){};
	public void on_1_Key(){};
	public void on_2_Key(){};
	public void on_3_Key(){};
	public void on_4_Key(){};
	public void on_5_Key(){};
	public void on_6_Key(){};
	public void on_7_Key(){};
	public void on_8_Key(){};
	public void on_9_Key(){};

	public void on_I_Key_pressed(){};
	public void on_O_Key_pressed(){};
	public  void on_G_Key_pressed(){};
	public  void on_E_Key_pressed(){};
	public  void on_F_Key_pressed(){};
	public  void on_P_Key_pressed(){};
	
	//arrows
	public  void on_LEFT_Key(){};
	public  void on_RIGHT_Key(){};
	public  void on_UP_Key(){};
	public  void on_DOWN_Key(){};
	
	//MOUSE
	public  void onMouseLeftPressed(){};
	public  void onMouseRightPressed(){};
	public  void on_MOUSELEFT_released(){};
	public  void on_MOUSERIGHT_released(){};
	
	public void onMouseMoved(){};
	//OTHER
	public  void onDeletePressed(){};

}
