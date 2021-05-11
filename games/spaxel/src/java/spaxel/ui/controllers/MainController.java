package spaxel.ui.controllers;

import spaxel.Game;
import spaxel.engine.Engine;
import spaxel.ui.elements.UIType;
import spaxel.engine.Resources;

/**
 * Callbacks for the elements of the play UI
 */
public final class MainController {
	private MainController() {

	}

	/**
	 * Start a new game
	 */
	public static void startGame() {
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.CLASS_SELECTION));
	}

	/**
	 * Open achievements
	 */
	public static void achievements() {
		// TODO
	}

	/**
	 * Open options
	 */
	public static void options() {
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.OPTIONS));
	}

	/**
	 * Open credits
	 */
	public static void credits() {
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.CREDITS));
	}

	/**
	 * Quit the game
	 */
	public static void quit() {
		Game.exit();
	}
}
