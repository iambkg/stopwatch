package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.service.ILogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class AddSportsmanDialogController {

    @Autowired
    private ILogicService logicService;

    public List<ISportsman> addSportsman(ISportsmanData sportsmanData) {
        List<ISportsman> sportsmen = logicService.addSportsman(sportsmanData);
        return sportsmen;
    }
}
