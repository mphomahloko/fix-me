package za.co.wethinkcode.model.executor;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.Scanner;

public class MarketExecutor {

	private static MarketExecutor _market = new MarketExecutor();

	private String _serverAddress = "127.0.0.1";
	private int _PORT = 5001;

	public String Order = "Egg 2 3";

	private Socket _socket;
	private Scanner _in;
	private PrintWriter _out;
	private int _marketId;
	
	public MarketExecutor() {
		return ;
	}
	
	public static MarketExecutor getMarket() {
		return _market;
	}

	private void getMarketId() {
		String line = _in.nextLine();
		System.out.println(line);
		this._marketId = Integer.parseInt(line.substring(19));
		return ;
	}

	private String marketStock() {
		String stockAvailable = "Stock";
		return stockAvailable;
	}

	public void run() throws IOException {
		try {
			this._socket = new Socket(_serverAddress, _PORT);
			this._in = new Scanner(_socket.getInputStream());
			this._out = new PrintWriter(_socket.getOutputStream(), true);

			getMarketId();
			_out.println(marketStock());
		} finally {}
	}
}
