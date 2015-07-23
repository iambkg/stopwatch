package by.bkg.stopwatch.core.service.export;

/**
 * @author Alexey Baryshnev
 */
public interface IExportService {

    void export(String path);

    void exportOpenCSV(String path);
}
