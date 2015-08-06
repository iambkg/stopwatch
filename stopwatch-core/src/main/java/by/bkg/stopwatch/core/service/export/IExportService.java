package by.bkg.stopwatch.core.service.export;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IExportService {

    void doExport(String path, List<String[]> data);
}
