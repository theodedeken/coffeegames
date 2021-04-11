package spaxel.sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spaxel.util.SpaxelRandom;
import spaxel.engine.GameState;

/**
 * Created by theo on 12/01/18.
 */
public class MusicList {
    private List<Music> alreadyPlayed;
    private Map<String, Music> music;
    private SpaxelRandom random;
    private static final int[] PROBABILITIES = {0, 0, 0, 10, 5, 1, 3, 10, 3, 1, 5, 10};
    private static final int TIME_THRESHOLD_1 = 600;
    private static final int TIME_THRESHOLD_2 = 1200;
    private static final int OFFSET_1 = 3;
    private static final int OFFSET_2 = 6;

    public MusicList(Map<String, Music> music) {
        this.music = music;
        this.alreadyPlayed = new ArrayList<>();
        this.random = new SpaxelRandom();
    }

    public Music getRandomSong(GameState state) {
        Map<Integer, Music> probabilityMap = new HashMap<>();
        int acc = 0;
        int time = state.getGameTime();
        for (Music m : music.values()) {
            if (!alreadyPlayed.contains(m)) {
                if (time < TIME_THRESHOLD_1) {
                    acc += PROBABILITIES[m.getIntensity()];
                } else if (time < TIME_THRESHOLD_2) {
                    acc += PROBABILITIES[OFFSET_1 + m.getIntensity()];
                } else {
                    acc += PROBABILITIES[OFFSET_2 + m.getIntensity()];
                }
            } else {
                acc += 1;
            }
            if (m.getIntensity() != 0) {
                probabilityMap.put(acc, m);
            }
        }
        int r = random.nextInt(acc);
        while (probabilityMap.get(r) == null) {
            r++;
        }
        alreadyPlayed.add(probabilityMap.get(r));
        return probabilityMap.get(r);
    }

    public void reset() {
        alreadyPlayed = new ArrayList<>();
    }

    public Music getSong(String name) {
        return music.get(name);
    }

    /**
     * Exit the music list and remove all references to songs
     */
    public void exit() {
        alreadyPlayed.clear();
        music.clear();
    }

}
