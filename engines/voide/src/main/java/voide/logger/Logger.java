package voide.logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Logger for the time measurements
 *
 * Created by theo on 24/06/17.
 */
public class Logger {

    private Map<String, LinkedList<LogResult>> history;
    private Map<String, Long> rollingSum;
    private int cutoff;
    private int avgAmount;
    private int currentAvg;

    /**
     * Create a new Logger that keeps a certain amount of measurements and
     * calculates rolling averages.
     *
     * @param cutoff    the amount of measurements to keep
     * @param avgAmount the amount of measurements to calculate the rolling average
     *                  with
     */
    public Logger(int cutoff, int avgAmount) {
        this.cutoff = cutoff;
        this.avgAmount = avgAmount;
        history = new HashMap<>();
        rollingSum = new HashMap<>();
    }

    /**
     * Clean the logger up by removing measurements that exceed the cutoff amount
     */
    public void cleanup() {
        // TODO figure out what to do with this function
        // Logger should become a more general purpose class to measure timings all over
        // voide
    }

    /**
     * Register a start of a measurement
     *
     * @param type the type of system the measurement is from
     */
    public void registerStart(String key) {
        if (!history.containsKey(key)) {
            history.put(key, new LinkedList<>());
            rollingSum.put(key, 0L);
        }
        history.get(key).add(new LogResult(System.nanoTime()));
    }

    /**
     * Register an end of a measurement
     *
     * @param type the type of system the measurement is from
     */
    public void registerEnd(String key) {
        if (!history.containsKey(key)) {
            throw new RuntimeException(
                String.format("Measurement of %s did not have a start", key)
            );
        }
        history.get(key).getLast().setEnd(System.nanoTime());
        rollingSum.put(
            key,
            rollingSum.get(key) + history.get(key).getLast().getDifference()
        );
        if (currentAvg == avgAmount) {
            rollingSum.put(
                key,
                rollingSum.get(key) -
                history
                    .get(key)
                    .get(history.get(key).size() - avgAmount - 1)
                    .getDifference()
            );
        }
    }

    public Map<String, LinkedList<LogResult>> getHistory() {
        return history;
    }

    public int getCurrentAvg() {
        return currentAvg;
    }

    public Map<String, Long> getRollingSum() {
        return rollingSum;
    }

    public void exit() {
        // TODO
    }
}
