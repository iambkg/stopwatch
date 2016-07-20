package by.bkg.stopwatch.core.model;

import java.util.List;

/**
 * @author <a href="alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ITeam {

    List<ISportsman> getMembers();

    void setMembers(List<ISportsman> members);

    String getTitle();

    void setTitle(String title);

    ICategory getCategory();

    void setCategory(ICategory category);

    String getStartNumber();

    void setStartNumber(String startNumber);

    /**
     * Overwrites data for current team.
     * If some field in <code>teamData</code> is null - null is set.
     * If field is ITeamData.EMPTY - nothing happens
     *
     * @param teamData sportsman data
     */
    void refresh(ITeam teamData);
}
