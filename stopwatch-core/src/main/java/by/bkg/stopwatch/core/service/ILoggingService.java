package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.ISportsman;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ILoggingService {

    void debug(List<ISportsman> sportsmen);

    void debug(String message);

    void error(String message);

    void error(Throwable throwable);

    void info(String message);
}
