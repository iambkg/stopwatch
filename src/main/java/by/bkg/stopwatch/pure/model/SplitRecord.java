package by.bkg.stopwatch.pure.model;

/**
 * @author Alexey Baryshnev
 */
public class SplitRecord implements ISplitRecord {

    private String startNumber;

    private ITimestamp timestamp;

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public ITimestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ITimestamp timestamp) {
        this.timestamp = timestamp;
    }
}
