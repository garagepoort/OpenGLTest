package be.davidcorp.domain.system;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import be.davidcorp.domain.components.InputComponent;
import be.davidcorp.domain.components.PlayerComponent;
import be.davidcorp.domain.entity.Sprite;

public class PlayerInputSystem implements System {

	private static PlayerInputSystem instance = new PlayerInputSystem();

	private PlayerInputSystem() {
	}

	public static PlayerInputSystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		InputComponent inputComponent = sprite.<InputComponent> getComponent(InputComponent.class);
		PlayerComponent playerComponent = sprite.<PlayerComponent> getComponent(PlayerComponent.class);
		if (inputComponent != null && playerComponent != null) {
			checkKeyboard(inputComponent);
			checkMouse(inputComponent);
		}
	}

	protected boolean mousePushedDown;

	public void checkInput() {
	}

	protected void checkMouse(InputComponent component) {
		while (Mouse.next()) {
			component.leftMouseButtonDown = Mouse.isButtonDown(0);
			component.rightMouseButtonDown = Mouse.isButtonDown(1);
			// onMouseMoved();
		}
	}

	protected void checkKeyboard(InputComponent component) {
		checkArrows(component);
		// while (Keyboard.next()) {
		// if (Keyboard.getEventKeyState()) {
		//
		// } else {
		// component.addButtonClicked(Keyboard.getEventKey());
		// }
		// }

	}

	private void checkArrows(InputComponent component) {
		component.keyMoveLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		component.keyMoveRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
		component.keyMoveUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		component.keyMoveDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
	}

}
