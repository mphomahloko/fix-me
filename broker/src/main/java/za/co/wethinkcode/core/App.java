package za.co.wethinkcode.core;

import java.io.IOException;

import za.co.wethinkcode.model.Broker;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Broker broker = new Broker();
        }catch(IOException e) {}
    }
}
