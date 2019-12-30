package za.co.wethinkcode.model.executor;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.NoSuchElementException;
import java.util.Scanner;

import za.co.wethinkcode.core.App;

import za.co.wethinkcode.model.product.Product;
import za.co.wethinkcode.model.ConsoleDecorator;

public class MarketExecutor {


	private static MarketExecutor _market = new MarketExecutor();

	private String _serverAddress = "127.0.0.1";
	private int _PORT = 5001;

	private Socket _socket;
	private Scanner _in;
	private PrintWriter _out;
	private int _marketId;
	
	public MarketExecutor() throws NullPointerException {
		return ;
	}
	
	public static MarketExecutor getMarket() throws NullPointerException {
		return _market;
	}

	public ConsoleDecorator _td = new ConsoleDecorator();

	private void getMarketId() throws NullPointerException {
		String line = _in.nextLine();
		System.out.println(line);
		this._marketId = Integer.parseInt(line.substring((line.length() - 6),line.length()));
		return ;
	}

	private void marketStock() throws NullPointerException {
		_out.println("[<span style=\"color: rgb(40, 150, 150); font-weight: bold; font-family: Consolas, monaco, monospace;\" >MARKET</span> : " + _marketId + "] Available Stock");

		String formTable = "";

		formTable += "<style> table { width: 100%; border-collapse: collapse; } td, th { padding: 5px 20px 5px 20px;} td { border-collapse: collapse;} th { border-collapse: collapse; background-color: rgb(0, 189, 170); font-weight: bold; font-family: Consolas, monaco, monospace; color: white; } </style>" +
				"<table>" +
				"<tr>" + "<th>Item</th>" + "<th>Amount (R)</th>" + "<th>Quantity</th>" + "</tr>";


		for (Product product: App.products) {

			formTable += "<tr>" + product + "</tr>";
		}
		formTable += "</table>";

		_out.println(formTable);
		return ;
	}



	public void run() throws IOException, NoSuchElementException, NullPointerException {
		try {
			this._socket = new Socket(_serverAddress, _PORT);
			this._in = new Scanner(_socket.getInputStream());
			this._out = new PrintWriter(_socket.getOutputStream(), true);
			String input;
			String responce;
			getMarketId();
			// send market products to brokers for trading.
			marketStock();
			while (true) {
				while (_in.hasNextLine()) {
					input = _in.nextLine();
					displayFixMessage(input);
					responce = App.tower.updatedProducts(input);
					_out.println(responce);
					displayFixMessage(responce);
					marketStock();
				}
			}
		}
		finally {}
	}

	private void displayFixMessage(String message) throws IOException, NullPointerException {

		if (message.contains("8=FIX.4.2")) {
			if (message.contains("39=")) {
				System.out.println(ConsoleDecorator.viewMessage("[" + ConsoleDecorator.blue + "MARKET" + ConsoleDecorator.reset + " : " + this._marketId + "] > [" + ConsoleDecorator.purple + "BROKER" + ConsoleDecorator.reset + " : " + message.substring((message.indexOf("56=") + 3), (message.indexOf("56=") + 3 + 6)) + "] " + message, "none"));
				char fulfilled = message.charAt(message.indexOf("39=") + 3);

				if (fulfilled == '2')
					System.out.println(ConsoleDecorator.viewMessage("[" + ConsoleDecorator.blue + "MARKET" + ConsoleDecorator.reset + " : " + this._marketId + "] > [" + ConsoleDecorator.purple + "BROKER" + ConsoleDecorator.reset + " : " + message.substring((message.indexOf("56=") + 3), (message.indexOf("56=") + 3 + 6)) + "] request has been " + ConsoleDecorator.green + "ACCEPTED" + ConsoleDecorator.reset, "none"));
				if (fulfilled == '8')
					System.out.println(ConsoleDecorator.viewMessage("[" + ConsoleDecorator.blue + "MARKET" + ConsoleDecorator.reset + " : " + this._marketId + "] > [" + ConsoleDecorator.purple + "BROKER" + ConsoleDecorator.reset + " : " + message.substring((message.indexOf("56=") + 3), (message.indexOf("56=") + 3 + 6)) + "] request has been " + ConsoleDecorator.red + "REJECTED" + ConsoleDecorator.reset, "none"));
			} else
				System.out.println(ConsoleDecorator.viewMessage("[" + ConsoleDecorator.blue + "MARKET" + ConsoleDecorator.reset + " : " + this._marketId + "] < [" + ConsoleDecorator.purple + "BROKER" + ConsoleDecorator.reset + " : " + message.substring((message.indexOf("49=") + 3), (message.indexOf("49=") + 3 + 6)) + "] " + message, "none"));
		}
		else
			System.out.println(message);
	}
}
