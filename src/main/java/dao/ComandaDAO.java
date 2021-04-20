/**
 * ComandaDAO
 */
package dao;
import connection.ConnectionFactory;
import model.Client;
import model.Comanda;
import model.Produs;
import presentation.Eroare;
import presentation.Succes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComandaDAO extends AbstractDAO<Comanda>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static Client c;
    private static Produs p;
    public static void pr(String nume)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        Connection connection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("SELECT * FROM Produs where nume=?");
            statement.setString(1, nume);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id=resultSet.getInt("idprodus");
                int cantitate=resultSet.getInt("cantitate");
                double pret=resultSet.getDouble("pret");
                p=new Produs(id,nume,cantitate,pret);
            }
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:Order " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public static void cl(String nume)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        Connection connection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("SELECT * FROM Client where nume=?");
            statement.setString(1, nume);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                int id=resultSet.getInt("idclient");
                String email=resultSet.getString("email");
                c=new Client(id,email,nume);
            }
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:Order " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public static void fisier(double cantitate,Comanda co)
    {
        try {
            String s2=c.getNume()+"_"+p.getNume();
            s2+=".txt";
            File myObj = new File(s2);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter(s2);
                myWriter.write("Clientul: "+c.getNume()+" a cumparat "+cantitate+" "+p.getNume()+" cu un total de plata de "+co.getPret());
                myWriter.close();
            } else {
                System.out.println("File already exists.");
                FileWriter myWriter = new FileWriter(s2);
                myWriter.write("Clientul: "+c.getNume()+" a cumparat "+cantitate+" "+p.getNume()+" cu un total de plata de "+co.getPret());
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void addC(String nume1,String nume2,int cantitate) {
        ComandaDAO p1 = new ComandaDAO();
        try {
            pr(nume2);cl(nume1);
            if(p.getCantitate()<cantitate) {
                Eroare e=new Eroare("prea putine produse");
            }
            else {
                Comanda co=new Comanda(0,c.getIdclient(),p.getIdprodus(),cantitate,p.getPret()*cantitate);
                p1.add(co);
                if(p.getCantitate()-cantitate==0) {
                    ProdusDAO pr = new ProdusDAO();
                    pr.remove(p);
                }
                else {
                    ProdusDAO pr = new ProdusDAO();
                    Produs p2=new Produs(p.getIdprodus(),p.getNume(),p.getCantitate()-cantitate,p.getPret());
                    pr.update(p,p2);
                    fisier(cantitate,co);
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
