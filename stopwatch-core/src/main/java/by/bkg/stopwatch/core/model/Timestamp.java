package by.bkg.stopwatch.core.model;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class Timestamp implements ITimestamp {

    private Long splitTime;

    private String splitTimeAsString;

    public Timestamp(Long splitTime, String splitTimeAsString) {
        this.splitTime = splitTime;
        this.splitTimeAsString = splitTimeAsString;
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
