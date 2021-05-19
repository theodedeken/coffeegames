package spaxel.state;

import spaxel.Constants;
import voide.math.VectorD;

/**
 * Represents the state of the current game
 *
 * Created by theo on 24/06/17.
 */
public class GameState {

    private int score;
    private int gameTime;
    private long timeOverflow;
    private boolean debug;
    private boolean logging;
    private VectorD cursorFollow;
    private VectorD screenOffset;
    private double updateTime;

    /**
     * Create a new GameState
     */
    public GameState() {
        super();
        this.cursorFollow =
            new VectorD(Constants.HALF_GAME_WIDTH, Constants.HALF_GAME_HEIGHT);
        this.screenOffset = new VectorD(0, 0);
    }

    public int getScore() {
        return score;
    }

    /**
     * Add score to the game score
     *
     * @param value the score to add
     */
    public void addScore(int value) {
        score += value;
    }

    public int getGameTime() {
        return gameTime;
    }

    public boolean isDebug() {
        return debug;
    }

    /**
     * Toggle the debug value
     */
    public void toggleDebug() {
        debug = !debug;
    }

    public boolean isLogging() {
        return logging;
    }

    /**
     * Toggle the logging value
     */
    public void toggleLogging() {
        logging = !logging;
    }

    /**
     * Add time to the current game time
     *
     * @param updateTime the time to add
     */
    public void addTime(long updateTime) {
        timeOverflow += updateTime;
        gameTime += timeOverflow / Constants.NS_PER_SECOND;
        timeOverflow %= Constants.NS_PER_SECOND;
    }

    public VectorD getCursorFollow() {
        return cursorFollow;
    }

    public void setCursorFollow(VectorD cursorFollow) {
        this.cursorFollow = cursorFollow;
    }

    /**
     * @return the screenOffset
     */
    public VectorD getScreenOffset() {
        return screenOffset;
    }

    /**
     * @param screenOffset the screenOffset to set
     */
    public void setScreenOffset(VectorD screenOffset) {
        this.screenOffset = screenOffset;
    }

    /**
     * @return the updateTime
     */
    public double getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(double updateTime) {
        this.updateTime = updateTime;
    }
}
