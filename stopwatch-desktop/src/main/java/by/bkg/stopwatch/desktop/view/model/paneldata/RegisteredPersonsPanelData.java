package by.bkg.stopwatch.desktop.view.model.paneldata;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.desktop.model.business.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public class RegisteredPersonsPanelData implements IPanelData {

    private Map<ICategory, List<Person>> data;

    public RegisteredPersonsPanelData() {
        data = new HashMap<ICategory, List<Person>>();
    }

    public void addPerson(Person person) {
        if (data.get(person.getCategory()) == null) {
            data.put(person.getCategory(), new ArrayList<Person>());
        }
        data.get(person.getCategory()).add(person);
    }

    @Override
    public void clear() {
        data.clear();
    }

    public Set<ICategory> getCategories() {
        return data.keySet();
    }

    public List<Person> getPersonsByCategory(ICategory category) {
        return data.get(category);
    }
}
