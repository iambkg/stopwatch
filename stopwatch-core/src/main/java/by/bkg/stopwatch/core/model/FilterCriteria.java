package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.FilterType;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class FilterCriteria {

    private FilterType filterType;

    private Object value;

    public boolean matches(ISportsman sportsman) {
        if (sportsman == null) {
            return false;
        }

        switch (filterType) {
            case BY_CATEGORY:
                return sportsman.getCategory().getName().equals(value);
            case BY_SEX:
                return sportsman.getCategory().getSex().equals(value);
        }
        return false;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
