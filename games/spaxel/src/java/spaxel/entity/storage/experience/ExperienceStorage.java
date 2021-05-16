package spaxel.entity.storage.experience;

import spaxel.Constants;
import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

/**
 * Created by theo on 26/06/17.
 */
public class ExperienceStorage extends Storage {
    private int currentXp;
    private int maxXp;
    private int level;

    public ExperienceStorage(int currentXp, int level) {
        super(SpaxelComponent.EXPERIENCE);
        this.currentXp = currentXp;
        this.level = level;
        this.maxXp = getXpToLevel();
    }

    public boolean ding() {
        return maxXp <= currentXp;
    }

    public void levelUp() {
        level++;
        currentXp = currentXp - maxXp;
        maxXp = getXpToLevel();
    }

    private int getXpToLevel() {
        return level * level * Constants.XP_FUNC_A + level * Constants.XP_FUNC_B + Constants.XP_FUNC_C;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void addXp(int xpGain) {
        currentXp += xpGain;
    }

    public void setCurrentXp(int xp) {
        this.currentXp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxXp() {
        return maxXp;
    }

    public ExperienceStorage copy() {
        return new ExperienceStorage(currentXp, level);
    }
}
