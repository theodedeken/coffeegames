package spaxel.system;

/**
 * The different types of systems
 */
public enum SystemType {
    UI("ui"),

    AI("ai"),

    RENDER("render"),

    SOUND("sound"),

    SPAWNER("spawner"),

    AGE("age"),

    VELOCITY("velocity"),

    DAMAGE("damage"),

    HEALTH("health"),

    COOLDOWN("cooldown"),

    HIT("hit"),

    MOUSE("mouse"),

    KEYBOARD("keyboard"),

    EQUIP("equip"),

    EXPERIENCE("experience"),

    SHIP("ship"),

    DIFFICULTY("difficulty"),

    MARKER("marker");

    private final String name;

    SystemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
