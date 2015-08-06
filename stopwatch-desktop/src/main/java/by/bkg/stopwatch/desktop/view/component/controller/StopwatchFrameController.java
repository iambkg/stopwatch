package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.core.service.export.CSVService;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.ExtendedSplitTableData;
import by.bkg.stopwatch.desktop.view.model.ISplitFilterForTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class StopwatchFrameController {        // TODO ABA: add "implements"

    @Autowired
    private ILogicService logicService;

    @Autowired
    private ISplitFilterForTable defaultFilter;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private CSVService csvService;

    public List<ISplitRecord> onSplit(final String startNumber) {
        return logicService.doSplit(startNumber);
    }

    public List<ISplitRecord> deleteSplit(final ISplitRecord splitToDelete) {
        return logicService.deleteSplit(splitToDelete);
    }

    public List<ISplitRecord> startNewEvent() {
        return logicService.startNewEvent();
    }

    public ExtendedSplitTableData getSplitTableData(final List<ISplitRecord> splits, final List<FilterCriteria> criterias) {
        ExtendedSplitTableData data = new ExtendedSplitTableData(appMessages);
        data.setDataVector(defaultFilter.getDataVector(splits, criterias));
        data.setColumnIdentifiers(defaultFilter.getColumnIdentifiers(splits, criterias));
        return data;
    }

    public List<ISplitRecord> getCurrentSplits() {
        return logicService.getEvent().getSplits();
    }

    public void exportToCSV(final String path, final Vector<Vector<String>> dataVector) {
        csvService.doExport(path, convertToExportData(dataVector));
    }

    private List<String[]> convertToExportData(Vector<Vector<String>> dataVector) {
        List<String[]> result = new ArrayList<String[]>();
        for (Vector<String> row : dataVector) {
            String[] rowToExport = new String[row.size() + 1];
            ISportsman sportsman = logicService.getSportsmanByStartNumber(row.firstElement());
            String fullName = "[UNKNOWN]";
            if (sportsman != null) {
                fullName = String.format("%s %s %s", sportsman.getLastName(), sportsman.getFirstName(), sportsman.getMiddleName());
            }
            rowToExport[0] = fullName;
            for (int i = 0; i < row.size(); i++) {
                rowToExport[i + 1] = row.get(i);
            }
            result.add(rowToExport);
        }
        return result;
    }
}
