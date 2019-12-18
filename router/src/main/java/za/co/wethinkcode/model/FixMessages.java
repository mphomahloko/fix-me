package za.co.wethinkcode.model;

import za.co.wethinkcode.model.ChainOfResponsibility.ChainOfCommand;

import java.util.ArrayList;
import java.util.List;
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
    List<String> fixList = new ArrayList(){};
    int count =0;
    String fixed ="";

    public FixMessages(int id, Scanner _in, PrintWriter _out) throws IOException {
        try {
            while (true) {
                _out.println("1. Buy \n2. Sell");
                String line = _in.nextLine();
                _out.println(line + " is what the client has typed");

                while(true)
                {
                    if(line.toLowerCase().equals("buy") || line.toLowerCase().equals("sell"))
                        break;
                    System.out.println("Please pick one of the option above");
                    _out.println("Please pick one of the option above");
                    line = _in.nextLine();
                }

                if(line.equals("Buy"))
                {

                    fixList.add(String.valueOf(id));
                    while (count == 0) {
                        System.out.println("Select market ID ");
                        _out.println("Select market ID ");
                        line = _in.nextLine();
                        break;
                    }
                    fixList.add(line);
                    while(true)
                    {
                        System.out.println("Enter your price: ");
                        _out.println("Enter your price: ");
                        line = _in.nextLine();
                        try{
                            Integer.parseInt(line);
                            break;
                        }catch(java.lang.NumberFormatException VariableDeclaratorId){
                        }
                        System.out.println("Invalid price");
                    }
                    fixList.add(line);
                    fixList.add("1");

                    while(!(line.equals("pen") || line.equals("books")))
                    {
                        System.out.println("Enter the available item in stock: ");
                        _out.println("Enter the available item in stock: ");
                        line =_in.nextLine();
                    }
                    fixList.add(line);
                    while(true)
                    {
                        System.out.println("Enter item quantity desired");
                        _out.println("Enter item quantity desired");
                        line = _in.nextLine();
                        try{
                            Integer.parseInt(line);
                            break;
                        }catch(java.lang.NumberFormatException VariableDeclaratorId){
                        }
                        System.out.println("Invalid unit");
                    }
                    fixList.add(line);
                    System.out.println(fixList);
                    for(int i =0; i < fixList.size(); i++)
                    {
                        fixed += ""+fixList.get(i) + "|";
                        System.out.println(fixList.get(i));
                    }
                    //create an encoder
                }
                else if(line.equals("Sell"))
                {
                    while (count == 0) {
                        System.out.println("Select market ID ");
                        _out.println("Select market ID ");
                        line = _in.nextLine();
                        break;
                    }
                    fixList.add(line);

                    while(true)
                    {
                        System.out.println("Enter your selling price: ");
                        _out.println("Enter your selling price: ");
                        line = _in.nextLine();
                        try{
                            Integer.parseInt(line);
                            break;
                        }catch(java.lang.NumberFormatException VariableDeclaratorId){
                        }
                        System.out.println("Invalid price");
                    }
                    fixList.add(line);
                    fixList.add("2");
                    while(!(line.equals("pen") || line.equals("books")))
                    {
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
                        try{
                            Integer.parseInt(line);
                            break;
                        }catch(java.lang.NumberFormatException VariableDeclaratorId){
                        }
                        System.out.println("Invalid unit");
                    }
                    fixList.add(line);
                    System.out.println(fixList);

                    for(int i =0; i < fixList.size(); i++)
                    {
                        fixed += ""+fixList.get(i) + "|";
                        System.out.println(fixList.get(i));

                    }
                    System.out.println(fixed);
                }
                System.out.println(fixed);
                Encoder FixedMessage1 =new Encoder();
                String return_this= FixedMessage1.MessageEncoder(fixList,"D");

                ChainOfCommand chainHandler = new ChainOfCommand();
                chainHandler.HandleChain(return_this);



            }
        }
        finally {}
    }
}