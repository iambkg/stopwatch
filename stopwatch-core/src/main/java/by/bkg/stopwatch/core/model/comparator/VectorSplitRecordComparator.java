package by.bkg.stopwatch.core.model.comparator;

import by.bkg.stopwatch.core.model.ISplitRecord;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class VectorSplitRecordComparator implements Comparator<Vector<ISplitRecord>> {

    @Override
    public int compare(Vector<ISplitRecord> o1, Vector<ISplitRecord> o2) {
        if (o1.size() == o2.size()) {
            // sort each Vector by splitRecords time
            SplitRecordComparator comparator = new SplitRecordComparator();
            Collections.sort(o1, comparator);
            Collections.sort(o2, comparator);
            return o1.lastElement().getTimestamp().getSplitTime().compareTo(o2.lastElement().getTimestamp().getSplitTime());
        } else {
            return new Integer(o2.size()).compareTo(o1.size());
        }
    }
}
