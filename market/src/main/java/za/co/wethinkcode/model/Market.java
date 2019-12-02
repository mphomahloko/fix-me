package za.co.wethinkcode.model;

import java.io.IOException;

import java.net.Socket;

import java.util.Scanner;

/**
 * Market App
 *
 */
public class Market {
    public Market() throws IOException {
        Socket socket =  new Socket("127.0.0.1", 5001);
        Scanner in = new Scanner(socket.getInputStream());

        System.out.println("Server response " + in.nextLine());
        try {
            in.close();
            socket.close();
        }catch(IOException e) {
            System.out.println("Market disconnected!");
        }
    }
}
