package be.davidcorp.domain.sprite;

import java.io.Serializable;

import be.davidcorp.metric.Point;
import be.davidcorp.transfo.TranslateTransfo;

public class HitBox implements Serializable{

	private static final long serialVersionUID = 876741359924492878L;
	private Point downLeftPoint = new Point(0, 0, 0);
	private Point downRightPoint = new Point(0, 0, 0);
	private Point upperRightPoint = new Point(0, 0, 0);
	private Point upperLeftPoint = new Point(0, 0, 0);
	
	public Point getDownLeftPoint() {
		return downLeftPoint;
	}
	
	public Point getCenter(){
		return new Point((getUpperLeftPoint().x + getDownRightPoint().x) / 2, (getUpperLeftPoint().y + getDownRightPoint().y) / 2, 0);
	}
	
	public void moveHorizontally(float xValue){
		TranslateTransfo trt = new TranslateTransfo(xValue, 0, 0);
		translateHitbox(trt);
	}
	
	public void moveVertically(float yValue){
		TranslateTransfo trt = new TranslateTransfo(0, yValue, 0);
		translateHitbox(trt);
	}

	private void translateHitbox(TranslateTransfo trt) {
		downLeftPoint = trt.mat.mult(downLeftPoint);
		downRightPoint = trt.mat.mult(downRightPoint);
		upperRightPoint = trt.mat.mult(upperRightPoint);
		upperLeftPoint = trt.mat.mult(upperLeftPoint);
	}
	
	public float getWidth(){
		return downRightPoint.x - downLeftPoint.x; 
	}

	public float getHeight(){
		return upperLeftPoint.y - downLeftPoint.y; 
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

	public void setUpperRightPoint(Point upperRight) {
		this.upperRightPoint = upperRight;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

}
