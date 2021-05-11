package spaxel.logger;

import spaxel.system.SystemType;
import java.util.EnumMap;
import java.util.Map;
import java.util.LinkedList;

/**
 * Logger for the time measurements
 * 
 * Created by theo on 24/06/17.
 */
public class Logger {
    private EnumMap<SystemType, LinkedList<LogResult>> history;
    private EnumMap<SystemType, Long> rollingSum;
    private int cutoff;
    private int avgAmount;
    private int currentAvg;

    /**
     * Create a new Logger that keeps a certain amount of measurements and calculates rolling
     * averages.
     * 
     * @param cutoff    the amount of measurements to keep
     * @param avgAmount the amount of measurements to calculate the rolling average with
     */
    public Logger(int cutoff, int avgAmount) {
        this.cutoff = cutoff;
        this.avgAmount = avgAmount;
        history = new EnumMap<>(SystemType.class);
        rollingSum = new EnumMap<>(SystemType.class);
        for (SystemType type : SystemType.values()) {
            history.put(type, new LinkedList<>());
            rollingSum.put(type, 0L);
        }
    }

    /**
     * Clean the logger up by removing measurements that exceed the cutoff amount
     */
    public void cleanup() {
        if (cutoff > 0) {
            for (SystemType type : SystemType.values()) {
                if (history.get(type).size() > cutoff) {
                    history.get(type).removeFirst();
                }
            }
        }
        if (history.get(SystemType.SOUND).size() < avgAmount + 1) {
            currentAvg = history.get(SystemType.SOUND).size();
        }
    }

    /**
     * Register a start of a measurement
     * 
     * @param type the type of system the measurement is from
     */
    public void registerStart(SystemType type) {
        history.get(type).add(new LogResult(System.nanoTime()));
    }

    /**
     * Register an end of a measurement
     * 
     * @param type the type of system the measurement is from
     */
    public void registerEnd(SystemType type) {
        history.get(type).getLast().setEnd(System.nanoTime());
        rollingSum.put(type, rollingSum.get(type) + history.get(type).getLast().getDifference());
        if (currentAvg == avgAmount) {
            rollingSum.put(type, rollingSum.get(type) - history.get(type)
                    .get(history.get(type).size() - avgAmount - 1).getDifference());
        }
    }

    public Map<SystemType, LinkedList<LogResult>> getHistory() {
        return history;
    }

    public int getCurrentAvg() {
        return currentAvg;
    }

    public Map<SystemType, Long> getRollingSum() {
        return rollingSum;
    }

    public void exit() {
        // TODO
    }
}
