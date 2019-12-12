package za.co.wethinkcode.core;

import java.io.IOException;

import za.co.wethinkcode.model.Market;

/**
 * Market App!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Market market = new Market();
	    market.run();
        }catch(IOException e) {}
    }
}
