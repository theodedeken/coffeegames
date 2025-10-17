package spaxel;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spaxel.engine.Engine;
import spaxel.resources.Resources;
import spaxel.system.AISystem;
import spaxel.system.AgeSystem;
import spaxel.system.CooldownSystem;
import spaxel.system.DamageSystem;
import spaxel.system.DifficultySystem;
import spaxel.system.ExperienceSystem;
import spaxel.system.GameSystem;
import spaxel.system.HealthSystem;
import spaxel.system.HitSystem;
import spaxel.system.KeyboardSystem;
import spaxel.system.MouseSystem;
import spaxel.system.SoundSystem;
import spaxel.system.SpawnerSystem;
import spaxel.system.SystemType;
import spaxel.system.UISystem;
import spaxel.system.VelocitySystem;

/**
 * Runnable for the thread that executes all the game updates
 */
public class UpdateRunner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(
        UpdateRunner.class.getName()
    );
    private volatile boolean running = true;
    private Map<SystemType, GameSystem> systems;

    /**
     * Create new UpdateRunner and initialize all the update systems
     */
    public UpdateRunner() {
        super();
        systems = new EnumMap<>(SystemType.class);
        // systems
        systems.put(SystemType.SOUND, new SoundSystem());
        systems.put(SystemType.UI, new UISystem());
        systems.put(SystemType.AI, new AISystem());
        systems.put(SystemType.SPAWNER, new SpawnerSystem());
        systems.put(SystemType.AGE, new AgeSystem());
        systems.put(SystemType.VELOCITY, new VelocitySystem());
        systems.put(SystemType.DAMAGE, new DamageSystem());
        systems.put(SystemType.HEALTH, new HealthSystem());
        systems.put(SystemType.COOLDOWN, new CooldownSystem());
        systems.put(SystemType.HIT, new HitSystem());
        systems.put(SystemType.MOUSE, new MouseSystem());
        systems.put(SystemType.KEYBOARD, new KeyboardSystem());
        systems.put(SystemType.EXPERIENCE, new ExperienceSystem());
        systems.put(SystemType.DIFFICULTY, new DifficultySystem());
    }

    public void run() {
        long lastUpdateStart;
        long lastUpdateEnd;
        long accTime = Constants.NS_PER_TICK;
        while (running) {
            if (
                glfwWindowShouldClose(Engine.get().getWindow()) ||
                Engine.get().shouldClose()
            ) {
                exit();
            }
            lastUpdateStart = System.nanoTime();
            updateSystems();
            accTime -= Constants.NS_PER_TICK;
            lastUpdateEnd = System.nanoTime();
            accTime += lastUpdateEnd - lastUpdateStart;
            if (accTime < Constants.NS_PER_TICK) {
                long sleepTime =
                    (Constants.NS_PER_TICK - accTime) / Constants.NS_PER_MS;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                    Engine.get().exit();
                    Thread.currentThread().interrupt();
                }
            }
            accTime += System.nanoTime() - lastUpdateEnd;
        }
        for (GameSystem system : systems.values()) {
            system.close();
        }
        Resources.get().exit();
        Engine.get().exit();
    }

    /**
     * Stop the execution of this runnable
     */
    public void exit() {
        running = false;
    }

    /**
     * Update the necessary systems depending on the gamestate of the engine
     */
    public void updateSystems() {
        Engine.get().getMouseWrapper().update();
        Engine.get().getKeyboard().update();

        if (Engine.get().getEngineState() != Engine.EngineState.PLAY) {
            systems.get(SystemType.SOUND).update();
            systems.get(SystemType.UI).update();
        } else {
            update(SystemType.AI);
            update(SystemType.SOUND);
            update(SystemType.SPAWNER);
            update(SystemType.AGE);
            update(SystemType.VELOCITY);
            update(SystemType.SHIP);
            update(SystemType.DAMAGE);
            update(SystemType.HEALTH);
            update(SystemType.COOLDOWN);
            update(SystemType.HIT);
            update(SystemType.MOUSE);
            update(SystemType.EQUIP);
            update(SystemType.EXPERIENCE);
            update(SystemType.UI);
            update(SystemType.DIFFICULTY);
            update(SystemType.MARKER);
            Engine.get().getGameState().addTime(Constants.NS_PER_TICK);
        }
        Engine.get().getEntityStream().cleanup();
        if (Engine.get().getGameState().isLogging()) {
            Engine.get().getLogger().cleanup();
        }
    }

    /**
     * Update a single system and register to the logger if necessary
     *
     * @param type the type of the system to update
     */
    public void update(SystemType type) {
        if (Engine.get().getGameState().isLogging()) {
            Engine.get().getLogger().registerStart(type.name());
            systems.get(type).update();
            Engine.get().getLogger().registerEnd(type.name());
        } else {
            systems.get(type).update();
        }
    }
}
