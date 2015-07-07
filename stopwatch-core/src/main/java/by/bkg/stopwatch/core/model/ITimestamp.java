package by.bkg.stopwatch.core.model;

import java.util.Date;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ITimestamp {

    // TODO ABA: do not use Date

    Date getValue();

    void setValue(Date value);
}
