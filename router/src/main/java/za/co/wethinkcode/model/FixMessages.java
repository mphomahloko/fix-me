package za.co.wethinkcode.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import java.io.PrintWriter;

public class FixMessages {
    int id;
    Scanner _in;
    PrintWriter _out;
    
    List<String> fixList;
    String fixed = "";

    public FixMessages(int id, Scanner _in, PrintWriter _out) {
        this.id = id;
        this._in = _in;
        this._out = _out;
    }

    public String buyOrSell() throws IOException {
        fixList = new ArrayList<String>();
        try {
            while (true) {
                _out.println("Do you wish to Buy Or Sell?.");
                String line = _in.nextLine();

                // broker chooses between buy or sell
                while (true) {
                    _out.println(line);
                    if (line.toLowerCase().equals("buy") || line.toLowerCase().equals("sell"))
                        break ;
                    _out.println("Please pick one of the option above");
                    line = _in.nextLine();
                }

                if (line.toLowerCase().equals("buy")) {

                    fixList.add(String.valueOf(id));
                    while (true) {
                        _out.println("Select market ID ");
                        line = _in.nextLine();
                        _out.println(line);
                        break;
                    }
                    fixList.add(line);
                    while(true) {
                        _out.println("Amount you are willing to spend: ");
                        line = _in.nextLine();
                        _out.println(line);
                        try {
                            Integer.parseInt(line);
                            break ;
                        } catch (java.lang.NumberFormatException VariableDeclaratorId) {
                            _out.println("Invalid price format.");
                        }
                    }
                    fixList.add(line);
                    // status to buy
                    fixList.add("1");

                    _out.println("Item you would like to purchace.");
                    line =_in.nextLine();
                    _out.println(line);
                    fixList.add(line);
                    while(true) {
                        _out.println("Item quantity ");
                        line = _in.nextLine();
                        _out.println(line);
                        try {
                            Integer.parseInt(line);
                            break;
                        } catch(java.lang.NumberFormatException VariableDeclaratorId) {
                            _out.println("Invalid unit");
                        }
                    }
                    fixList.add(line);
                    for(int i =0; i < fixList.size(); i++) {
                        fixed += ""+fixList.get(i) + "|";
                    }
                }
                else if (line.toLowerCase().equals("sell")) {
                    while (true) {
                        _out.println("Select the market ID ");
                        line = _in.nextLine();
                        break;
                    }
                    fixList.add(line);

                    while(true) {
                        _out.println("Enter your selling price: ");
                        line = _in.nextLine();
                        try {
                            Integer.parseInt(line);
                            break;
                        } catch(java.lang.NumberFormatException VariableDeclaratorId){
                            _out.println("Invalid price");
                        }
                    }
                    fixList.add(line);
                    fixList.add("2");
                    while(!(line.equals("pen") || line.equals("books"))) {
                        System.out.println("Enter the available item you wish to sell");
                        _out.println("Enter the available item you wish to sell ");
                        line =_in.nextLine();
                    }
                    fixList.add(line);
                    while(true)
                    {
                        System.out.println("Enter item quantity desired to sell");
                        _out.println("Enter item quantity desired to sell");
                        line = _in.nextLine();
                        try {
                            Integer.parseInt(line);
                            break;
                        } catch(java.lang.NumberFormatException VariableDeclaratorId){
                        }
                        System.out.println("Invalid unit");
                    }
                    fixList.add(line);
                }
                Encoder FixedMessage1 = new Encoder();
                return FixedMessage1.MessageEncoder(fixList,"D");
                // send msg to the router
                // ChainOfCommand chainHandler = new ChainOfCommand();
                // chainHandler.HandleChain(return_this);
            }
        } finally {}
    }
}