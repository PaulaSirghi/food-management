/**
 * ProdusDAO
 */
package dao;

import model.Produs;
import presentation.Succes;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class ProdusDAO extends AbstractDAO<Produs>{
    protected static final Logger LOGGER = Logger.getLogger(ProdusDAO.class.getName());
    public ProdusDAO() {};
    public static void view(){
        ProdusDAO p1 = new ProdusDAO();
        try {
            JTable table = new JTable();
            table = p1.createTable(p1.ViewAll());
            JFrame frame = new JFrame();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void addP(String nume,int cantitate,double pret){
        ProdusDAO p1 = new ProdusDAO();
        try {
            Produs p=new Produs(0,nume,cantitate,pret);
            p1.add(p);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void removeP(String nume) {
        ProdusDAO p1 = new ProdusDAO();
        try {
            Produs p=new Produs(0,nume,0,0);
            p1.remove(p);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void updateP(String nume,String nume2,int cantitate2,double pret) {
        ProdusDAO p1 = new ProdusDAO();
        try {
            Produs p=new Produs(0,nume,0,0);
            Produs p2=new Produs(0,nume2,cantitate2,pret);
            p1.update(p,p2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
