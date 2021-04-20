/**
 * Here are called the validators and the methods from bll
 */
package bll;
import bll.validators.*;
import dao.ProdusDAO;
import model.Produs;

import java.util.ArrayList;
import java.util.List;


public class ProdusBLL {
    private List<Validator<Produs>> validators;
    public ProdusBLL() {
        validators = new ArrayList<Validator<Produs>>();
        validators.add(new ProdusValidator());
        validators.add(new PretValidator());
    }
    public void view()
    {
        ProdusDAO.view();
    }
    public void add(String nume, int cantitate, double pret) {
        Produs c=new Produs(0,nume,cantitate,pret);
        int i=0;
        for (Validator<Produs> v : validators) {
            if(i==0) {
                v.validate(c);
            }
        }
        ProdusDAO.addP(nume,cantitate,pret); }
    public void delete(String nume) {ProdusDAO.removeP(nume); }
    public void update(String nume, String nume2, int cantitate2, double pret) {
        Produs c=new Produs(0,nume,0,pret);
        int i=0;
        for (Validator<Produs> v : validators) {
            if(i==0) {
                v.validate(c);
            }
        }ProdusDAO.updateP(nume,nume2,cantitate2,pret); }
}
