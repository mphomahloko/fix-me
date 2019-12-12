package za.co.wethinkcode.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.PrintWriter;
import java.io.IOException;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;

import java.util.Scanner;

public class Broker {
	private String _serverAddress = "127.0.0.1";
	private int _PORT = 5000; 
	private Socket _socket;
	private Scanner _in;
	private PrintWriter _out;
	private int _brokerId;

	// broker GUI
	private JFrame _frame = new JFrame("Broker");
	private JTextField _textField = new JTextField(50);
	private JTextArea _messageArea = new JTextArea(16, 50);

    public Broker() {
	    _textField.setEditable(false);
	    _messageArea.setEditable(false);
	    _frame.getContentPane().add(_textField, BorderLayout.SOUTH);
	    _frame.getContentPane().add(new JScrollPane(_messageArea), BorderLayout.CENTER);
	    _frame.pack();

	    // send on enter and clear to prepare for next msg
	    _textField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
			    _out.println(_textField.getText());
			    _textField.setText("");
		    }
	    });
	    DefaultCaret caret = (DefaultCaret)_messageArea.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    _frame.setVisible(true);
    }

    private void getBrokerId() {
	    String line = _in.nextLine();
	    _messageArea.append(line + "\n");
	    this._brokerId =  Integer.parseInt(line.substring(19));
	    return ;
    }

    public void run() throws IOException {
	    try {
		    this._socket = new Socket(_serverAddress, _PORT);
		    this._in = new Scanner(_socket.getInputStream());
		    this._out = new PrintWriter(_socket.getOutputStream(), true);

		    getBrokerId();
		    this._frame.setTitle("Broker " + _brokerId);
		    this._textField.setEditable(true);

		    while (_in.hasNextLine()) {
			    String line = _in.nextLine();
			    _messageArea.append(line + "\n");
		    }
	    } finally {
		    _frame.setVisible(false);
		    _frame.dispose();
	    }
    }
}
