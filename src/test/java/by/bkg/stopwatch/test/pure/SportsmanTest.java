package by.bkg.stopwatch.test.pure;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.stubs.CategoryDictionary;
import by.bkg.stopwatch.pure.model.Event;
import by.bkg.stopwatch.pure.model.ISportsman;
import by.bkg.stopwatch.pure.model.Sportsman;
import by.bkg.stopwatch.pure.model.SportsmanData;
import by.bkg.stopwatch.pure.model.enums.Sex;
import by.bkg.stopwatch.pure.service.IService;
import by.bkg.stopwatch.pure.service.Service;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexey Baryshnev
 */
public class SportsmanTest {

    private final String F_NAME = "fName";

    private final String M_NAME = "mName";

    private final String L_NAME = "lName";

    @Test
    private void addSportsmanTest() {
//        TODO ABA: use Spring
        IService service = new Service();

        List<ISportsman> sportsmen = service.addSportsman(createSportsmanData());

        assertEquals(1, sportsmen.size());
        assertEquals(F_NAME, sportsmen.get(0).getFirstName());
        // TODO ABA: finish assertions
    }

    private SportsmanData createSportsmanData() {
        Calendar dateOfBirthCalendar = Calendar.getInstance();
        dateOfBirthCalendar.set(Calendar.YEAR, 1988);
        dateOfBirthCalendar.set(Calendar.MONTH, 10);
        dateOfBirthCalendar.set(Calendar.DAY_OF_MONTH, 18);

        Category category = new CategoryDictionary().getAvailableCategories().firstElement();

        Date dateOfBirth = dateOfBirthCalendar.getTime();
        Sex sex = Sex.MALE;
        return new SportsmanData(F_NAME, M_NAME, L_NAME, dateOfBirth, sex, category);
    }

    @Test
    public void editSportsmanTest() {
        SportsmanData sportsmanData = createSportsmanData();

        // TODO ABA: use Spring
        IService service = new Service();

        service.addSportsman(sportsmanData);

        String newFirstName = "newFirstName";
        sportsmanData.setFirstName(newFirstName);

        List<ISportsman> refreshedSportsmen = service.editSportsman(sportsmanData);

        assertEquals(1, refreshedSportsmen.size());
        assertEquals(newFirstName, refreshedSportsmen.get(0).getFirstName());
    }
}
