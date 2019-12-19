package za.co.wethinkcode.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.IOException;

import java.net.Socket;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLDocument;

import java.util.Scanner;

public class Broker {
	private String _serverAddress = "127.0.0.1";
	private int _PORT = 5000; 
	private Socket _socket;
	private Scanner _in;
	private PrintWriter _out;
	private int _brokerId;

	// broker GUI
	private JFrame _frame; // = new JFrame("Broker");
	private JTextField _textField; // = new JTextField(50);
	private JTextPane _messageArea; // = new JTextArea(16, 50);

	// Text Decoration
	private String green = "co:green";
	private String grey = "co:grey";
	private String red = "co:red";
	private String blue = "co:blue";
	private String purple = "co:purple";
	private String reset = "co:reset";

    public Broker() {
		_frame = new JFrame("Broker");
		_messageArea = new JTextPane();
		_textField = new JTextField(50);


	    _messageArea.setSize(500, 250);
	    _messageArea.setEditable(false);
		_messageArea.setContentType("text/html");

		_textField.setEditable(false);

		_frame.getContentPane().add(_textField, BorderLayout.SOUTH);
		_frame.getContentPane().add(new JScrollPane(_messageArea), BorderLayout.CENTER);
		_frame.pack();

		//		_frame.setContentPane((this._panel));

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




		//on ESCAPE
//		_panel.registerKeyboardAction(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				onClose();
//			}
//		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		//editorPane1.setEditable(false);

		_frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				_textField.requestFocus();
			}
		});

		//when cross is clicked
//		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		_frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				onClose();
//			}
//		});

		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(550, 300);
		_frame.setVisible(true);
    }

    private void getBrokerId() throws IOException, BadLocationException {
	    String line = _in.nextLine();
//	    _messageArea.append(line + "\n");
		viewMessage(line, "none");
	    this._brokerId =  Integer.parseInt(line.substring(19));
	    return ;
    }

    public void run() throws IOException, BadLocationException {
	    try {
		    this._socket = new Socket(_serverAddress, _PORT);
		    this._in = new Scanner(_socket.getInputStream());
		    this._out = new PrintWriter(_socket.getOutputStream(), true);

		    getBrokerId();
		    this._frame.setTitle("Broker " + _brokerId);
			this._textField.setEditable(true);
			while (true) {
				while (_in.hasNextLine()) {
					String line = _in.nextLine();
//					_messageArea.append(line + "\n");
					viewMessage(line, "none");
				}
			}
	    } finally {
		    _frame.setVisible(false);
		    _frame.dispose();
	    }
    }

//____________________________________________________________________________________________TEXT DECORATOR___________________________________________________________________________________________

	public void viewMessage(String message, String type) throws IOException, BadLocationException {
		_messageArea.setEditable(true);

		String green = "style=\"color: rgb(162,184,36); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
		String grey = "style=\"color: rgb(120,120,120); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
		String red = "style=\"color: rgb(255,107,104); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
		String blue = "style=\"color: rgb(83,148,236); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
		String purple = "style=\"color: rgb(177, 79, 209); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
		String reset = "style=\"color: rgb(0,0,0); font-weight: bold; font-family: Consolas, monaco, monospace;\"";

		String text;
		String mesType = "";
		String replaceString = message;


		if (type.toLowerCase().contains("error") || type.toLowerCase().contains("normal")) {
			String norm = (this._brokerId == 0) ? ("[<span " + grey + ">" + "FIX-ME" + "</span>" + "] ") : ("[<span " + purple + ">" + "BROKER" + "</span>" + "<span " + reset + "> :" + this._brokerId + "</span>] ");
			mesType = (type.toLowerCase().contains("error") ? ("[<span " + red + ">" + "ERROR" + "</span>" + "] ") : norm);
		}

		replaceString = replaceString.replace("co:blue", ("<span " + blue + ">"));
		replaceString = replaceString.replace("co:grey", ("<span " + grey + ">"));
		replaceString = replaceString.replace("co:green", ("<span " + green + ">"));
		replaceString = replaceString.replace("co:purple", ("<span " + purple + ">"));
		replaceString = replaceString.replace("co:red", ("<span " + red + ">"));
		replaceString = replaceString.replace("co:reset", "</span>");


		text = ("<p" + reset + ">" + mesType + replaceString + "</p><br>");

		this.appendToJPane(text);

	}

	private void appendToJPane(String text) throws IOException, BadLocationException {

		HTMLDocument doc = (HTMLDocument) _messageArea.getStyledDocument();
		doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), text);

		_messageArea.setEditable(false);

		return;
	}

//____________________________________________________________________________________________SWING COMPONENT___________________________________________________________________________________________

	public void onClose() {
		_frame.dispose();
		return;
	}

}
