package dao;
import connection.ConnectionFactory;
import presentation.Succes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    public AbstractDAO() {
        /** used for reflection technique*/
        this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public String [] getCols(T object) throws IllegalArgumentException, IllegalAccessException {
        /** get the atributes of an object*/
        ArrayList<String> columnNamesArrayList = new ArrayList<String>();
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNamesArrayList.add(field.getName());
        }
        String[] columnNames = new String[columnNamesArrayList.size()];
        columnNames = columnNamesArrayList.toArray(columnNames);
        return columnNames;
    }
    private String createFindQuery(String field) {
        /** Create a find query*/
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");sb.append(" * ");sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }
    private String createDeleteQuery(T object) throws IllegalArgumentException, IllegalAccessException {
        /**create delete query*/
        String[] col=getCols(object);
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");int i=0;
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(object) instanceof Long) {
                if(col[i].charAt(0)=='i' && col[i].charAt(1)=='d') {
                    i++;
                    continue;
                }
            }
            else {
                sb.append(col[i++]+"=");
                sb.append("'");
                sb.append(field.get(object));
                sb.append("'");
                break;
            }
        }
        return sb.toString();
    }
    private void createWhereClause(StringBuilder sb,T object,String[] col) throws IllegalAccessException {
        /** create the where clause for an update query*/
        int i=0;
        for(Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if(field.get(object) instanceof Long) {
                if(col[i].charAt(0)=='i' && col[i].charAt(1)=='d')
                {
                    i++;
                    continue;
                }
            }
            else {
                sb.append(col[i++]+"=");
                sb.append("'");
                sb.append(field.get(object));
                sb.append("'");
                break;
            }
        }
    }
    private void createSetClause(StringBuilder sb,T object2,String[] col) throws IllegalAccessException {
        /** create the set clause of an update query*/
        int i=0;
        for(Field field : object2.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(object2) instanceof Long) {
                if(col[i].charAt(0)=='i' && col[i].charAt(1)=='d')
                {
                    i++;
                    continue;
                }
                sb.append(col[i++]+"=");
                sb.append(field.get(object2));
                sb.append(",");
            }
            else {
                sb.append(col[i++]+"=");
                sb.append("'");
                sb.append(field.get(object2));
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length()-1);
    }
    private String createUpdateQuery(T object,T object2) throws IllegalArgumentException, IllegalAccessException {
        /**
         * create update query*/
        String[] col=getCols(object);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" set ");
        createSetClause(sb,object2,col);
        sb.append(" WHERE ");
        createWhereClause(sb,object,col);
        return sb.toString();
    }
    private String createInsertQuery(T object) throws IllegalArgumentException, IllegalAccessException {
        /**
         * create insert query*/
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(object) instanceof Integer) {
                sb.append(field.get(object));
                sb.append(",");
            }
            else {
                sb.append("'");
                sb.append(field.get(object));
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String createViewAllQuery() throws IllegalArgumentException, IllegalAccessException {
        /**create select query*/
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(";");
        return sb.toString();
    }
    private List<T> createObjects(ResultSet resultSet){
        List<T> lista = new ArrayList<T>();
        try { while(resultSet.next()) {
            T instance = type.newInstance();
            for(Field field: type.getDeclaredFields()) {
                Object value =  resultSet.getObject(field.getName());
                PropertyDescriptor pD = new  PropertyDescriptor(field.getName(), type);
                Method method = pD.getWriteMethod();
                method.invoke(instance, value);
            }
            lista.add(instance);
        }
        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | SQLException | IntrospectionException | InstantiationException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public List<T> ViewAll() throws IllegalArgumentException, IllegalAccessException {
        /** apply the select query*/
        PreparedStatement  s = null;
        ResultSet rs= null;
        Connection con = null;
        String view= createViewAllQuery();
        try {
            con = ConnectionFactory.getConnection();
            s = (PreparedStatement) con.prepareStatement(view);
            rs = s.executeQuery();
            return createObjects(rs);
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO class" + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(s);
            ConnectionFactory.close(con);
        }
        return null;
    }

    public JTable createTable(List<T> objects) throws IllegalArgumentException, IllegalAccessException {
        /**create a JTable to display the data from an sql table*/
        ArrayList<String> columnNamesA= new ArrayList<String>();
        for(Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNamesA.add(field.getName());
        }
        String[] columnNames = new String[columnNamesA.size()];
        columnNames = columnNamesA.toArray(columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        Iterator<T> i = objects.iterator();
        while(i.hasNext()) {

            ArrayList<Object> columnDataAsArrayList = new ArrayList<Object>();
            T o = i.next();
            for(Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                columnDataAsArrayList.add(field.get(o));
            }
            Object[] columnDataA= new Object[columnDataAsArrayList.size()];
            columnDataA = columnDataAsArrayList.toArray(columnDataA);
            tableModel.addRow(columnDataA);
        }
        JTable table = new JTable(tableModel);
        return table;
    }

    public void add(T object) throws IllegalArgumentException, IllegalAccessException {
        /**call the insert query to insert an object into a table*/
        Connection connection = null;
        PreparedStatement  statement = null;
        String query = createInsertQuery(object);
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:not found " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public void remove(T object) throws IllegalArgumentException, IllegalAccessException {
        /**call the delete query to remove an object */
        Connection connection = null;
        PreparedStatement  statement = null;
        String query = createDeleteQuery(object);
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:not found " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public void update(T object,T object2) throws IllegalArgumentException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement  statement = null;
        String query = createUpdateQuery(object,object2);
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.execute();
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:not found " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        String query = createFindQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
