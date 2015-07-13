package by.bkg.stopwatch.core.test;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.Sportsman;
import by.bkg.stopwatch.core.model.enums.Sex;
import by.bkg.stopwatch.core.model.stub.CategoryDictionary;
import by.bkg.stopwatch.core.service.ILogicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Baryshnev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context-core-test.xml")
public class SportsmanTest {

    @Autowired
    private ILogicService service;

    private final String F_NAME = "fName";

    private final String M_NAME = "mName";

    private final String L_NAME = "lName";

    private final String START_NUMBER = "123";

    @Before
    public void doBeforeTest() {
        service.flush();
    }

    @Test
    public void addSportsmanTest() {
        List<ISportsman> sportsmen = service.addSportsman(createSportsman());

        assertEquals(1, sportsmen.size());

        ISportsman sportsmanToTest = sportsmen.get(0);
        Calendar dateOfBirthCalendar = Calendar.getInstance();
        dateOfBirthCalendar.setTime(sportsmanToTest.getDateOfBirth());

        assertEquals(F_NAME, sportsmanToTest.getFirstName());
        assertEquals(M_NAME, sportsmanToTest.getMiddleName());
        assertEquals(L_NAME, sportsmanToTest.getLastName());
        assertEquals(Sex.MALE, sportsmanToTest.getSex());
        assertEquals(START_NUMBER, sportsmanToTest.getStartNumber());
        assertEquals(1988, dateOfBirthCalendar.get(Calendar.YEAR));
        assertEquals(10, dateOfBirthCalendar.get(Calendar.MONTH));
        assertEquals(18, dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void editSportsmanTest() {
        ISportsman sportsman = createSportsman();

        List<ISportsman> sportsmen = service.addSportsman(sportsman);
        assertEquals(1, sportsmen.size());

        sportsman.setStartNumber(sportsmen.get(0).getStartNumber());
        String newFirstName = "newFirstName";
        sportsman.setFirstName(newFirstName);

        List<ISportsman> refreshedSportsmen = service.editSportsman(sportsman);

        assertEquals(1, refreshedSportsmen.size());

        ISportsman sportsmanToTest = refreshedSportsmen.get(0);
        Calendar dateOfBirthCalendar = Calendar.getInstance();
        dateOfBirthCalendar.setTime(sportsmanToTest.getDateOfBirth());

        assertEquals(newFirstName, sportsmanToTest.getFirstName());
        assertEquals(M_NAME, sportsmanToTest.getMiddleName());
        assertEquals(L_NAME, sportsmanToTest.getLastName());
        assertEquals(Sex.MALE, sportsmanToTest.getSex());
        assertEquals(START_NUMBER, sportsmanToTest.getStartNumber());
        assertEquals(1988, dateOfBirthCalendar.get(Calendar.YEAR));
        assertEquals(10, dateOfBirthCalendar.get(Calendar.MONTH));
        assertEquals(18, dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void deleteSportsmanTest() {
        assertTrue(service.getEvent().getSportsmen().isEmpty());

        ISportsman sportsmanData = createSportsman();
        service.addSportsman(sportsmanData);

        assertEquals(1, service.getEvent().getSportsmen().size());

        service.deleteSportsman(sportsmanData);
        assertTrue(service.getEvent().getSportsmen().isEmpty());
    }

    private ISportsman createSportsman() {
        Calendar dateOfBirthCalendar = Calendar.getInstance();
        dateOfBirthCalendar.set(Calendar.YEAR, 1988);
        dateOfBirthCalendar.set(Calendar.MONTH, 10);
        dateOfBirthCalendar.set(Calendar.DAY_OF_MONTH, 18);

        ICategory category = new CategoryDictionary().getAvailableCategories().firstElement();

        Date dateOfBirth = dateOfBirthCalendar.getTime();
        Sex sex = Sex.MALE;
        Sportsman sportsman = new Sportsman();
        sportsman.setFirstName(F_NAME); // TODO ABA: order this shit
        sportsman.setMiddleName(M_NAME);
        sportsman.setLastName(L_NAME);
        sportsman.setDateOfBirth(dateOfBirth);
        sportsman.setSex(sex);
        sportsman.setCategory(category);
        sportsman.setStartNumber(START_NUMBER);
        return sportsman;
    }
}
