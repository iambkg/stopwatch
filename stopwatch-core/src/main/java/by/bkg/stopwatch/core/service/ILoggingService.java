package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.ISportsman;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ILoggingService {

    void log(List<ISportsman> sportsmen);

    void error(String message);
}
