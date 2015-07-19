package by.bkg.stopwatch.desktop.model.validation;

/**
 * @author Alexey Baryshnev
 */
public class ValidationResult {

    private boolean valid;

    private String errorMessage;

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
