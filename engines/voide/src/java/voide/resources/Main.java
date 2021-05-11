package voide.resources;

import voide.sound.Sound;

public class Main {
    // TODO remove once fully integrated
    public static void main(String[] args) {
        Resources.get().load("/resource_map.yml");
        Sound test = Resources.get().getResource("music.Beast Mode", Sound.class);
    }
}