package za.co.wethinkcode.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Router class
 * 
 */
public class Router {
	public Router() throws IOException {
		try(ServerSocket listener = new ServerSocket(5000)) {
			System.out.println("Router is running...");
			while (true) {
				try(Socket socket = listener.accept()) {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("You connected successfully...");
				}
			}
		}
	}
}
