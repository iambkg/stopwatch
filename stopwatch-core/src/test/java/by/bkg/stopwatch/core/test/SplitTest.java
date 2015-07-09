package by.bkg.stopwatch.core.test;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.service.ILogicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context-core-test.xml")
public class SplitTest {

    @Autowired
    private ILogicService service;

    private final String START_NUMBER = "123";

    @Before
    public void doBefore() {
        service.flush();
    }

    @Test
    public void splitTest() {
        List<ISplitRecord> initialSplits = service.getEvent().getSplits();

        assertTrue(initialSplits.isEmpty());

        service.startEvent();
        List<ISplitRecord> splits = service.doSplit(START_NUMBER);
        service.stopEvent();

        assertEquals(1, splits.size());
        assertEquals(START_NUMBER, splits.get(0).getStartNumber());
        assertNotNull(splits.get(0).getSplitTime());
    }
}
