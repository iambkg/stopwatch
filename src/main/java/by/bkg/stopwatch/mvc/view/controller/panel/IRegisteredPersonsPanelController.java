package by.bkg.stopwatch.mvc.view.controller.panel;

import by.bkg.stopwatch.mvc.view.controller.IComponentController;
import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.view.model.paneldata.RegisteredPersonsPanelData;
import by.bkg.stopwatch.mvc.view.component.panel.IRegisteredPersonsPanel;

import java.util.List;
import java.util.Set;

/**
 * @author Alexey Baryshnev
 */
public interface IRegisteredPersonsPanelController extends IComponentController<IRegisteredPersonsPanel> {

    void addPerson(Person person);

    void removePerson();

    RegisteredPersonsPanelData getData();

    Set<Category> getCategories();

    List<Person> getPersonsByCategory(Category category);
}
