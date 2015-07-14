package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class EditSplitDialogController {  // TODO ABA: add "implenets"

    @Autowired
    private ILogicService logicService;

    public List<ISplitRecord> editSplit(final ISplitRecord splitToEdit) {
        return logicService.editSplit(splitToEdit);
    }
}
