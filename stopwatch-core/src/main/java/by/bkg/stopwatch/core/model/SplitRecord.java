package by.bkg.stopwatch.core.model;

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

    @Override
    public String toString() {
        return String.format("%s. %s", this.startNumber, this.timestamp.getSplitTimeAsString());
    }
}
