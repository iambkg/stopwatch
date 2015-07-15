package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.desktop.view.model.enums.FilterType;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class FilterCriteria {

    private FilterType filterType;

    private Object value;

    public boolean matches(ISportsman sportsman) {
        switch (filterType) {
            case BY_CATEGORY:
                return false;
            case BY_SEX:
                return false;
        }
        return false;
    }
}
