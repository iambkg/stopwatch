package by.bkg.stopwatch.mvc.model.panel;

import by.bkg.stopwatch.common.AppConstants;
import by.bkg.stopwatch.mvc.model.IData;
import by.bkg.stopwatch.mvc.model.Person;
import by.bkg.stopwatch.mvc.model.Split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Competition data.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchData implements IData {

    private Map<Integer, Person> persons = new HashMap<Integer, Person>();

    private String DATA_FORMAT = "%d: %s<br>";

    private List<Split> splits = new ArrayList<Split>();

    {
        persons.put(AppConstants.NOT_ASSIGNED_START_NUMBER, new Person("Unassigned", AppConstants.NOT_ASSIGNED_START_NUMBER)); // TODO ABA: i18n
    }

    public void addPerson(Person person) {
        Integer startNumber = person.getStartNumber();
        if (persons.get(startNumber) != null) {
            throw new IllegalArgumentException("Person with start number " + startNumber + " already added");
        }
        persons.put(startNumber, person);
    }

    public void addSplit(Split split) {
        Integer startNumber = split.getStartNumber();
        if (AppConstants.NOT_ASSIGNED_START_NUMBER != startNumber && persons.get(startNumber) == null) {
            throw new IllegalArgumentException("Did not find person with start number " + startNumber);
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
