package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Date;

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

    public Sportsman(ISportsman sportsman) {
        this.person = new Person(sportsman);
        this.category = sportsman.getCategory();
        this.startNumber = sportsman.getStartNumber();
    }

    @Override
    public void refresh(ISportsman sportsman) {
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

//        if (sportsman.getSex() != ISportsmanData.EMPTY) {
        getPerson().setSex(sportsman.getSex());
//        }

//        if (sportsman.getDateOfBirth() != ISportsmanData.EMPTY) {
        getPerson().setDateOfBirth(sportsman.getDateOfBirth());
//        }

//        if (sportsman.getStartNumber() != ISportsmanData.EMPTY) {
        setStartNumber(sportsman.getStartNumber());
//        }
    }

    public IPerson getPerson() {
        return person;
    }

    @Override
    public void setPerson(IPerson person) {
        this.person = person;
    }

    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getFirstName() {
        return getPerson().getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        getPerson().setFirstName(firstName);
    }

    public String getMiddleName() {
        return getPerson().getMiddleName();
    }

    @Override
    public void setMiddleName(String middleName) {
        getPerson().setMiddleName(middleName);
    }

    public String getLastName() {
        return getPerson().getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        getPerson().setLastName(lastName);
    }

    public Sex getSex() {
        return getPerson().getSex();
    }

    @Override
    public void setSex(Sex sex) {
        getPerson().setSex(sex);
    }

    // TODO ABA: use class without time
    public Date getDateOfBirth() {
        return getPerson().getDateOfBirth();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        getPerson().setDateOfBirth(dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format("%s. %s %s %s - %s", startNumber, person.getLastName(), person.getFirstName(), person.getMiddleName(), category.getName());
    }
}
