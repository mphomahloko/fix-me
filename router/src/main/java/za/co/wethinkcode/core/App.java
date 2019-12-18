package za.co.wethinkcode.core;

import za.co.wethinkcode.model.FixMessageDatabase;
import za.co.wethinkcode.model.Router;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Router App
 *
 */
public class App {
//  public FixMessageDatabase fixMessageDatabase;

  public static void main(String[] args) {
    try {
      Router router = new Router();

//      FixMessageDatabase fixMessageDatabase = new FixMessageDatabase();
//      fixMessageDatabase.createTransactionTable();

    } catch (IOException | SQLException e) {
      System.out.println(e);
    }
    }

}
