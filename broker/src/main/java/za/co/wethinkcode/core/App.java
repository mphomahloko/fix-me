package za.co.wethinkcode.core;

import java.io.IOException;

import za.co.wethinkcode.model.Broker;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 *  Broker App!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        try {
//            Broker broker = new Broker();
//            broker.run();
//        }
//        catch (IOException | BadLocationException e) {
//            System.out.println("");
//        }
//        catch (NullPointerException e) {
//            System.out.println("NULLLLLLLLLL");
//        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final Broker broker = new Broker();
            }
        });
    }
}
