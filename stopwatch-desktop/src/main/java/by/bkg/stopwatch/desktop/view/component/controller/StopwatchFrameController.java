package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.desktop.model.SplitTableData;
import by.bkg.stopwatch.desktop.view.model.ISplitFilterForTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class StopwatchFrameController {        // TODO ABA: add "implenets"

    @Autowired
    private ILogicService logicService;

    @Autowired
    private ISplitFilterForTable defaultFilter;


    public List<ISplitRecord> onSplit(final String startNumber) {
        return logicService.doSplit(startNumber);
    }

    public List<ISplitRecord> deleteSplit(final ISplitRecord splitToDelete) {
        return logicService.deleteSplit(splitToDelete);
    }

    public List<ISplitRecord> startNewEvent() {
        return logicService.startNewEvent();
    }

    public SplitTableData getSplitTableData(List<ISplitRecord> splits, List<FilterCriteria> criterias) {
        SplitTableData data = new SplitTableData();
        data.setDataVector(defaultFilter.getDataVector(splits, criterias));
        data.setColumnIdentifiers(defaultFilter.getColumnIdentifiers(splits, criterias));
        return data;
    }
}
