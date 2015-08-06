package by.bkg.stopwatch.core.service.export;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IExportImportService {

    void doExport(String path, List<String[]> data);

    // TODO ABA: add import
}
