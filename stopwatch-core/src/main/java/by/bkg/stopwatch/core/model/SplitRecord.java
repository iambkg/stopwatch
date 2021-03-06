package by.bkg.stopwatch.core.model;

/**
 * @author Alexey Baryshnev
 */
public class SplitRecord implements ISplitRecord {

    private String startNumber;

    private ITimestamp timestamp;

    public SplitRecord(final String startNumber, final Long splitTime, final String splitTimeAsString) {
        this.startNumber = startNumber;
        this.timestamp = new Timestamp(splitTime, splitTimeAsString);
    }

    @Override
    public void refresh(final ISplitRecord newData) {
        this.startNumber = newData.getStartNumber();
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(final String startNumber) {
        this.startNumber = startNumber;
    }

    public ITimestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final ITimestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s. %s", this.startNumber, this.timestamp.getSplitTimeAsString());
    }
}
