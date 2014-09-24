package by.bkg.timer;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Alexey Baryshnev
 */
public class CommonsLangStopWatch {

    public void doSomeSplits() throws InterruptedException {
        StopWatch stopwatch = new StopWatch();

        stopwatch.start();

        Thread.sleep(1400);
        splitTime(stopwatch);

        Thread.sleep(1050);
        splitTime(stopwatch);

        Thread.sleep(1001);
        splitTime(stopwatch);

        stopwatch.stop(); // optional

    }

    private void splitTime(StopWatch stopwatch) {
        stopwatch.split();
        long splitTime = stopwatch.getSplitTime();
        System.out.println(stopwatch.toSplitString() + "(" + splitTime + ")");
        stopwatch.unsplit();
    }
}
