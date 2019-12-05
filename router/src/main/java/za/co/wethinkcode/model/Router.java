package za.co.wethinkcode.model;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Router class
 *
 */
public class Router {
    private static Set<Integer> _ids = new HashSet<>();
    private static int _posibleID = 100000;
    public Router() throws IOException {
        System.out.println("Router is running...");

        Thread brokers = new Thread(new ManagesBroker());
        brokers.start();

        Thread markets = new Thread(new ManagesMarket());
        markets.start();
    }

/* ********************************* Broker ************************************** */ 
    private static class ManagesBroker implements Runnable{
        @Override
        public void run() {
            ExecutorService pool = Executors.newFixedThreadPool(20);
            try(ServerSocket listener = new ServerSocket(5000)) {
                while (true) {
                    pool.execute(new ManageBroker(listener.accept()));
                }
            }catch(IOException e) {}
        }
    }

    private static class ManageBroker implements Runnable {
        private int _id;
        private Socket _socket;
        private Scanner _in;
        private PrintWriter _out;

        public ManageBroker(Socket socket) {
            this._socket = socket;
        }

        @Override public void run() {
            try{
                _in = new Scanner(_socket.getInputStream());
                _out = new PrintWriter(_socket.getOutputStream(), true);
                synchronized (_ids) {
                    if (!_ids.contains(_posibleID += 1)) {
                        _ids.add(_id = _posibleID - 1);
                    }
                }
                // add communication here
                _out.println("your broker id is: " + _id);
//                while (true) {
//
//                }
            }catch(IOException e) {}
        }
    }

/* ********************************* Market ************************************** */
    private static class ManagesMarket implements Runnable {
        public ManagesMarket() {
            return ;
        }

        @Override
        public void run() {
            ExecutorService pool = Executors.newFixedThreadPool(20);
            try(ServerSocket listener = new ServerSocket(5001)) {
                while (true) {
                    pool.execute(new ManageMarket(listener.accept()));
                }
            }catch(IOException e) {}
        }
    }

    private static class ManageMarket implements Runnable {
        private int _id;
        private Socket _socket;
        private Scanner _in;
        private PrintWriter _out;

        public ManageMarket(Socket socket) {
            this._socket = socket;
            return ;
        }

        @Override public void run() {
            try{
                _in = new Scanner(_socket.getInputStream());
                _out = new PrintWriter(_socket.getOutputStream(), true);
                synchronized (_ids) {
                    if (!_ids.contains(_posibleID += 1)) {
                        _ids.add(_id = _posibleID - 1);
                    }
                }
                _out.println("your market id is: " + _id);
            }catch(IOException e) {}
        }
    }
}
