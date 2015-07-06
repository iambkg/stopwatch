package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.mvc.model.business.Category;

/**
 * @author Alexey Baryshnev
 */
public class Sportsman implements ISportsman {

    private IPerson person;

    private Category category;

    public Sportsman(ISportsmanData sportsmanData) {
        this.person = new Person(sportsmanData);
        this.category = sportsmanData.getCategory();
    }

    @Override
    public void refresh(ISportsmanData sportsmanData) {
        if (sportsmanData.getCategory() != ISportsmanData.FAKE) {
            this.category = sportsmanData.getCategory();
        }

        if (sportsmanData.getFirstName() != ISportsmanData.FAKE) {
            getPerson().setFirstName(sportsmanData.getFirstName());
        }

        if (sportsmanData.getMiddleName() != ISportsmanData.FAKE) {
            getPerson().setMiddleName(sportsmanData.getMiddleName());
        }

        if (sportsmanData.getLastName() != ISportsmanData.FAKE) {
            getPerson().setLastName(sportsmanData.getLastName());
        }

        if (sportsmanData.getSex() != ISportsmanData.FAKE) {
            getPerson().setSex(sportsmanData.getSex());
        }

        if (sportsmanData.getDateOfBirth() != ISportsmanData.FAKE) {
            getPerson().setDateOfBirth(sportsmanData.getDateOfBirth());
        }
    }

    public IPerson getPerson() {
        return person;
    }

    public Category getCategory() {
        return category;
    }

    public Long geId() {
        return getPerson().getId();
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
}
