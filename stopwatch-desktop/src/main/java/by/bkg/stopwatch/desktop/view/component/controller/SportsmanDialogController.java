package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.service.ILogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class SportsmanDialogController {    // TODO ABA: add "implenets"

    @Autowired
    private ILogicService logicService;

    public List<ISportsman> addSportsman(final ISportsman sportsmanToAdd) {
        List<ISportsman> sportsmen = logicService.addSportsman(sportsmanToAdd);
        return sportsmen;
    }

    public List<ISportsman> editSportsman(final ISportsman sportsmanToEdit) {
        List<ISportsman> sportsmen = logicService.editSportsman(sportsmanToEdit);
        return sportsmen;
    }
}
