package za.co.wethinkcode.model;

import java.awt.*;
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

import java.net.UnknownHostException;
import java.util.Scanner;

public class Broker implements Runnable {
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
	public String blue = "co:blue";
	public String green = "co:green";
	public String grey = "co:grey";
	public String purple = "co:purple";
	public String red = "co:red";
	public String teal = "co:teal";
	public String orange = "co:orange";
	public String reset = "co:reset";

    public Broker() {
    	try {
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
					try {
						_out.println(_textField.getText());
						_textField.setText("");
					}
					catch (NullPointerException e) {
						System.out.println("NULLLLLLLLLL");
					}
				}
			});
			DefaultCaret caret = (DefaultCaret) _messageArea.getCaret();
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
					try {
						_textField.requestFocus();
					}
					catch (NullPointerException ex) {
						System.out.println("NULLLLLLLLLL");
					}
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
			_frame.setSize(850, 400);
			_frame.setVisible(true);

			new Thread(this).start();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		}
	}

    private void getBrokerId() throws IOException, BadLocationException, NullPointerException {
    	try {
			String line = _in.nextLine();
//	    _messageArea.append(line + "\n");
//		viewMessage(line, "none");
			this._brokerId = Integer.parseInt(line.substring(19));
			viewMessage("your broker ID is " + this._brokerId, "normal");
			return;
		}
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		}
    }

	@Override
    public void run() {
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
					if (line.contains("8=FIX.4.2"))
						displayFixMessage(line);
					else
						viewMessage(line, "none");
				}
			}
	    }
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    _frame.setVisible(false);
		    _frame.dispose();
	    }
    }

	private void displayFixMessage(String message) throws IOException, BadLocationException, NullPointerException {
		try {
			if (message.contains("39=")) {
				viewMessage(" &lt; [" + blue + "MARKET" + reset + " : " + message.substring((message.indexOf("49=") + 3), (message.indexOf("49=") + 3 + 6)) + "] " + message, "normal");
				char fulfilled = message.charAt(message.indexOf("39=") + 3);

				if (fulfilled == '2')
					viewMessage(" &lt; [" + blue + "MARKET" + reset + " : " + message.substring((message.indexOf("49=") + 3), (message.indexOf("49=") + 3 + 6)) + "] request has been " + green + "ACCEPTED" + reset, "normal");
				if (fulfilled == '8')
					viewMessage(" &lt; [" + blue + "MARKET" + reset + " : " + message.substring((message.indexOf("49=") + 3), (message.indexOf("49=") + 3 + 6)) + "] request has been " + red + "REJECTED" + reset, "normal");
			} else {
				viewMessage(" &gt; [" + blue + "MARKET" + reset + " : " + message.substring((message.indexOf("56=") + 3), (message.indexOf("56=") + 3 + 6)) + "] " + message, "normal");
			}
		}
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		}
	}

//____________________________________________________________________________________________TEXT DECORATOR___________________________________________________________________________________________

	public void viewMessage(String message, String type) throws IOException, BadLocationException, NullPointerException {
    	try {
			_messageArea.setEditable(true);

			String green = "style=\"color: rgb(161,221,112); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String grey = "style=\"color: rgb(120,120,120); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String red = "style=\"color: rgb(199,12,58); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String teal = "style=\"color: rgb(0, 189, 170); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String purple = "style=\"color: rgb(133, 89, 165); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String blue = "style=\"color: rgb(40, 150, 150); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String orange = "style=\"color: rgb(244, 89, 4); font-weight: bold; font-family: Consolas, monaco, monospace;\"";
			String reset = "style=\"color: rgb(0,0,0); font-weight: bold; font-family: Consolas, monaco, monospace;\"";

			String text;
			String mesType = "";
			String replaceString = message;


			if (type.toLowerCase().contains("error") || type.toLowerCase().contains("normal")) {
				String norm = (this._brokerId == 0) ? ("[<span " + teal + ">" + "FIX-ME" + "</span>" + "] ") : ("[<span " + purple + ">" + "BROKER" + "</span>" + "<span " + reset + "></span> : " + this._brokerId + "] ");
				mesType = (type.toLowerCase().contains("error") ? ("[<span " + red + ">" + "ERROR" + "</span>" + "] ") : norm);
			}

			replaceString = replaceString.replace("co:blue", ("<span " + blue + ">"));
			replaceString = replaceString.replace("co:grey", ("<span " + grey + ">"));
			replaceString = replaceString.replace("co:green", ("<span " + green + ">"));
			replaceString = replaceString.replace("co:purple", ("<span " + purple + ">"));
			replaceString = replaceString.replace("co:red", ("<span " + red + ">"));
			replaceString = replaceString.replace("co:teal", ("<span " + teal + ">"));
			replaceString = replaceString.replace("co:reset", "</span>");


			text = ("<p" + reset + ">" + mesType + replaceString + "</p><br>");

			this.appendToJPane(text);
		}
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		}

	}

	private void appendToJPane(String text) throws IOException, BadLocationException, NullPointerException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    HTMLDocument doc = (HTMLDocument) _messageArea.getStyledDocument();
                    doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), text);

                    _messageArea.setEditable(false);

                    return;
                }
                catch (NullPointerException e) {
                    System.out.println("NULLLLLLLLLL");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });
//		try {
//			HTMLDocument doc = (HTMLDocument) _messageArea.getStyledDocument();
//			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), text);
//
//			_messageArea.setEditable(false);
//
//			return;
//		}
//		catch (NullPointerException e) {
//			System.out.println("NULLLLLLLLLL");
//		}
	}

//____________________________________________________________________________________________SWING COMPONENT___________________________________________________________________________________________

	public void onClose() throws IOException, BadLocationException, NullPointerException {
    	try {
			_frame.dispose();
			return;
		}
		catch (NullPointerException e) {
			System.out.println("NULLLLLLLLLL");
		}
	}

}
