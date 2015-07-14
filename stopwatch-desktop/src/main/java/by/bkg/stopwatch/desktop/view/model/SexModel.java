package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.enums.Sex;

/**
 * Used on UI
 *
 * @author Alexey Baryshnev
 */
public class SexModel {

    private Sex sex;

    private String displayText;

    public SexModel(final Sex sex, final String displayText) {
        this.sex = sex;
        this.displayText = displayText;
    }

    public Sex getSex() {
        return sex;
    }

    public String getDisplayText() {
        return displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
