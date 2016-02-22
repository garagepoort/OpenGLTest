package be.davidcorp.applicationLayer.dto.color;

import org.newdawn.slick.Color;

public class ColorDTO {
	private Color color;
	
	public ColorDTO(int r, int g, int b){
		color = new Color(r, g, b);
	}
	
	public int getRed(){
		return color.getRed();
	}
	public int getGreen(){
		return color.getGreen();
	}
	public int getBlue(){
		return color.getBlue();
	}
}
