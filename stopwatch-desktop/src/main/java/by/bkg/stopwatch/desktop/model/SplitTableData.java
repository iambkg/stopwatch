package by.bkg.stopwatch.desktop.model;

import by.bkg.stopwatch.core.model.ISplitRecord;

import java.util.Vector;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public class SplitTableData {

    Vector<Vector<ISplitRecord>> dataVector;

    Vector<String> columnIdentifiers;

    public Vector<Vector<ISplitRecord>> getDataVector() {
        return dataVector;
    }

    public void setDataVector(Vector<Vector<ISplitRecord>> dataVector) {
        this.dataVector = dataVector;
    }

    public Vector<String> getColumnIdentifiers() {
        return columnIdentifiers;
    }

    public void setColumnIdentifiers(Vector<String> columnIdentifiers) {
        this.columnIdentifiers = columnIdentifiers;
    }
}
