package spaxel.input;

import voide.input.Key;

public enum SpaxelKey implements Key {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ESC,
    DEBUG,
    LOG;

    public String id() {
        return name();
    }
}
