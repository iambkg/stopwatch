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

    @Override
    public Long getSplitTime() {
        return splitTime;
    }

    @Override
    public void setSplitTime(final Long splitTime) {
        this.splitTime = splitTime;
    }

    @Override
    public void setSplitTimeAsString(final String splitTimeAsString) {
        this.splitTimeAsString = splitTimeAsString;
    }

    @Override
    public String getSplitTimeAsString() {
        return splitTimeAsString;
    }
}
