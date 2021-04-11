package spaxel.system;


/**
 * Abstract base class of each system of the game
 */
public abstract class GameSystem {
	protected SystemType type;

	/**
	 * Create a new GameSystem
	 * 
	 * @param type the type of the system
	 */
	public GameSystem(SystemType type) {
		this.type = type;
	}

	/**
	 * Execute the system logic
	 */
	public abstract void update();

	public SystemType getType() {
		return type;
	}

	public void close() {
		// TODO should be abstract
	}
}
