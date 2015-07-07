package by.bkg.stopwatch.pure.model;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ISplitRecord {

    String getStartNumber();

    void setStartNumber(String startNumber);

    ITimestamp getTimestamp();

    void setTimestamp(ITimestamp timestamp);
}
