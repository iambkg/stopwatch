package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.core.service.export.IExportService;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.ExtendedSplitTableData;
import by.bkg.stopwatch.desktop.view.model.ISplitFilterForTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    private IExportService exportService;

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

    public void exportToExcel(final String path) {
//        exportService.export(path);
        exportService.exportOpenCSV(path);
    }
}
