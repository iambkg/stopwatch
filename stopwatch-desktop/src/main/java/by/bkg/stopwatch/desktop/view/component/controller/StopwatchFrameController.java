package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class StopwatchFrameController {

    @Autowired
    private ILogicService logicService;


    public List<ISplitRecord> onSplit(String startNumber) {
        return logicService.doSplit(startNumber);
    }
}
