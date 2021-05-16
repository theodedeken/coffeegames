package spaxel.engine;

import spaxel.Game;
import voide.input.Keyboard;
import voide.input.MouseWrapper;
import voide.logger.Logger;
import spaxel.sound.MusicList;
import spaxel.ui.UIType;
import spaxel.entity.EntityStream;
import voide.sound.Music;
import voide.ui.UI;

public final class Engine {
	private static final Engine engine = new Engine();

	private Keyboard keys;
	private MouseWrapper mouseWrapper;

	private EntityStream nentities;

	private UI currentUI;
	private EngineState engineState;

	private long window;

	private GameState gameState;

	private Logger logger;
	private MusicList musicList;
	private boolean shouldClose;

	private Engine() {
		gameState = new GameState();
		nentities = new EntityStream();
		engineState = EngineState.LOAD;
	}

	public static Engine get() {
		return engine;
	}

	public enum EngineState {
		MENU, PLAY, PAUSE, LOAD, EXIT
	}

	public void start() {

	}

	public void finishLoading() {
		musicList = new MusicList(voide.resources.Resources.get().getNamespaceResources("music", Music.class));
		this.keys = new Keyboard(window, Resources.get().getKeyConfiguration());

		nentities.cleanup();
		logger = new Logger(1000, 100);

		currentUI = voide.resources.Resources.get().getResource(UIType.MAIN.key(), UI.class);
		engineState = EngineState.MENU;

		Game.startUpdating();
	}

	public void stopGame() {
		nentities.scheduleClear();
		gameState = new GameState();
		// logger.reset();
		musicList.reset();
	}

	public void setWindow(long window) {
		this.window = window;
	}

	public Keyboard getKeyboard() {
		return keys;
	}

	public MouseWrapper getMouseWrapper() {
		return mouseWrapper;
	}

	public void setMouseWrapper(MouseWrapper wrapper) {
		this.mouseWrapper = wrapper;
	}

	public EntityStream getNEntityStream() {
		return nentities;
	}

	public EngineState getEngineState() {
		return engineState;
	}

	public void setEngineState(EngineState gs) {
		engineState = gs;
	}

	public GameState getGameState() {
		return gameState;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public UI getCurrentUI() {
		return currentUI;
	}

	public void setCurrentUI(UI currentUI) {
		currentUI.reset();
		this.currentUI = currentUI;
	}

	public void exit() {
		nentities.scheduleClear();
		currentUI = null;
		engineState = EngineState.EXIT;
		gameState = null;
		logger.exit();
		musicList.exit();
		shouldClose = true;
	}

	public boolean shouldClose() {
		return shouldClose;
	}

	public MusicList getMusicList() {
		return musicList;
	}

	public long getWindow() {
		return window;
	}

}
