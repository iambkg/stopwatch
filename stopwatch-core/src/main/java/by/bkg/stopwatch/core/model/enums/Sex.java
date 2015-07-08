package by.bkg.stopwatch.core.model.enums;

/**
 * @author Alexey Baryshnev
 */
public enum Sex {

    MALE("sex.male"), FEMALE("sex.female");

    private String i18nKey;

    Sex(String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public String getI18nKey() {
        return i18nKey;
    }
}