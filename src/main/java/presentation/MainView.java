/**
 * The UI for main frame
 * */
package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame{
    private ClientView c;
    private OrderView o;
    private ProductView p;
    private JButton b1=new JButton("client");
    private JButton b2=new JButton("comanda");
    private JButton b3=new JButton("produs");
    public MainView()
    {
        JPanel panou=new JPanel();
        panou.setLayout(new FlowLayout());
        panou.add(b1);
        panou.add(b2);
        panou.add(b3);
        this.setContentPane(panou);
        this.setLocationRelativeTo(null);
        this.setSize(300,100);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public ClientView getC() {
        return c;
    }

    public void setC(ClientView c) {
        this.c = c;
    }

    public OrderView getO() {
        return o;
    }

    public void setO(OrderView o) {
        this.o = o;
    }

    public ProductView getP() {
        return p;
    }

    public void setP(ProductView p) {
        this.p = p;
    }

    public void client(ActionListener mal) {
        b1.addActionListener(mal);
    }
    public void produs(ActionListener mal) { b3.addActionListener(mal); }
    public void comanda(ActionListener mal) { b2.addActionListener(mal); }
}
