package by.bkg.stopwatch.desktop.model.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public class ValidationChain<T> {

    private List<ValidationEntity<T>> chain;

    public List<ValidationResult> validate(final T value) {
        List<ValidationResult> result = new ArrayList<ValidationResult>();
        for (ValidationEntity<T> validationEntity : chain) {
            result.add(validationEntity.validate(value));
        }
        return result;
    }

    public ValidationChain<T> add(final ValidationEntity<T> validationEntity) {
        if (chain == null) {
            chain = new ArrayList<ValidationEntity<T>>();
        }
        chain.add(validationEntity);
        return this;
    }
}
