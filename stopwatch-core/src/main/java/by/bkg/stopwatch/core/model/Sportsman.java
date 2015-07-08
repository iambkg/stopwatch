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

    public Sportsman(ISportsmanData sportsmanData) {
        this.person = new Person(sportsmanData);
        this.category = sportsmanData.getCategory();
        this.startNumber = sportsmanData.getStartNumber();
    }

    @Override
    public void refresh(ISportsmanData sportsmanData) {
        if (sportsmanData.getCategory() != ISportsmanData.EMPTY) {
            setCategory(sportsmanData.getCategory());
        }

        if (sportsmanData.getFirstName() != ISportsmanData.EMPTY) {
            getPerson().setFirstName(sportsmanData.getFirstName());
        }

        if (sportsmanData.getMiddleName() != ISportsmanData.EMPTY) {
            getPerson().setMiddleName(sportsmanData.getMiddleName());
        }

        if (sportsmanData.getLastName() != ISportsmanData.EMPTY) {
            getPerson().setLastName(sportsmanData.getLastName());
        }

        if (sportsmanData.getSex() != ISportsmanData.EMPTY) {
            getPerson().setSex(sportsmanData.getSex());
        }

        if (sportsmanData.getDateOfBirth() != ISportsmanData.EMPTY) {
            getPerson().setDateOfBirth(sportsmanData.getDateOfBirth());
        }

        if (sportsmanData.getStartNumber() != ISportsmanData.EMPTY) {
            setStartNumber(sportsmanData.getStartNumber());
        }
    }

    public IPerson getPerson() {
        return person;
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

    public String getMiddleName() {
        return getPerson().getMiddleName();
    }

    public String getLastName() {
        return getPerson().getLastName();
    }

    public Sex getSex() {
        return getPerson().getSex();
    }

    // TODO ABA: use class without time
    public Date getDateOfBirth() {
        return getPerson().getDateOfBirth();
    }

    @Override
    public String toString() {
        return "Sportsman{" +
                "person=" + person +
                ", category=" + category +
                ", startNumber='" + startNumber + '\'' +
                '}';
    }
}
