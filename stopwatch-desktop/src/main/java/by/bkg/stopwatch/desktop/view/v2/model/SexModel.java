package by.bkg.stopwatch.desktop.view.v2.model;

import by.bkg.stopwatch.core.model.enums.Sex;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class SexModel {

    private Sex sex;

    private String displayText;

    public SexModel(Sex sex, String displayText) {
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
