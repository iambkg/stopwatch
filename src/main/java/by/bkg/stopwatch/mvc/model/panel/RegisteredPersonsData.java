package by.bkg.stopwatch.mvc.model.panel;

import by.bkg.stopwatch.mvc.model.Category;
import by.bkg.stopwatch.mvc.model.IData;
import by.bkg.stopwatch.mvc.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Baryshnev
 */
public class RegisteredPersonsData implements IData {

    private Map<Category, List<Person>> data;

    public RegisteredPersonsData() {
        data = new HashMap<Category, List<Person>>();
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

    public Set<Category> getCategories() {
        return data.keySet();
    }
}
