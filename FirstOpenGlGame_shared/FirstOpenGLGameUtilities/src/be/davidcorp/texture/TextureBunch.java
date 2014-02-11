package be.davidcorp.texture;

import java.util.HashMap;

public class TextureBunch {
	private String defaultTexture;
	private HashMap<Integer, String> textures = new HashMap<Integer, String>();
	private int lastTextureTime = -1;
	private boolean loop;
	private String currentTexture;
	private int textureTime;
	
	public void updateTexture(){
		if(loop){
			textureTime++;
			if(textures.containsKey(textureTime)){
					currentTexture = textures.get(textureTime);
				
			}
			if(textureTime==lastTextureTime){
				textureTime=0;
			}
		}
	}
	
	public void stopLoop(){
		loop=false;
		currentTexture=defaultTexture;
	}
	
	public void startLoop(){
		loop=true;
	}
	
	public void resetLoop(){
		textureTime=0;
		loop=true;
	}
	
	public void setBackToDefault(){
		currentTexture=defaultTexture;
	}
	
	public String getCurrentTexture() {
		if(currentTexture == null || currentTexture.equals("")) return defaultTexture;
		return currentTexture;
	}
	
	public TextureBunch withTextureAtSecond(String texture, int seconds){
		textures.put(seconds, texture);
		return this;
	}
	
	public TextureBunch withDefaultTexture(String texture){
		defaultTexture = texture;
		currentTexture = texture;
		return this;
	}

	public TextureBunch withLastTextureTime(int lastTextureTime) {
		this.lastTextureTime = lastTextureTime;
		return this;
	}
}
