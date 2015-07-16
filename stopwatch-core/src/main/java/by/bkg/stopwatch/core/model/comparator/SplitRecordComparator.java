package by.bkg.stopwatch.core.model.comparator;

import by.bkg.stopwatch.core.model.ISplitRecord;

import java.util.Comparator;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class SplitRecordComparator implements Comparator<ISplitRecord> {

    @Override
    public int compare(ISplitRecord o1, ISplitRecord o2) {
        return o1.getTimestamp().getSplitTime().compareTo(o2.getTimestamp().getSplitTime());
    }
}
