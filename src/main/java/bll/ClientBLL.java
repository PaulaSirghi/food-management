/**
 * Here are called the validators and the methods from bll
 */
package bll;

import java.util.ArrayList;
import java.util.List;
import bll.validators.EmailValidator;
import bll.validators.NumeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


public class ClientBLL {

    private List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new NumeValidator());
    }

    public void view() { ClientDAO.view(); }
    public void add(String nume,String email) {
        Client c=new Client(0,email,nume);
        for (Validator<Client> v : validators) {
            v.validate(c);
        }
        ClientDAO.addC(nume,email); }
    public void deleteClient(String nume,String email) { ClientDAO.removeC(nume,email); }
    public void updateClient(String nume,String email,String nume2,String email2) {
        Client c=new Client(0,email2,nume2);
        for (Validator<Client> v : validators) {
            v.validate(c);
        }
        ClientDAO.updateC(nume,email,nume2,email2); }
}
