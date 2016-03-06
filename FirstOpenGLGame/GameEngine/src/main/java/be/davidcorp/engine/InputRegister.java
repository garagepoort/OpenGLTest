package be.davidcorp.engine;

public class InputRegister {

	public static void registerKeyboardInput(long window, InputController inputController){
		inputController.checkKeyboard(window);
	}

	public static void registerMouseInput(long window, InputController inputController){
		inputController.checkMouse(window);
	}
}
