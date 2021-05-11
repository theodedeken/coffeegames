package spaxel.system;

import spaxel.engine.Engine;
import spaxel.system.SystemType;
import voide.sound.Music;

public class SoundSystem extends GameSystem {
	private Music currentMusic;

	public SoundSystem() {
		super(SystemType.SOUND);
	}

	public void update() {
		if (currentMusic == null || !currentMusic.isActive()) {
			nextSong();
		}
	}

	public void nextSong() {
		if (currentMusic != null) {
			currentMusic.stop();
		}
		Engine engine = Engine.get();
		if (engine.getEngineState() != Engine.EngineState.MENU) {
			currentMusic = engine.getMusicList().getRandomSong(engine.getGameState());
			play();
		} else {
			currentMusic = engine.getMusicList().getSong("Intro");
			play();
		}

	}

	public void play() {
		currentMusic.play();
	}

	@Override
	public void close() {
		currentMusic.stop();
		currentMusic.close();
	}

}
