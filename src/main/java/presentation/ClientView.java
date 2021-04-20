/**
 * UI for Client
 * */
package presentation;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClientView extends JFrame{
    private JTextField tfNume = new JTextField("");
    private JTextField tfEmail=new JTextField("");
    private JTextField tfNume2 = new JTextField("");
    private JTextField tfEmail2=new JTextField("");
    private JButton ok=new JButton("Inserare");
    private JButton mod=new JButton("Update");
    private JButton del=new JButton("Delete");
    private JButton lista=new JButton("Clienti");
    private JButton inapoi = new JButton("Inapoi");

    public JPanel jpanel1(){
        JPanel panel1 = new JPanel();
        JLabel s = new JLabel("Operatii client");
        s.setFont(new Font("", Font.BOLD, 20));
        panel1.add(s);
        panel1.setLayout(new FlowLayout());
        return panel1;
    }
    public JPanel jpanel3() {
        JPanel panel3 = new JPanel();
        JLabel nume1 = new JLabel("Nume");
        tfNume.setColumns(10);
        panel3.add(nume1);
        panel3.add(tfNume);
        panel3.setLayout(new FlowLayout());
        return panel3;
    }
    public JPanel jpanel4(){
        JPanel panel4=new JPanel();
        JLabel email=new JLabel("Email");
        tfEmail.setColumns(10);
        panel4.add(email);
        panel4.add(tfEmail);
        panel4.setLayout(new FlowLayout());
        return panel4;
    }
    public JPanel jpanel33(){
        JPanel panel33=new JPanel();
        JLabel nume2=new JLabel("Nume2");
        tfNume2.setColumns(10);
        panel33.add(nume2);
        panel33.add(tfNume2);
        panel33.setLayout(new FlowLayout());
        return panel33;
    }
    public JPanel jpanel44(){
        JPanel panel44=new JPanel();
        JLabel email2=new JLabel("Email2");
        tfEmail2.setColumns(10);
        panel44.add(email2);
        panel44.add(tfEmail2);
        panel44.setLayout(new FlowLayout());

        return panel44;
    }
    public JPanel jpanel5(){
        JPanel panel5=new JPanel();
        panel5.add(ok);
        panel5.add(mod);
        panel5.add(del);
        panel5.add(lista);
        panel5.setLayout(new FlowLayout());
        return panel5;
    }

    public JPanel jpanel6(){
        JPanel panel6=new JPanel();
        panel6.add(inapoi);
        panel6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        return panel6;
    }


    public ClientView() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        JPanel panel1=jpanel1();
        JPanel panel2 = new JPanel();
        JPanel cont=new JPanel();
        JPanel panel3 = jpanel3();
        JPanel panel4=jpanel4();
        JPanel panel33=jpanel33();
        JPanel panel44=jpanel44();
        JPanel panel5 = jpanel5();
        JPanel panel6=jpanel6();
        cont.setLayout(new GridLayout(2,2));
        cont.add(panel3);
        cont.add(panel33);
        cont.add(panel4);
        cont.add(panel44);
        inapoi.setForeground(Color.red);
        JPanel p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(cont);
        p.add(panel5);
        p.add(panel6);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void ins(ActionListener mal) {
        ok.addActionListener(mal);
    }
    public void upd(ActionListener mal) {
        mod.addActionListener(mal);
    }
    public void delete(ActionListener mal) {
        del.addActionListener(mal);
    }
    public void listare(ActionListener mal) {
        lista.addActionListener(mal);
    }
    public void inapoi(ActionListener mal){inapoi.addActionListener(mal);}

    public JTextField getTfNume() { return tfNume; }
    public JTextField getTfEmail() {
        return tfEmail;
    }
    public JTextField getTfNume2() { return tfNume2; }
    public JTextField getTfEmail2() { return tfEmail2; }
}



