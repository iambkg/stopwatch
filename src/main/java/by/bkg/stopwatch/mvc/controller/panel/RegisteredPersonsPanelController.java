package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.controller.IEventBus;
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

    public RegisteredPersonsPanelController(IEventBus eventBus, IRegisteredPersonsPanel panel) {
        setPanel(panel);
        this.data = new RegisteredPersonsPanelData();
        setEventBus(eventBus);
    }

    public void addPerson(Person person) {
        getData().addPerson(person);
        getPanel().populateTree();
    }

    public RegisteredPersonsPanelData getData() {
        return data;
    }

    public Set<Category> getCategories() {
        return getData().getCategories();
    }

    public List<Person> getPersonsByCategory(Category category) {
        return getData().getPersonsByCategory(category);
    }
}
