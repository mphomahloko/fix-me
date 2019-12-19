package za.co.wethinkcode.model;

import sun.jvm.hotspot.debugger.win32.coff.TestDebugInfo;

import java.io.PrintWriter;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Router class
 *
 */
public class Router {
    public static TextDecorator _td = new TextDecorator();
    private static Set<Integer> _ids = new HashSet<>();
    // The set of all the print writers for all the brokers, used for broadcast.
    private static Set<Map<Integer,PrintWriter>> _brokerWriters = new HashSet<>();
    private static Set<Map<Integer,PrintWriter>> _marketWriters = new HashSet<>();

    private static FixMessageDatabase fixMessageDatabase = new FixMessageDatabase();

    private static int _posibleID = 100000;
    public Router() throws IOException, SQLException, NoSuchElementException {
        System.out.println(_td.viewMessage("Router is running...","normal"));

        fixMessageDatabase.createTransactionTable();

        Thread brokers = new Thread(new ManagesBroker());
        brokers.start();

        Thread markets = new Thread(new ManagesMarket());
        markets.start();
    }

/* ********************************* Broker ************************************** */ 
    private static class ManagesBroker implements Runnable {
        @Override
        public void run() {
            ExecutorService pool = Executors.newFixedThreadPool(20);
            try (ServerSocket listener = new ServerSocket(5000)) {
                while (true) {
                    pool.execute(new ManageBroker(listener.accept()));
                }
            } catch (IOException e) {}
            catch (NoSuchElementException e) {
                System.out.println(_td.viewMessage("Broker | Market Disconnection Detected!","error"));
            }
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
            try {
                _in = new Scanner(_socket.getInputStream());
                _out = new PrintWriter(_socket.getOutputStream(), true);
                synchronized (_ids) {
                    if (!_ids.contains(_posibleID += 1)) {
                        _ids.add(_id = _posibleID - 1);
                    }
                }
                _out.println("your broker id is: " + _id);
                System.out.println(_td.viewMessage(("[" + _td.purple + "BROKER " + _td.reset + ": " +  _id + "] has successfully connected..."),"none"));
                Map<Integer,PrintWriter> broker = new HashMap<Integer,PrintWriter>();
                broker.put(_id, _out);
                _brokerWriters.add(broker);
                // ...
                FixMessages fix = new FixMessages(_id, _in, _out);
                while (true) {
                    String fixedMsg;
                    // 1. get fixed msg
                    // fixedMsg =  _in.nextLine();
                    fixedMsg = fix.buyOrSell();
                    _out.println(fixedMsg);
                    System.out.println(fixedMsg);
                    fixMessageDatabase.saveToDataBase(fixedMsg);

                    // 2. send to desired market
                    Decoder recieverID = new Decoder(fixedMsg);
                    for (Map<Integer, PrintWriter> writers : _marketWriters) {
                        for (Integer identifier: writers.keySet()) { 
                            if (Integer.parseInt(recieverID.getReciverID()) == identifier) {
                                PrintWriter writer = writers.get(identifier);
                                writer.println(fixedMsg);
                            }
                        }
                    }
                    // 3. wait for responce from market

                    // 4.  send back to broker

                }

            }
            catch (IOException | SQLException ex) {
                System.out.println(ex);
            }
            catch (NoSuchElementException e) {
                System.out.println(_td.viewMessage("Broker | Market Disconnection Detected!","error"));
            }
            finally {
		    if (_ids.contains(_id)) {
			    _ids.remove(_id);
		    }
		    System.out.println(_td.viewMessage(("[" + _td.red + "BROKER " + _td.reset + ": " +  _id + "] has disconnected..."),"none"));
		    try {
			    _socket.close();
		    } catch (IOException e) {}
            catch (NoSuchElementException e)
            {
                System.out.println(_td.viewMessage("Broker | Market Disconnection Detected!","error"));
            }
	    }
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
            try (ServerSocket listener = new ServerSocket(5001)) {
                while (true) {
                    pool.execute(new ManageMarket(listener.accept()));
                }
            } catch (IOException e) {}
            catch (NoSuchElementException e)
            {
                System.out.println(_td.viewMessage("Broker | Market Disconnection Detected!","error"));
            }
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
            try {
                _in = new Scanner(_socket.getInputStream());
                _out = new PrintWriter(_socket.getOutputStream(), true);
                synchronized (_ids) {
                    if (!_ids.contains(_posibleID += 1)) {
                        _ids.add(_id = _posibleID - 1);
                    }
                }
//                _out.println("your market id is: " + _id);
                _out.println(_td.viewMessage(("[" + _td.blue + "MARKET " + _td.reset + ": " +  _id + "] you market is " + _id),"none"));
                System.out.println(_td.viewMessage(("[" + _td.blue + "MARKET " + _td.reset + ": " +  _id + "] has successfully connected..."),"none"));

                Map<Integer,PrintWriter> market = new HashMap<Integer,PrintWriter>();
                market.put(_id, _out);
                _marketWriters.add(market);

                // sending available products to the broker
                for (Map<Integer, PrintWriter> writers : _brokerWriters) {
                    for (PrintWriter writer: writers.values()) {
                        while (_in.hasNextLine()) {    
                            writer.println(_in.nextLine());
                        }
                    }
                }
            } catch (IOException e) {}
            catch (NoSuchElementException e)
            {
                System.out.println(_td.viewMessage("Broker | Market Disconnection Detected!","error"));
            }
        }
    }
}
