package by.bkg.stopwatch.core.model;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ISplitRecord {

    String getStartNumber();

    void setStartNumber(String startNumber);

    @Deprecated
    ITimestamp getTimestamp();

    @Deprecated
    void setTimestamp(ITimestamp timestamp);

    Long getSplitTime();

    void setSplitTime(Long splitTime);

    String getSplitTimeAsString();

    void setSplitTimeAsString(String splitTimeAsString);
}
