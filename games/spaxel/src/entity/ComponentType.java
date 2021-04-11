package spaxel.entity;

/**
 * The different types of components
 * 
 * Created by theo on 31/05/17.
 */
public enum ComponentType {
    // Storage components
    AGE("age"),

    RENDERABLE("renderable"),

    COOLDOWN("cooldown"),

    TRANSFORMATION("transformation"),

    HITSHAPE("collision"),

    CHANGE("velocity"),

    SPAWN_STORE("spawn_store"),

    MOVE("move"),

    STATUS("actor"),

    DAMAGE("damage"),

    HEALTH("health"),

    EXPERIENCE("experience"),

    ITEM("item"),

    SHOOT("shoot"),

    SHIELD("shield"),

    STACK("stack"),

    EVENT_STORE("event_store"),

    AFFECT_STORE("affect_store"),

    RENDER_STORE("render_store"),

    PROJECTILE("projectile"),

    // Behaviour components
    SPAWN("spawn"),

    KEYBOARD("keyboard"),

    MOUSE("mouse"),

    DEATH("death"),

    AFFECT("affect"),

    RENDER("render"),

    AI("ai"),

    HIT("hit"),

    EVENT("event");


    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
