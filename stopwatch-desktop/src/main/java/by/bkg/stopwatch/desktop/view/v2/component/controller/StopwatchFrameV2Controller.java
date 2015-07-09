package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.core.service.ITimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class StopwatchFrameV2Controller {

    @Autowired
    private ITimingService timingService;

    @Autowired
    private ILogicService logicService;


    public List<ISplitRecord> onSplit(String startNumber) {
        return logicService.doSplit(startNumber);
    }
}
