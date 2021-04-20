/**
 * validator
 */
package bll.validators;

import model.Produs;
import presentation.Eroare;

import java.util.regex.Pattern;

public class ProdusValidator implements Validator<Produs>{
    private static final String NAME_PATTERN = ("^[\\p{L} .'-]+$");
    public void validate(Produs t) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(t.getNume()).matches()) {
            Eroare er=new Eroare("nume introdus gresit");
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }
}
