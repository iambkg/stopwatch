package by.bkg.stopwatch.core.model;

import java.util.Date;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class Timestamp implements ITimestamp {

    private Date value;

    public Timestamp(Date value) {
        this.value = value;
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public void setValue(Date value) {
        this.value = value;
    }
}
