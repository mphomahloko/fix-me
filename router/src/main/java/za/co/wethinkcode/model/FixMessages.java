package za.co.wethinkcode.model;

import java.util.Scanner;
import java.io.IOException;

import java.io.PrintWriter;

public class FixMessages {
    private String _decision;
    private String _instrument;
    private double _quantity;
    private String _market;
    private float _price;
    private String _message;

    public FixMessages(int id, Scanner _in, PrintWriter _out) throws IOException {
	    try {
		    while (true) {
			    _out.println("1. Buy \n2. Sell");
			    String line = _in.nextLine();
			    _out.println(line + " is what the client has typed");
		    }
	    }
	    finally {}
    }
}
