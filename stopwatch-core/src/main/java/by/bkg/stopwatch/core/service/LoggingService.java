package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ITeam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Service
public class LoggingService implements ILoggingService {

    private final Logger log = Logger.getLogger("stopwatch");

    @Override
    public void debugSportsmen(final List<ISportsman> sportsmen) {
        for (ISportsman sportsman : sportsmen) {
            log.debug(sportsman);
        }
    }

    @Override
    public void debugTeams(List<ITeam> teams) {
        for (ITeam team : teams) {
            log.debug(team);
        }
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void error(final String message) {
        log.error(message);
    }

    @Override
    public void error(final Throwable throwable) {
        log.error("", throwable);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }
}
