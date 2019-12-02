package za.co.wethinkcode.model;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Router class
 *
 */
public class Router {
    public Router() throws IOException {
        Thread brokers = new Thread(new ManageBroker());
        brokers.start();

        Thread markets = new Thread(new ManageMarket());
        markets.start();
    }
    
    private static class ManageBroker implements Runnable {
        ManageBroker() {
            return ;
        }

        @Override
        public void run() {
            System.out.println("connected to port 5000");
            try(ServerSocket listener = new ServerSocket(5000)) {
                System.out.println("Router is running...");
                while (true) {
                    try(Socket socket = listener.accept()) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("You connected successfully...");
                        System.out.println("Broker");
                    }catch(IOException e) {}
                }
            }catch(IOException e) {}
        }
    }

    private static class ManageMarket implements Runnable {
        ManageMarket() {
            return ;
        }
        @Override public void run() {
            System.out.println("connected to port 5001");
            try(ServerSocket listener = new ServerSocket(5001)) {
                System.out.println("Router is running...");
                while (true) {
                    try(Socket socket = listener.accept()) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("You connected successfully...");
                        System.out.println("Market");
                    }catch(IOException e) {}
                }
            }catch(IOException e) {}
        }
    }
}
