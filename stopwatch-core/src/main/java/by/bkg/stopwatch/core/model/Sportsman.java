package by.bkg.stopwatch.core.model;

import java.util.Calendar;

/**
 * @author Alexey Baryshnev
 */
public class Sportsman implements ISportsman {

    private IPerson person;

    private ICategory category;

    private String startNumber;

    public Sportsman() {
        this.person = new Person();
    }

    @Override
    public void refresh(final ISportsman sportsman) {
//        if (sportsman.getCategory() != ISportsmanData.EMPTY) {
        setCategory(sportsman.getCategory());
//        }

//        if (sportsman.getFirstName() != ISportsmanData.EMPTY) {
        getPerson().setFirstName(sportsman.getFirstName());
//        }

//        if (sportsman.getMiddleName() != ISportsmanData.EMPTY) {
        getPerson().setMiddleName(sportsman.getMiddleName());
//        }

//        if (sportsman.getLastName() != ISportsmanData.EMPTY) {
        getPerson().setLastName(sportsman.getLastName());
//        }

//        if (sportsman.getDateOfBirth() != ISportsmanData.EMPTY) {
        getPerson().setDateOfBirth(sportsman.getDateOfBirth());
//        }

//        if (sportsman.getStartNumber() != ISportsmanData.EMPTY) {
        setStartNumber(sportsman.getStartNumber());
//        }
    }

    @Override
    public IPerson getPerson() {
        return person;
    }

    @Override
    public void setPerson(final IPerson person) {
        this.person = person;
    }

    @Override
    public ICategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(final ICategory category) {
        this.category = category;
    }

    @Override
    public String getStartNumber() {
        return startNumber;
    }

    @Override
    public void setStartNumber(final String startNumber) {
        this.startNumber = startNumber;
    }

    @Override
    public String getFirstName() {
        return getPerson().getFirstName();
    }

    @Override
    public void setFirstName(final String firstName) {
        getPerson().setFirstName(firstName);
    }

    @Override
    public String getMiddleName() {
        return getPerson().getMiddleName();
    }

    @Override
    public void setMiddleName(final String middleName) {
        getPerson().setMiddleName(middleName);
    }

    @Override
    public String getLastName() {
        return getPerson().getLastName();
    }

    @Override
    public void setLastName(final String lastName) {
        getPerson().setLastName(lastName);
    }

    // TODO ABA: use class without time
    @Override
    public Calendar getDateOfBirth() {
        return getPerson().getDateOfBirth();
    }

    @Override
    public void setDateOfBirth(final Calendar dateOfBirth) {
        getPerson().setDateOfBirth(dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format("%s. %s %s %s - %s", startNumber, person.getLastName(), person.getFirstName(), person.getMiddleName(), category.getName());
    }
}
