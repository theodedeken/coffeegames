package spaxel.entity;

import voide.entity.ComponentType;

public enum SpaxelComponent implements ComponentType {
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

    SpaxelComponent(String name) {
        this.name = name;
    }

    public String id() {
        return name();
    }

    public String getName() {
        return name;
    }

}
