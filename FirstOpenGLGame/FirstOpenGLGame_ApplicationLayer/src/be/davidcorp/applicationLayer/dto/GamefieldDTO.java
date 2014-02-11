package be.davidcorp.applicationLayer.dto;

public class GamefieldDTO {

	private int id;
	private String name;
	private float x;
	private float y;
	private int width;
	private int height;
	private boolean shadowsOn;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isShadowsOn() {
		return shadowsOn;
	}

	public void setShadowsOn(boolean shadowsOn) {
		this.shadowsOn = shadowsOn;
	}
	

	public String toString(){
		return name;
	}
}
