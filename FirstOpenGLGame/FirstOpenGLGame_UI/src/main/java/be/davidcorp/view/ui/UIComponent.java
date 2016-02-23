package be.davidcorp.view.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import be.davidcorp.inputControl.MouseButton;
import be.davidcorp.metric.Point;
import be.davidcorp.texture.TextureBunch;
import be.davidcorp.view.ui.button.ClickEvent;
import be.davidcorp.view.ui.button.ClickListener;
import be.davidcorp.view.ui.panel.UIComponentCollisionChecker;

public abstract class UIComponent {

	private int width;
	private int height;
	private float x;
	private float y;
	private boolean visible = true;
	private boolean closed;
	private TextureBunch textureBunch;
	private boolean clickable = true;
	private List<ClickListener> _listeners = new ArrayList<ClickListener>();
	private List<UIComponent> components = new ArrayList<UIComponent>();
	private List<UIComponent> componentsToBeAdded = new ArrayList<UIComponent>();
	private List<UIComponent> componentsToBeRemoved= new ArrayList<UIComponent>();

	public UIComponent(float x, float y, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public void addComponent(UIComponent component, float x, float y) {
		component.setX(getX() + x);
		component.setY(getY() + y);
		componentsToBeAdded.add(component);
	}
	
	public void removeComponent(UIComponent component) {
		componentsToBeRemoved.add(component);
	}

	public boolean checkMouseClick(int x, int y, MouseButton buttonClicked) {
		if (isVisible() && !isClosed() && clickable && UIComponentCollisionChecker.isPointInsidePanel(this, new Point(x, y, 0))) {
			for (UIComponent component : components) {
				component.checkMouseClick(x, y, buttonClicked);
				if (buttonClicked == MouseButton.LEFTBUTTON){
					component.fireOnLeftClickEvent(x, y);
				}else if(buttonClicked == MouseButton.RIGHTBUTTON){
					component.fireOnRightClickEvent(x, y);
				}
			}
			return true;
		}
		return false;
	}

	public String getTexture() {
		if (textureBunch == null) {
			return null;
		}
		return textureBunch.getCurrentTexture();
	}

	public void setTexture(String texture) {
		if (textureBunch == null) {
			textureBunch = new TextureBunch();
		}
		textureBunch.withDefaultTexture(texture);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		float diff = getX() - x;
		this.x = x;
		for (UIComponent component : components) {
			component.setX(component.getX() - diff);
		}
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		float diff = getY() - y;
		this.y = y;
		for (UIComponent component : components) {
			component.setY(component.getY() - diff);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		for (UIComponent component : components) {
			component.setVisible(visible);
		}
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean close) {
		this.closed = close;
	}

	protected void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public synchronized void addEventListener(ClickListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(ClickListener listener) {
		_listeners.remove(listener);
	}

	public synchronized void fireOnLeftClickEvent(int x, int y) {
		if (UIComponentCollisionChecker.isPointInsidePanel(this, new Point(x, y, 0))) {
			ClickEvent event = new ClickEvent(this, x, y);
			Iterator<ClickListener> i = _listeners.iterator();
			while (i.hasNext()) {
				((ClickListener) i.next()).onLeftClick(event);
			}
		}
	}

	public synchronized void fireOnRightClickEvent(int x, int y) {
		if (UIComponentCollisionChecker.isPointInsidePanel(this, new Point(x, y, 0))) {
			ClickEvent event = new ClickEvent(this, x, y);
			Iterator<ClickListener> i = _listeners.iterator();
			while (i.hasNext()) {
				((ClickListener) i.next()).onRightClick(event);
			}
		}
	}

	public void draw() {
		updateChildComponents();
		if (this.isVisible() && !isClosed()) {
			drawThis();
			for (UIComponent component : components) {
				component.draw();
			}
		}
	}

	private void updateChildComponents() {
		components.addAll(componentsToBeAdded);
		componentsToBeAdded.clear();
		components.removeAll(componentsToBeRemoved);
		componentsToBeRemoved.clear();
	}

	public List<UIComponent> getComponents() {
		return components;
	}


	public abstract void drawThis();
}
