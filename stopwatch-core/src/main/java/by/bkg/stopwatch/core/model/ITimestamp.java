package by.bkg.stopwatch.core.model;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ITimestamp {

    Long getSplitTime();

    void setSplitTime(Long splitTime);

    String getSplitTimeAsString();

    void setSplitTimeAsString(String splitTimeAsString);
}
