package Sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	private static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	private static Scanner s;
	
	public static void loadSounds(File f) throws IOException, UnsupportedAudioFileException{
		try {
			s = new Scanner(f);
			while(s.hasNext()){
				sounds.put(s.next(), new Sound(s.next()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void PlaySound(String s){
		sounds.get(s).Play();
	}
	
	public static void stopSound(String s){
		sounds.get(s).stop();
	}
	
	public static void loopSound(String s){
		sounds.get(s).loop();
	}
	
	public static void replaySound(String s){
		sounds.get(s).replay();
	}
	
	public static boolean isPlayingSound(String s){
		return sounds.get(s).isPlaying();
	}
	
}
