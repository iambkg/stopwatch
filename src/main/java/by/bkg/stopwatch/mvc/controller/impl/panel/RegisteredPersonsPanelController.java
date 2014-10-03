package by.bkg.stopwatch.mvc.controller.impl.panel;

import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.model.Category;
import by.bkg.stopwatch.mvc.model.panel.RegisteredPersonsData;
import by.bkg.stopwatch.mvc.view.impl.panel.RegisteredPersonsPanel;

import java.util.Set;

/**
 * Controller for RegisteredPersonsPanel.
 *
 * @author Alexey Baryshnev
 */
public class RegisteredPersonsPanelController extends GenericPanelController<RegisteredPersonsPanel> {

    private RegisteredPersonsData data;

    public RegisteredPersonsPanelController(EventBus eventBus, RegisteredPersonsPanel panel) {
        setPanel(panel);
        this.data = new RegisteredPersonsData();
        setEventBus(eventBus);
    }

    public RegisteredPersonsData getData() {
        return data;
    }

    public void setData(RegisteredPersonsData data) {
        this.data = data;
    }

    public Set<Category> getCategories() {
        return getData().getCategories();
    }

    public void onAddPerson() {}

    public void onRemovePerson() {}
}
