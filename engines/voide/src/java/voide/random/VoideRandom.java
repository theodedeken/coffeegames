package voide.random;

import java.util.Random;
import java.util.List;

/**
 * Extension of the {@link Random} class to provide some extra methods used in
 * the Game.
 */
public class VoideRandom extends Random {
    private static final long serialVersionUID = 1;

    /**
     * Constructs a new SpaxelRandom object.
     */
    public VoideRandom() {
        super();
    }

    /**
     * Choose an element from the given choices.
     * 
     * @param choices the list containing the possible choices.
     * 
     * @return the chosen element
     */
    public <T> T choose(List<T> choices) {
        return choices.get(nextInt(choices.size()));
    }

    /**
     * Choose an element from the given choices.
     * 
     * @param choices the possible choices.
     * 
     * @return the chosen element
     */
    public <T> T choose(T... choices) {
        return choices[nextInt(choices.length)];
    }

    /**
     * Return an integer value between the given bounds.
     * 
     * @param min The minimum bound inclusive
     * @param max The maximum bound exclusive
     * 
     * @return int between the bounds
     */
    public int between(int min, int max) {
        return nextInt(max - min) + min;
    }

    /**
     * Return a double value between the given bounds
     * 
     * @param min The minimum bound inclusive
     * @param max The maximum bound exclusive
     * 
     * @return double between the bounds
     */
    public double between(double min, double max) {
        return min + nextDouble() * (max - min);
    }

    /**
     * Return true with the given chance
     * 
     * @param chance The chance that true will be returned
     * 
     * @return pass
     */
    public boolean pass(double chance) {
        return nextDouble() < chance;
    }

    /**
     * Return a random double
     * 
     * @param multiplier the multiplier
     * 
     * @return a random double value multiplied with the multiplier
     */
    public double nextDouble(double multiplier) {
        return nextDouble() * multiplier;
    }
}
