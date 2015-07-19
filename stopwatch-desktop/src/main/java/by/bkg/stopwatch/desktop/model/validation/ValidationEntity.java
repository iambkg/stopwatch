package by.bkg.stopwatch.desktop.model.validation;

import by.bkg.stopwatch.desktop.model.AppConstants;
import org.apache.commons.lang.StringUtils;

/**
 * @author Alexey Baryshnev
 */
public abstract class ValidationEntity<T> {

    public static final ValidationEntity<String> MANDATORY_TEXT_FIELD = new ValidationEntity<String>() {
        @Override
        public boolean isValid(final String inputText) {
            return !StringUtils.isEmpty(inputText);
        }

        @Override
        public String getErrorMessage() {
            return "validation.mandatory-field";
        }
    };

    public static final ValidationEntity<String> DIGITS_TEXT_FIELD = new ValidationEntity<String>() {
            @Override
            public boolean isValid(final String inputText) {
                return inputText.matches("\\d+");
            }

            @Override
            public String getErrorMessage() {
                return "validation.only-digits-allowed";
            }
        };

    public ValidationResult validate(final T value) {
        ValidationResult result = new ValidationResult();
        boolean valid = isValid(value);
        result.setValid(valid);
        result.setErrorMessage(!valid ? getErrorMessage() : AppConstants.EMPTY_STRING);
        return result;
    }

    public abstract boolean isValid(final T value);

    public abstract String getErrorMessage();
}
