package spaxel.ui.controllers;

import spaxel.Game;
import spaxel.engine.Engine;
import spaxel.engine.Resources;
import spaxel.ui.UIType;
import voide.ui.UI;

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
		Engine.get().setCurrentUI(voide.resources.Resources.get().getResource(UIType.CLASS_SELECTION.key(), UI.class));
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
		Engine.get().setCurrentUI(voide.resources.Resources.get().getResource(UIType.OPTIONS.key(), UI.class));
	}

	/**
	 * Open credits
	 */
	public static void credits() {
		Engine.get().setCurrentUI(voide.resources.Resources.get().getResource(UIType.CREDITS.key(), UI.class));
	}

	/**
	 * Quit the game
	 */
	public static void quit() {
		System.out.println("called quits");
		Game.exit();
	}
}
