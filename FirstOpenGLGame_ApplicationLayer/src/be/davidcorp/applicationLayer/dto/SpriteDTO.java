package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.metric.Point;
import be.davidcorp.texture.TextureBunch;

public class SpriteDTO {

	private int id;
	
	private float x;
	private float y;
	private int width;
	private int height;
	
	
	private Point downLeftPoint;
	private Point downRightPoint;
	private Point upperRightPoint;
	private Point upperLeftPoint;
	
	private Point center;

	private TextureBunch textureBunch;
	
	private ColorDTO color;
	
	private float rotationAngle;
	
	public SpriteDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		initializeHitboxPoints();
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		initializeHitboxPoints();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		initializeHitboxPoints();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		initializeHitboxPoints();
	}

	public Point getDownLeftPoint() {
		return downLeftPoint;
	}

	public void setDownLeftPoint(Point downLeftPoint) {
		this.downLeftPoint = downLeftPoint;
	}

	public Point getDownRightPoint() {
		return downRightPoint;
	}

	public void setDownRightPoint(Point downRightPoint) {
		this.downRightPoint = downRightPoint;
	}

	public Point getUpperRightPoint() {
		return upperRightPoint;
	}

	public void setUpperRightPoint(Point upperRightPoint) {
		this.upperRightPoint = upperRightPoint;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public TextureBunch getTextureBunch() {
		return textureBunch;
	}
	
	public void setTextureBunch(TextureBunch textureBunch) {
		this.textureBunch = textureBunch;
	}
	
	public void setColor(ColorDTO color) {
		this.color = color;
	}
	
	public ColorDTO getColor() {
		return color;
	}
	
	public void setRotationAngle(float rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	
	public float getRotationAngle() {
		return rotationAngle;
	}
	
	public Point getCenter(){
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	private void initializeHitboxPoints(){
		setDownLeftPoint(new Point(getX(), getY(), 0));
		setDownRightPoint(new Point(getX()+getWidth(), getY(), 0));
		setUpperRightPoint(new Point(getX()+getWidth(), getY()+getHeight(), 0));
		setUpperLeftPoint(new Point(getX(), getY()+getHeight(), 0));
	}
}
