/**
 * The UI for order
 * */
package presentation;

import connection.ConnectionFactory;
import dao.AbstractDAO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class OrderView extends JFrame{
    private JTextField tfCantitate = new JTextField("");
    private static JComboBox<String> produs=new JComboBox<>();
    private static JComboBox<String> client=new JComboBox<>();
    private JButton ok=new JButton("Comanda");
    private JButton inapoi=new JButton("Inapoi");
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    public JPanel jpanel1(){
        JPanel panel1 = new JPanel();
        JLabel s = new JLabel("Operatii comenzi");
        s.setFont(new Font("", Font.BOLD, 20));
        panel1.add(s);
        panel1.setLayout(new FlowLayout());
        return panel1;
    }
    public JPanel jpanel3() {
        JPanel panel3 = new JPanel();
        JLabel nume1=new JLabel("client");
        panel3.add(nume1);
        panel3.add(client);
        panel3.setLayout(new FlowLayout());
        return panel3;
    }
    public JPanel jpanel33(){
        JPanel panel33=new JPanel();
        JLabel nume2=new JLabel("Cantitate");
        tfCantitate.setColumns(10);
        panel33.add(nume2);
        panel33.add(tfCantitate);
        panel33.setLayout(new FlowLayout());
        return panel33;
    }
    public JPanel jpanel5(){
        JPanel panel5=new JPanel();
        panel5.add(ok);
        panel5.setLayout(new FlowLayout());
        return panel5;
    }
    public JPanel jpanel4(){
        JPanel panel4=new JPanel();
        JLabel email=new JLabel("produs");
        panel4.add(email);
        panel4.add(produs);
        panel4.setLayout(new FlowLayout());
        return panel4;
    }

    public OrderView() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        cl();
        pr();
        JPanel panel1 = jpanel1();
        JPanel cont=new JPanel();
        JPanel panel3 = jpanel3();
        JPanel panel4=jpanel4();
        JPanel panel33=jpanel33();
        JPanel panel5 = jpanel5();
        JPanel panel6=new JPanel();
        cont.setLayout(new GridLayout(2,2));
        cont.add(panel3);
        cont.add(panel33);
        cont.add(panel4);
        inapoi.setForeground(Color.red);
        panel6.add(inapoi);
        panel6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel p = new JPanel();
        p.add(panel1);
        p.add(cont);
        p.add(panel5);
        p.add(panel6);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void cl()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nume FROM Client") ;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        Connection connection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(String.valueOf(sql));
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                client.addItem(resultSet.getString("nume"));
            }
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:Order " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }
    public static void pr()
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nume FROM Produs") ;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        Connection connection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(String.valueOf(sql));
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                produs.addItem(resultSet.getString("nume"));
            }
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:Order " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public void ins(ActionListener mal) {
        ok.addActionListener(mal);
    }
    public void inapoi(ActionListener mal){inapoi.addActionListener(mal);}

    public JTextField getTfCantitate() {
        return tfCantitate;
    }

    public static JComboBox<String> getProdus() {
        return produs;
    }

    public static void setProdus(JComboBox<String> produs) {
        OrderView.produs = produs;
    }

    public static JComboBox<String> getClient() {
        return client;
    }

    public static void setClient(JComboBox<String> client) {
        OrderView.client = client;
    }

}



