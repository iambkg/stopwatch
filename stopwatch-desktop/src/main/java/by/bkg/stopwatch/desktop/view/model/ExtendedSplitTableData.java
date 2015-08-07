package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Adds metadata (e.g. start number) to display in table over Splits.
 *
 * @author Alexey Baryshnev
 */
public class ExtendedSplitTableData {

    private Vector<Vector<String>> dataVector;

    private Vector<String> columnIdentifiers;

    private Vector<Vector<ISplitRecord>> originalData;

    private final AppMessages appMessages;

    private final List<ExtraColumn> extraColumns;

    public ExtendedSplitTableData(final AppMessages appMessages) {
        this.appMessages = appMessages;
        extraColumns = new ArrayList<ExtraColumn>();
        extraColumns.add(ExtraColumn.START_NUMBER); // TODO ABA: set in runtime?
    }

    public void setDataVector(final Vector<Vector<ISplitRecord>> originalData) {
        this.originalData = originalData;
        dataVector = new Vector<Vector<String>>();

        for (Vector<ISplitRecord> row : originalData) {
            Vector<String> resultingRow = new Vector<String>();
            addExtraColumns(resultingRow, row);
            for (ISplitRecord split : row) {
                resultingRow.add(split.getTimestamp().getSplitTimeAsString());
            }
            dataVector.add(resultingRow);
        }
    }

    private void addExtraColumns(final Vector<String> resultingRow, final Vector<ISplitRecord> row) {
        ISplitRecord split = row.firstElement();
        resultingRow.add(split.getStartNumber());
    }

    public void setColumnIdentifiers(final Vector<String> originalColumnIdentifiers) {
        columnIdentifiers = new Vector<String>();

        addExtraColumnIdentifiers(columnIdentifiers);
        columnIdentifiers.addAll(originalColumnIdentifiers);
    }

    public Vector<Vector<String>> getDataVector() {
        return dataVector;
    }

    public Vector<String> getColumnIdentifiers() {
        return columnIdentifiers;
    }

    public Vector<Vector<ISplitRecord>> getOriginalData() {
        return originalData;
    }

    public String[] getInitialColumnNames() {
        List<String> colNames = new ArrayList<String>();
        addExtraColumnIdentifiers(colNames);

        colNames.add(String.format("%s %d", appMessages.getString("label.lap"), 1));

        return colNames.toArray(new String[colNames.size()]);
    }

    private void addExtraColumnIdentifiers(final List<String> colNames) {
        for (ExtraColumn extraColumn : extraColumns) {
            switch (extraColumn) {
                case START_NUMBER:
                    colNames.add(appMessages.getString("label.start-number-split-table-header"));
                    break;
                default:
                    break;
            }
        }
    }

    public enum ExtraColumn {
        START_NUMBER
    }
}
