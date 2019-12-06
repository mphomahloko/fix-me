package za.co.wethinkcode.model;

import java.io.PrintWriter;
import java.util.Scanner;

public class FixMessages {
    private String _decision;
    private String _instrument;
    private double _quantity;
    private String _market;
    private float _price;
    private String _message;

    public FixMessages(int id, Scanner _in, PrintWriter _out)
    {
        while (true)
        {
            String input;
            _out.println("1. Buy \n2. Sell");
            input = _in.nextLine();


        }

    }
}
