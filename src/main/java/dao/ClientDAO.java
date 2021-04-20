/**
 * ClientDAO
 */

package dao;

import model.Client;
import presentation.Succes;

import javax.swing.*;
import java.awt.*;


public class ClientDAO extends AbstractDAO<Client>{
    public ClientDAO() {};
    public static void view() {
        ClientDAO p1 = new ClientDAO();
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
    public static void addC(String nume,String email) {
        ClientDAO p1 = new ClientDAO();
        try {
            Client c=new Client(0,email,nume);
            p1.add(c);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void removeC(String nume,String email) {
        ClientDAO p1 = new ClientDAO();
        try {
            Client c=new Client(0,email,nume);
            p1.remove(c);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void updateC(String nume,String email,String nume2,String email2) {
        ClientDAO p1 = new ClientDAO();
        try {
            Client c=new Client(0,email,nume);
            Client c2=new Client(0,email2,nume2);
            p1.update(c,c2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
