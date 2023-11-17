package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Connessione extends Thread {

	private Socket connection;
	private ObjectInputStream input;	
	private ObjectOutputStream output;	
	private Semaphore lettura;
	private Semaphore scrittura;
	private String role;
	private Server server;
	private boolean broadcastReceiver;

	public Connessione(Socket request, Game game, Server server) {
		
		try {
			this.server = server;
			broadcastReceiver = false;
			connection = request;
			
			System.out.println("Connection requested by: "+connection.getInetAddress().toString()+":"+connection.getPort());	//stampiamo il nuovo client collegato
			
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
			
			lettura = new Semaphore(1);
			scrittura = new Semaphore(0);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void waitRequest() throws IOException {
		Message op = null;
		op = new Message(Protocollo.WAITED, "The connection to the server was succesful, wait for another player to connect...");
		output.writeObject(op);
	}

	public void startGame(int role) throws IOException {
		Message op = null;
		op = new Message(Protocollo.ACCEPTED);
		output.writeObject(op);
		if(role == 0)
			this.role = "Attaccante";
		else
			this.role = "Portiere";
	}

	public void connectionRejected() throws IOException {
		Message op = null;
		op = new Message(Protocollo.REJECTED, "The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
		output.writeObject(op);
		
		input.close();
		output.close();
		connection.close();
	}

	public void run() {
		try {
			output.writeObject(new Message(Protocollo.ASSIGNMENT, this.role));
			
			boolean end_connection = false;
			
			server.currentShot = new Shot();
			while(!end_connection) {
				System.out.println("Waiting for an operation...");
				
				Message op = null;
				String result = null;
				Object o = new Object();
				
				o = input.readObject();
				if(o instanceof Message) {
					op = (Message)o;
					
					switch(op.getOp()) {
						case SHOOT:
							server.currentShot.setShot(op.getMessage());
							result = "Shot received, waiting for save...";
							if(server.currentShot.getSave() != null) {
								result = server.game.tryShot(server.currentShot);
								server.broadcastMessage = server.currentShot.getShot()+server.currentShot.getSave()+result;
								System.out.println(server.broadcastMessage);
								server.currentShot = new Shot();
								broadcastReceiver = false;
							}else {
								broadcastReceiver = true;
							}
							System.out.println(result);
							op = new Message(Protocollo.ACK, result);
							break;
						case SAVE:
							server.currentShot.setSave(op.getMessage());
							result = "Save received, waiting for shot...";
							if(server.currentShot.getShot()!=null) {
								result = server.game.tryShot(server.currentShot);
								server.broadcastMessage = server.currentShot.getShot()+server.currentShot.getSave()+result;
								System.out.println(server.broadcastMessage);
								server.currentShot = new Shot();
								broadcastReceiver = false;
							}else {
								broadcastReceiver = true;
							}
							System.out.println(result);
							op = new Message(Protocollo.ACK, result);
							break;
						case ACK:
						case NACK:
							op = new Message(Protocollo.NACK, "The client sent a wrong input: "+op.getMessage());
							break;
						case END_CONNECTION:
							//TODO: The other client should be warned of this
							System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
							end_connection = true;
							break;
						default:
							throw new IOException();
					}
					
					output.writeObject(op);
					
					if(broadcastReceiver) {
						do{
							//System.out.println();
							Thread.sleep(1000);
							if(server.broadcastMessage != null) {
								output.writeObject(new Message(Protocollo.BROADCAST, server.broadcastMessage));
							}
						}while(server.broadcastMessage == null);
						server.broadcastMessage = null;
					}
				}else {
					throw new IOException();
				}
			}
			
			input.close();
			output.close();
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRole() {
		return this.role;
	}
	
	public Socket getConnection() {
		return this.connection;
	}
	
	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}
}