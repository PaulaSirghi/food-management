/**
 * We have the extract methods to convert data from the gui into double, integer or String data
 * The constructor initialize ClientView, ProductView and OrderView
 * ProdusListener, ComandaListener, ClientListener call the constructors of the new classes implementing the operation for clients or producs or orders
 * InsertListener classes are used to call the methods for inserting new objects in the sql tables
 * DeleteListener classes are used to call the methods for deleting a given object
 * UpdateListener classes are used to call methods for executing an update on an object
 * ListListener classes are used to call methods for showing all the objects (Produs object or Client object) in a table
 * InapoiListener classes are used to go back to the main UI of our project
 * */
package presentation;

import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import start.ReflectionExample;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private MainView m;
    private String nume;
    private String email;
    private String numeNou;
    private String emailNou;
    private String client;
    private String produs;
    private int cantitate;
    private int cantitateNoua;
    private double pret;
    public Controller(MainView m)
    {
        this.m=m;
        m.client(new ClientListener());
        m.produs(new ProdusListener());
        m.comanda(new ComandaListener());

    }

    public void extrage()
    {
        try{
            this.nume=m.getC().getTfNume().getText();
            this.email=m.getC().getTfEmail().getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void extrageProdus()
    {
        try{
            this.nume=m.getP().getTfNume().getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void extrageNou()
    {
        try{
            this.numeNou=m.getC().getTfNume2().getText();
            this.emailNou=m.getC().getTfEmail2().getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrageNouProdus()
    {
        try{
            this.numeNou=m.getP().getTfNume2().getText();
            this.cantitateNoua= Integer.parseInt(m.getP().getTfcantitate().getText());
            this.pret=Double.parseDouble(m.getP().getTfPret().getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extrageClient()
    {
        try{
            this.client= String.valueOf(m.getO().getClient().getSelectedItem());
            this.produs= String.valueOf(m.getO().getProdus().getSelectedItem());
            this.cantitate= Integer.parseInt(m.getO().getTfCantitate().getText());
        } catch (Exception e) {
            Eroare er=new Eroare("Date introduse gresit");
        }
    }
    class ClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setC(new ClientView());
            m.getC().ins(new InsertListenerC());
            m.getC().upd(new UpdateListener());
            m.getC().delete(new DeleteListener());
            m.getC().listare(new ListListener());
            m.getC().inapoi(new InapoiListener());
            ReflectionExample.retrieveProperties(m.getC());

        }
    }
    class ProdusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setP(new ProductView());
            m.getP().ins(new InsertListenerP());
            m.getP().upd(new UpdateListener2());
            m.getP().delete(new DeleteListener2());
            m.getP().listare(new ListListener2());
            m.getP().inapoi(new InapoiListener2());
            ReflectionExample.retrieveProperties(m.getP());

        }
    }
    class ComandaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setO(new OrderView());
            m.getO().ins(new InsertListenerO());
            m.getO().inapoi(new InapoiListener3());
            ReflectionExample.retrieveProperties(m.getO());

        }
    }
    class InapoiListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.getO().setVisible(false);
        }
    }
    class InapoiListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.getP().setVisible(false);
        }
    }
    class InapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.getC().setVisible(false);
        }
    }
    class UpdateListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrageProdus();
            extrageNouProdus();
            ProdusBLL pBll = new ProdusBLL();
            pBll.update(nume,numeNou,cantitateNoua,pret);
        }
    }

    class DeleteListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrageProdus();
            ProdusBLL pBll = new ProdusBLL();
            pBll.delete(nume);
        }
    }
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrage();
            extrageNou();
            extrageNou();
            ClientBLL clientBll = new ClientBLL();
            clientBll.updateClient(nume,email,numeNou,emailNou);
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrage();
            ClientBLL clientBll = new ClientBLL();
            clientBll.deleteClient(nume,email);
        }
    }
    class InsertListenerO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrageClient();
            ComandaBLL pBll = new ComandaBLL();
            pBll.add(client,produs,cantitate);

        }
    }
    class InsertListenerP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrageNouProdus();
            ProdusBLL pBll = new ProdusBLL();
            pBll.add(numeNou,cantitateNoua,pret);

        }
    }
    class InsertListenerC implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            extrageNou();
            ClientBLL clientBll = new ClientBLL();
            clientBll.add(numeNou,emailNou);
        }
    }
    class ListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBll = new ClientBLL();
            clientBll.view();
        }
    }
    class ListListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProdusBLL produsBll = new ProdusBLL();
            produsBll.view();
        }
    }

}
