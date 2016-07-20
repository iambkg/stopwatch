package by.bkg.stopwatch.core.model;

import java.util.List;

/**
 * @author <a href="alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class Team implements ITeam {

    private List<ISportsman> members;

    private String title;

    private ICategory category;

    private String startNumber;

    public List<ISportsman> getMembers() {
        return members;
    }

    public void setMembers(List<ISportsman> members) {
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public void refresh(final ITeam team) {
//        if (sportsman.getCategory() != ISportsmanData.EMPTY) {
        setCategory(team.getCategory());
//        }

//        if (sportsman.getFirstName() != ISportsmanData.EMPTY) {
        setTitle(team.getTitle());
//        }

//        if (sportsman.getMiddleName() != ISportsmanData.EMPTY) {
//        getPerson().setMiddleName(sportsman.getMiddleName());
//        }

//        if (sportsman.getLastName() != ISportsmanData.EMPTY) {
//        getPerson().setLastName(sportsman.getLastName());
//        }

//        if (sportsman.getDateOfBirth() != ISportsmanData.EMPTY) {
//        getPerson().setDateOfBirth(sportsman.getDateOfBirth());
//        }

//        if (sportsman.getStartNumber() != ISportsmanData.EMPTY) {
        setStartNumber(team.getStartNumber());
//        }
    }
}
