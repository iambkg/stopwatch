package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.paneldata.RegisteredPersonsPanelData;
import by.bkg.stopwatch.mvc.view.panel.IRegisteredPersonsPanel;

import java.util.Set;

/**
 * @author Alexey Baryshnev
 */
public interface IRegisteredPersonsPanelController extends IComponentController<IRegisteredPersonsPanel> {

    void addPerson(Person person);

    RegisteredPersonsPanelData getData();

    Set<Category> getCategories();

    void onAddPerson();

    void onRemovePerson();
}
