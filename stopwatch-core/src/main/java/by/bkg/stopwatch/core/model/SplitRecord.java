package by.bkg.stopwatch.core.model;

/**
 * @author Alexey Baryshnev
 */
public class SplitRecord implements ISplitRecord {

    private String startNumber;

    private ITimestamp timestamp;

    private Long splitTime;

    private String splitTimeAsString;

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

    public Long getSplitTime() {
        return splitTime;
    }

    public void setSplitTime(Long splitTime) {
        this.splitTime = splitTime;
    }

    public void setSplitTimeAsString(String splitTimeAsString) {
        this.splitTimeAsString = splitTimeAsString;
    }

    public String getSplitTimeAsString() {
        return splitTimeAsString;
    }
}
