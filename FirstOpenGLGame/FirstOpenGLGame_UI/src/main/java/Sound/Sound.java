package main.java.Sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private boolean playing;
	private AudioInputStream audioStream;
	private String path;
	private Clip clip;


	public Sound(AudioInputStream clip){
		this.audioStream = clip;
	}

	public Sound(String path) throws FileNotFoundException, IOException, UnsupportedAudioFileException {
		this.path=path;
		audioStream = createReusableAudioInputStream(new File(path));

	}

	public void loop(){
		if(!isPlaying()){
			playing=true;
		}
	}

	public void Play(){

		if(!isPlaying() || !clip.isActive()){
			AudioInputStream stream = this.audioStream;
			try {
				stream.reset();
				clip = AudioSystem.getClip();
				clip.open(stream);
				clip.start();
				playing=true;
			} catch (IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void replay(){
		if(isPlaying()){
			stop();
		}
		if(!isPlaying() || !clip.isActive()){
			AudioInputStream stream = this.audioStream;
			try {
				stream.reset();
				clip = AudioSystem.getClip();
				clip.open(stream);
				clip.start();
				playing=true;
			} catch (IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stop(){
		if(isPlaying()){
			clip.stop();
			playing=false;

		}
	}

	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	public AudioInputStream getClip() {
		return audioStream;
	}
	public void setClip(AudioInputStream clip) {
		this.audioStream = clip;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	private static AudioInputStream createReusableAudioInputStream(File file) throws IOException, UnsupportedAudioFileException {
		AudioInputStream ais = null;
		try
		{
			ais = AudioSystem.getAudioInputStream(file);
			byte[] buffer = new byte[1024 * 32];
			int read = 0;
			ByteArrayOutputStream baos = 
					new ByteArrayOutputStream(buffer.length);
			while ((read = ais.read(buffer, 0, buffer.length)) != -1)
			{
				baos.write(buffer, 0, read);
			}
			AudioInputStream reusableAis = 
					new AudioInputStream(
							new ByteArrayInputStream(baos.toByteArray()),
							ais.getFormat(),
							AudioSystem.NOT_SPECIFIED);
			return reusableAis;
		}
		finally
		{
			if (ais != null)
			{
				ais.close();
			}
		}
	}

}
