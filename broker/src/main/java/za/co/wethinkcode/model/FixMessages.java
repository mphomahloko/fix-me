package za.co.wethinkcode.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import java.io.PrintWriter;

public class FixMessages {

    List<String> fixList = new ArrayList<String>();
    int count = 0;
    String fixed ="";

    public FixMessages(int id, Scanner _in, PrintWriter _out) throws IOException {
        try {
            while (true) {
                _out.println("Do you wish to Buy Or Sell?.");
                String line = _in.nextLine();
                if (line.toLowerCase().equals("/quit"))
                    break ;
                // broker chooses between buy or sell
                while (true) {
                    if (line.toLowerCase().equals("buy") || line.toLowerCase().equals("sell"))
                        break;
                    System.out.println("Please pick one of the option above");
                    _out.println("Please pick one of the option above");
                    line = _in.nextLine();
                }

                if (line.toLowerCase().equals("buy"))
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
                        try {
                            Integer.parseInt(line);
                            break ;
                        } catch (java.lang.NumberFormatException VariableDeclaratorId) {
                        }
                        System.out.println("Invalid price");
                    }
                    fixList.add(line);
                    fixList.add("1");

                    while(!(line.equals("pen") || line.equals("books"))) {
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
                        } catch(java.lang.NumberFormatException VariableDeclaratorId) {
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
                else if (line.toLowerCase().equals("sell"))
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
                        } catch (java.lang.NumberFormatException VariableDeclaratorId){
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
                Encoder FixedMessage1 = new Encoder();
                String return_this = FixedMessage1.MessageEncoder(fixList,"D");

            }
        } finally {}
    }
}