package za.co.wethinkcode.model;

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
//                    System.out.println("buy");
                    //create market object which we can tap into and receive current Stock
                    //multiple market list here
                    //search for right market
                    //demo - deal with one
                    while (count == 0) {
                        System.out.println("Select market ID ");
                        _out.println("Select market ID ");
                        line = _in.nextLine();
                        break;

//                        match = Integer.parseInt(line);
//                        for (Integer marketID : marketList) {
//                            if (match.equals(marketID)) {
//                                count++;// add market to list and break out
//                                break;
//                            }
//                        }
//                        if (count == 0)
//                            System.out.println("Invalid market id");
                        //break out of loop with one count
                    }
                    // list object .add(line) ////holds all inputted value from user in list object
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
                    // list object .add(line) //holds all inputed value from user

                    while(!(line.equals("pen") || line.equals("books")))
                    {
                        System.out.println("Enter the available item in stock: ");
                        _out.println("Enter the available item in stock: ");
                        line =_in.nextLine();
                    }
                    fixList.add(line);
                    // list object .add(line) //holds all inputed value from user

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
                    // list object .add(line) //holds all inputed value from user
                    fixList.add(line);

                    System.out.println("\n\n\n\n");
//                    out.println(line);
                    System.out.println("\n\n\n\n");
                    System.out.println(fixList);

                    String fixed ="";
                    for(int i =0; i < fixList.size(); i++)
                    {
                        fixed += ""+fixList.get(i) + "|";
                        System.out.println(fixList.get(i));

                    }
                    System.out.println(fixed);

                    //create an encoder
                }

                if(line.equals("Sell"))
                {

                }


            }
        }
        finally {}
    }
}