package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.service.ISplitFilterService;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.comparator.VectorSplitRecordComparator;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Component
public class SplitFilterForTable implements ISplitFilterForTable {

    @Autowired
    private ISplitFilterService filter;

    @Autowired
    private AppMessages appMessages;

    @Override
    public Vector<Vector<ISplitRecord>> getDataVector(final List<ISplitRecord> refreshedSplits, final List<FilterCriteria> filterCriterias) {
        Map<String, List<ISplitRecord>> orderedSplits = filter.orderSplits(refreshedSplits, filterCriterias);
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

        Collections.sort(result, new VectorSplitRecordComparator());

        return result;
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
    public Vector<String> getColumnIdentifiers(final List<ISplitRecord> refreshedSplits, final List<FilterCriteria> filterCriterias) {
        Map<String, List<ISplitRecord>> orderedSplits = filter.orderSplits(refreshedSplits, filterCriterias);
        int numberOfLaps = countLaps(orderedSplits);
        Vector<String> result = new Vector<String>();

//        result.add(appMessages.getString("label.number"));
        for (int lap = 1; lap <= numberOfLaps; lap++) {
            result.add(String.format("%s %d", appMessages.getString("label.lap"), lap));
        }
        return result;
    }
}
