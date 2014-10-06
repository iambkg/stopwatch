package by.bkg.stopwatch.mvc.model.business;

/**
 * Class which keeps split data - start number of a person and time value
 *
 * @author Alexey Baryshnev
 */
public class Split {

    private String startNumber;

    private Long splitTime;

    private final String splitTimeAsString;

    public Split(String startNumber, Long splitTime, String splitTimeAsString) {
        this.startNumber = startNumber;
        this.splitTime = splitTime;
        this.splitTimeAsString = splitTimeAsString;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public Long getSplitTime() {
        return splitTime;
    }

    public String getSplitTimeAsString() {
        return splitTimeAsString;
    }
}
