package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class DefaultSplitFilter implements ISplitFilter {

    private AppMessages appMessages;

    public DefaultSplitFilter(final AppMessages appMessages) {
        this.appMessages = appMessages;
    }

    @Override
    public Vector<Vector<ISplitRecord>> getDataVector(final List<ISplitRecord> refreshedSplits) {
        Map<String, List<ISplitRecord>> orderedSplits = orderSplits(refreshedSplits);
        Vector<Vector<ISplitRecord>> result = new Vector<Vector<ISplitRecord>>();

        for (String startNumber : orderedSplits.keySet()) {
            // fill row
            Vector<ISplitRecord> row = new Vector<ISplitRecord>();

            List<ISplitRecord> sportsmanSplits = orderedSplits.get(startNumber);
            for (ISplitRecord sportsmanSplit : sportsmanSplits) {
                row.add(sportsmanSplit);
            }
            result.add(row);
        }

        return sort(result);
    }

    private Vector<Vector<ISplitRecord>> sort(final Vector<Vector<ISplitRecord>> toSort) {
        Collections.sort(toSort, new Comparator<Vector<ISplitRecord>>() {
            @Override
            public int compare(Vector<ISplitRecord> o1, Vector<ISplitRecord> o2) {
                if (o1.size() == o2.size()) {
                    // sort by time split time
                    sortByTimeSplitTime(o1);
                    sortByTimeSplitTime(o2);
                    return o1.lastElement().getTimestamp().getSplitTime().compareTo(o2.lastElement().getTimestamp().getSplitTime());
                } else {
                    return new Integer(o2.size()).compareTo(o1.size());
                }
            }

            private void sortByTimeSplitTime(Vector<ISplitRecord> o1) {
                Collections.sort(o1, new Comparator<ISplitRecord>() {
                    @Override
                    public int compare(ISplitRecord o1, ISplitRecord o2) {
                        return o1.getTimestamp().getSplitTime().compareTo(o2.getTimestamp().getSplitTime());
                    }
                });
            }
        });
        return toSort;
    }

    private Map<String, List<ISplitRecord>> orderSplits(final List<ISplitRecord> refreshedSplits) {
        SortedMap<String, List<ISplitRecord>> results = new TreeMap<String, List<ISplitRecord>>();
        for (ISplitRecord split : refreshedSplits) {
            String startNumber = split.getStartNumber();
            List<ISplitRecord> splits = results.get(startNumber);
            if (splits == null) {
                splits = new ArrayList<ISplitRecord>();
                results.put(startNumber, splits);
            }
            splits.add(split);
        }
        return results;
    }

    private int countLaps(final Map<String, List<ISplitRecord>> orderedSplits) {
        int laps = 1;
        for (List<ISplitRecord> splitsForSportsman : orderedSplits.values()) {
            int sportsmanLaps = splitsForSportsman.size();
            if (laps < sportsmanLaps) {
                laps = sportsmanLaps;
            }
        }
        return laps;
    }

    @Override
    public Vector<String> getColumnIdentifiers(final List<ISplitRecord> refreshedSplits) {
        Map<String, List<ISplitRecord>> orderedSplits = orderSplits(refreshedSplits);
        int numberOfLaps = countLaps(orderedSplits);
        Vector<String> result = new Vector<String>();

//        result.add(appMessages.getString("label.number"));
        for (int lap = 1; lap <= numberOfLaps; lap++) {
            result.add(String.format("%s %d", appMessages.getString("label.lap"), lap));
        }
        return result;
    }
}
