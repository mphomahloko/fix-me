package za.co.wethinkcode.core;

import za.co.wethinkcode.model.Router;
import za.co.wethinkcode.model.Logger;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Router App
 *
 */
public class App {
  public Logger Logger;

  public static void main(String[] args) {
    try {
      Router router = new Router();

      Logger logger = new Logger();
      logger.createTransactionTable();;

    } catch (IOException | SQLException e) {
      System.out.println(e);
    }
    }

}
