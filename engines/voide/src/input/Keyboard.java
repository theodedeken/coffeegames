package voide.input;

import java.util.Map;

/**
 * Keeps the state of the keyboard
 */
public class Keyboard {
	private Map<Key, KeyState> keys;

	private long window;

	/**
	 * Create a new Keyboard for the given window with the given key configuration
	 * 
	 * @param window the window in which the keyboard is keeping state
	 * @param keys   the key configuration
	 */
	public Keyboard(long window, Map<Key, KeyState> keys) {
		this.window = window;
		this.keys = keys;
	}

	/**
	 * Update the state of the keys
	 */
	public void update() {
		for (KeyState value : keys.values()) {
			value.update(window);
		}
	}

	/**
	 * Get the keystate of the given key
	 * 
	 * @param key the key to get the state from
	 * 
	 * @return the keystate
	 */
	public KeyState get(Key key) {
		return keys.get(key);
	}
}
