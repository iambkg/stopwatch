package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.ISplitRecord;

import java.util.List;
import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ISplitFilter {

    Vector<Vector<ISplitRecord>> getDataVector(final List<ISplitRecord> refreshedSplits, List<FilterCriteria> filterCriterias);

    Vector<String> getColumnIdentifiers(final List<ISplitRecord> refreshedSplits, List<FilterCriteria> filterCriterias);
}
