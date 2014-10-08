package by.bkg.stopwatch.mvc.view.i18n;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Alexey Baryshnev
 */
@Component
public class AppMessages {

    private ResourceBundle rb;

    public AppMessages() {
        this.rb = ResourceBundle.getBundle("AppMessages", Locale.getDefault());
    }

    public String getString(String key) {
        return rb.getString(key);
    }
}
