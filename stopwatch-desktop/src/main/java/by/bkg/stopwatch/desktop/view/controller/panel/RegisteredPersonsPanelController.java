package by.bkg.stopwatch.desktop.view.controller.panel;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.view.model.paneldata.RegisteredPersonsPanelData;
import by.bkg.stopwatch.desktop.view.component.panel.IRegisteredPersonsPanel;

import java.util.List;
import java.util.Set;

/**
 * Controller for RegisteredPersonsPanel.
 *
 * @author Alexey Baryshnev
 */
public class RegisteredPersonsPanelController extends GenericPanelController<IRegisteredPersonsPanel>
        implements IRegisteredPersonsPanelController {

    private RegisteredPersonsPanelData data;

    public RegisteredPersonsPanelController() {
        this.data = new RegisteredPersonsPanelData();
    }

    @Override
    public void addPerson(Person person) {
        getData().addPerson(person);
        getPanel().populateTree();
    }

    @Override
    public void removePerson() {
    }

    @Override
    public RegisteredPersonsPanelData getData() {
        return data;
    }

    @Override
    public Set<ICategory> getCategories() {
        return getData().getCategories();
    }

    @Override
    public List<Person> getPersonsByCategory(ICategory category) {
        return getData().getPersonsByCategory(category);
    }
}
