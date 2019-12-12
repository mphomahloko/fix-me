package za.co.wethinkcode.core;

import java.io.IOException;

import za.co.wethinkcode.model.Broker;

/**
 *  Broker App!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Broker broker = new Broker();
	    broker.run();
        } catch (IOException e) {}
    }
}
