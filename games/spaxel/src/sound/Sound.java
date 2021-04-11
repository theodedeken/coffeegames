package spaxel.sound;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());

	private String path;
	private Clip clip;

	public Sound() {

	}

	public Sound(String path) {
		this.path = path;
	}

	public void initialize() {
		try {
			System.out.println(path);
			URL url = getClass().getResource(path);
			clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
			audio.close();
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void play() {
		clip.start();
	}

	public void close() {
		clip.close();
	}

	public boolean isActive() {
		return clip.isActive();
	}

	public void stop() {
		clip.stop();
		clip.setFramePosition(0);
	}

}
