package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import control.Controller;
import control.InitialWindowController;

public class Client extends Thread{

	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private InitialWindowController cfi;
	private Controller controller;
	private boolean end_connection;

	public Client(String IP, InitialWindowController cfi, Controller controller) {
		
		this.cfi = cfi;
		end_connection = false;
		
		try { 	
			connection = new Socket(IP, 20000);	
			
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			
			this.controller = controller;
		}
		catch (IOException e) {
			System.out.println("There was an error while connecting the client to the server. The program will be terminated.");
            //e.printStackTrace();
        }
	}
	
	public void run() {
		while(!end_connection) {
			String res = "";
			try {
				Object o = input.readObject();
				if(o instanceof Message) {
					Message message = (Message)o;
					switch(message.getOp()) {
						case WAITED:
							System.out.println("[SERVER] Waited.");
							cfi.waitRequest();
							break;
						case ACCEPTED:
							System.out.println("[SERVER] Accepted");
							cfi.acceptedRequest();
							System.out.println("[SERVER] Game started");
							break;
						case REJECTED:
							System.out.println("[SERVER] Rejected.");
							cfi.rejectedRequest();
							end_connection = true;
							input.close();	
							output.close();
							connection.close();
							break;
						case ASSIGNMENT:
							System.out.println("[SERVER] You were assigned to this role: "+message.getMessage());
							controller.setInitialRole(message.getMessage());
							controller.changeRole();
							controller.showRole();
							break;
						case BROADCAST:
							System.out.println("[SERVER] Broadcast message: "+message.getMessage());
							res = message.getMessage();
							controller.displayShotResult(res);
							controller.changeRole();
							controller.showRole();
							break;
						case ENDED:
							System.out.println("[SERVER] Game ended");
							controller.askForNewGame();
							break;
						case ACK:
							if(message.getMessage() != null)
								System.out.println("[SERVER] Ack received: "+message.getMessage());
							else
								System.out.println("[SERVER] Ack received.");
							controller.waitForTurn(message.getMessage());
							break;
						case NACK:
							if(message.getMessage() != null)
								System.out.println("[SERVER] Nack received: "+message.getMessage());
							else
								System.out.println("[SERVER] Nack received.");
							res = message.getMessage();
							break;
						default:
							throw new IOException();	
					}
				}else {
					throw new IOException();
				}
				
			}catch(Exception e){
				System.out.println("There was an error while reading the message received from the server. The program will be terminated.");
			}
		}
	}
	
	public void sendInput() {
		String role = controller.getRole();
		String position = controller.getPosition();
		try {
			if(role.equals("Portiere"))
				output.writeObject(new Message(Protocol.SAVE, position));
			else
				output.writeObject(new Message(Protocol.SHOOT, position));
		}catch(Exception e) {
			System.out.println("There was an error while sending the shot or save to the server. The program will be terminated.");
		}

	}
	
	public void closeConnection() {
		try {
			output.writeObject(new Message(Protocol.END_CONNECTION));
		}catch(Exception e) {
			System.out.println("There was an error while closing the connection to the server. The program will be terminated.");
		}
	}
	
	public void newGame() {
		try {
			output.writeObject(new Message(Protocol.ACK));
		}catch(Exception e) {
			System.out.println("There was an error while trying to start a new game. The program will be terminated.");
		}
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}

