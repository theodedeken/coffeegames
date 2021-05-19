package voide.logger;

/**
 * Represent a log entry of a time measurement
 *
 * Created by theo on 24/06/17.
 */
public class LogResult {

    private long start;
    private long end;

    /**
     * Create a new LogResult starting at the given time
     *
     * @param start the time to start the logresult
     */
    public LogResult(long start) {
        this.start = start;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getDifference() {
        return end - start;
    }
}
