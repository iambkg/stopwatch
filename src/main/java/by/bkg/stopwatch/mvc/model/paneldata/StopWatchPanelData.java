package by.bkg.stopwatch.mvc.model.paneldata;

import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.business.Split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Competition data.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchPanelData implements IPanelData {

    private Map<String, Person> persons = new HashMap<String, Person>();

    private String DATA_FORMAT = "%s: %s<br>";

    private List<Split> splits = new ArrayList<Split>();

    public void addPerson(Person person) {
        String startNumber = person.getStartNumber();
        if (persons.get(startNumber) != null) {
            // TODO ABA: should be custom exception
            throw new IllegalArgumentException("Person with start number " + startNumber + " already added");
        }
        persons.put(startNumber, person);
    }

    public void addSplit(Split split) {
        String startNumber = split.getStartNumber();
        if (persons.get(startNumber) == null) {
            // TODO ABA: should do it here?
            persons.put(startNumber, new Person("fake-" + startNumber, startNumber));
            // TODO ABA: add "fake" category
        }
        persons.get(startNumber).addSplit(split);
        splits.add(split);
    }

    public String getDataAsString() {
        String result = "<html><body>";
        for (Split split : splits) {
            result = result.concat(String.format(DATA_FORMAT, split.getStartNumber(), split.getSplitTimeAsString()));
        }
        return result.concat("</body></html>");
    }

    @Override
    public void clear() {
        persons.clear();
        splits.clear();
    }
}
