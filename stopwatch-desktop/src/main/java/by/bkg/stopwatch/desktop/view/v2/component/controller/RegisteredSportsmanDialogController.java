package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.desktop.view.v2.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class RegisteredSportsmanDialogController {

    @Autowired
    private ILogicService logicService;

    public void delete(ISportsmanData selectedSportsman, Callback<List<ISportsman>> deleteCallback) {
        List<ISportsman> refreshedList = logicService.deleteSportsman(selectedSportsman);
        deleteCallback.execute(refreshedList);
    }
}
