/**
 * The start class with the main method
 * */
package start;

import java.sql.SQLException;
import presentation.*;

public class Start {
    public static void main(String[] args) throws SQLException {
        MainView m=new MainView();
        Controller con=new Controller(m);
    }

}
