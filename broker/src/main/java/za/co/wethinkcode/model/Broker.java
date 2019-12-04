package za.co.wethinkcode.model;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class Broker {
    public Broker() throws IOException {
        Socket socket =  new Socket("127.0.0.1", 5000);
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Server response " + in.nextLine());
        try {
            in.close();
            socket.close();
            System.out.println("Market disconnected!");
        }catch(IOException e) {
        }
    }
}
