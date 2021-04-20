/**
 * Here are called the validators and the methods from bll
 */
package bll;
import dao.ComandaDAO;

public class ComandaBLL {
    public void add(String nume1,String nume2,int cantitate) {ComandaDAO.addC(nume1,nume2,cantitate); }
}
