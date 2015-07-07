package by.bkg.stopwatch.desktop.view.controller.panel;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.desktop.view.controller.IComponentController;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.view.model.paneldata.RegisteredPersonsPanelData;
import by.bkg.stopwatch.desktop.view.component.panel.IRegisteredPersonsPanel;

import java.util.List;
import java.util.Set;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IRegisteredPersonsPanelController extends IComponentController<IRegisteredPersonsPanel> {

    void addPerson(Person person);

    void removePerson();

    RegisteredPersonsPanelData getData();

    Set<ICategory> getCategories();

    List<Person> getPersonsByCategory(ICategory category);
}
