/**
 * validator
 */
package bll.validators;
import java.util.regex.Pattern;

import model.Client;
import presentation.Eroare;

public class NumeValidator implements Validator<Client> {
    private static final String NAME_PATTERN = ("^[\\p{L} .'-]+$");
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(t.getNume()).matches()) {
            Eroare er=new Eroare("nume introdus gresit");
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }

}
