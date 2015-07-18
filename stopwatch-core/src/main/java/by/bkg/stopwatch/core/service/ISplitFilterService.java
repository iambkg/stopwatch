package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ISplitRecord;

import java.util.List;
import java.util.Map;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ISplitFilterService {

    Map<String, List<ISplitRecord>> orderSplits(final List<ISplitRecord> refreshedSplits, final List<FilterCriteria> filterCriterias);
}
