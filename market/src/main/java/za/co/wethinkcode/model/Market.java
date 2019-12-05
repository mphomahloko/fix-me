package za.co.wethinkcode.model;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.Scanner;

/**
 * Market App
 *
 */
public class Market {
   private Scanner _in;
   private PrintWriter _out;

    public Market() throws IOException {
        Socket socket =  new Socket("127.0.0.1", 5001);
        _in = new Scanner(socket.getInputStream());
        _out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Server response " + _in.nextLine());
        try {
            // implementing logic
            _in.close();
            socket.close();
            System.out.println("Market disconnected!");
        }catch(IOException e) {
        }
    }
}
