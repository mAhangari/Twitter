package ir.maktab56.Twitter;

import java.sql.SQLException;
import java.text.ParseException;
import ir.maktab56.Twitter.util.ApplicationContext;

public class App {
    public static void main( String[] args ) throws SQLException, ParseException {
        ApplicationContext.menu.showMenu();
    }
}
