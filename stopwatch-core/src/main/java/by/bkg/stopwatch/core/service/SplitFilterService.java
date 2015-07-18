package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Service
public class SplitFilterService implements ISplitFilterService {

    @Autowired
    private ILogicService logicService;

    @Override
    public Map<String, List<ISplitRecord>> orderSplits(final List<ISplitRecord> refreshedSplits, final List<FilterCriteria> filterCriterias) {
        SortedMap<String, List<ISplitRecord>> results = new TreeMap<String, List<ISplitRecord>>();
        for (ISplitRecord split : refreshedSplits) {
            if (!matchesCriterias(logicService.getSportsmanByStartNumber(split.getStartNumber()), filterCriterias)) {
                // specified split does not match selected filter criterias, do not add it into results
                continue;
            }
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

    private boolean matchesCriterias(ISportsman sportsman, List<FilterCriteria> filterCriterias) {
        boolean matches = true;
        for (FilterCriteria filterCriteria : filterCriterias) {
            matches = matches && filterCriteria.matches(sportsman);
        }
        return matches;
    }
}
