package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.ISportsman;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Service
public class LoggingService implements ILoggingService {

    private Logger log = Logger.getLogger("stopwatch");

    public void log(List<ISportsman> sportsmen) {
        for (ISportsman sportsman : sportsmen) {
            log.info(sportsman);
        }
    }

    public void error(String message) {
        log.error(message);
    }
}
