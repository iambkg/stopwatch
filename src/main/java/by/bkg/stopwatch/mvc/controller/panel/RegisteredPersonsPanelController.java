package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.paneldata.RegisteredPersonsPanelData;
import by.bkg.stopwatch.mvc.view.panel.IRegisteredPersonsPanel;

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
    public RegisteredPersonsPanelData getData() {
        return data;
    }

    @Override
    public Set<Category> getCategories() {
        return getData().getCategories();
    }

    @Override
    public List<Person> getPersonsByCategory(Category category) {
        return getData().getPersonsByCategory(category);
    }
}
