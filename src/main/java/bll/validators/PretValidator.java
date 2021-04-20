/**
 * validator
 */
package bll.validators;

import model.Produs;
import presentation.Eroare;

import java.util.regex.Pattern;

public class PretValidator implements Validator<Produs> {

    private static final String PRICE_PATTERN = ("^(?:[1-9][0-9]{2}|[1-9][0-9]?(?:\\.[0-9])?)$");
    public void validate(Produs t) {
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        if (!pattern.matcher(String.valueOf(t.getPret())).matches()) {
            Eroare er=new Eroare("pret introdus gresit");
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }
}
