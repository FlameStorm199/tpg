package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {

	private Socket connection;
	private ObjectInputStream input;	
	private ObjectOutputStream output;	
	private String role;
	private Server server;
	private boolean broadcastReceiver;

	public Connection(Socket request, Game game, Server server) {
		
		try {
			this.server = server;
			broadcastReceiver = false;
			connection = request;
			
			System.out.println("Connection requested by: "+connection.getInetAddress().toString()+":"+connection.getPort());	//stampiamo il nuovo client collegato
			
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
		}
		catch (IOException e) {
			System.out.println("An IOException has been thrown while initializing the connection for "+connection.getInetAddress().toString()+". The connection will be terminated.");
			//e.printStackTrace();
		}
		
	}
	
	public void connectionRejected() throws IOException {
		Message op = null;
		op = new Message(Protocol.REJECTED, "The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
		output.writeObject(op);
		
		System.out.println("The connection "+connection.getInetAddress().toString()+" has been rejected.");
		input.close();
		output.close();
		connection.close();
	}
	
	public void waitRequest() throws IOException {
		Message op = null;
		op = new Message(Protocol.WAITED, "The connection to the server was succesful, wait for another player to connect...");
		output.writeObject(op);
		System.out.println("The connection "+connection.getInetAddress().toString()+" is waiting for the other client.");
	}

	public void startGame(int role) throws IOException {
		Message op = null;
		op = new Message(Protocol.ACCEPTED);
		output.writeObject(op);
		if(role == 0)
			this.role = "Attaccante";
		else
			this.role = "Portiere";
		
		System.out.println("The connection "+connection.getInetAddress().toString()+" has been accepted.");
	}

	public void run() {
		try {
			output.writeObject(new Message(Protocol.ASSIGNMENT, this.role));
			
			boolean end_connection = false;
			
			server.currentShot = new Shot();
			while(!end_connection) {
				//System.out.println("Waiting for an operation...");
				
				Message message = null;
				String result = null;
				Object o = new Object();
				
				o = input.readObject();
				if(o instanceof Message) {
					message = (Message)o;
					
					System.out.println("The address "+connection.getInetAddress().toString()+" has just sent a message of type "+message.getOp() +" that says: \""+message.getMessage()+"\"");
					switch(message.getOp()) {
						case SHOOT:
							server.currentShot.setShot(message.getMessage());
							result = "Shot received, waiting for save...";
							if(server.currentShot.getSave() != null) {
								String temp = "";
								result = server.game.tryShot(server.currentShot);
								if(this.role.equals("Attaccante"))
									temp = server.currentShot.getShot()+ "@" +
											server.currentShot.getSave()+ "@" + 
											server.game.getShotNumber() + "@" +
											server.game.getScore1() + "@" + 
											server.game.getScore2() + "@" + 
											result;
								else
									temp = server.currentShot.getShot()+ "@" +
											server.currentShot.getSave()+ "@" + 
											server.game.getShotNumber() + "@" +
											server.game.getScore2() + "@" + 
											server.game.getScore1() + "@" + 
											result;								
								server.broadcastMessage = temp;
								server.currentShot = new Shot();
								broadcastReceiver = false;
								result = server.broadcastMessage;
								message = new Message(Protocol.BROADCAST, result);
							}else {
								broadcastReceiver = true;
								message = new Message(Protocol.ACK, result);
							}
							
							break;
						case SAVE:
							server.currentShot.setSave(message.getMessage());
							result = "Save received, waiting for shot...";
							if(server.currentShot.getShot()!=null) {
								String temp = "";
								result = server.game.tryShot(server.currentShot);
								if(this.role.equals("Attaccante"))
									temp = server.currentShot.getShot()+ "@" +
											server.currentShot.getSave()+ "@" + 
											server.game.getShotNumber() + "@" +
											server.game.getScore1() + "@" + 
											server.game.getScore2() + "@" + 
											result;
								else
									temp = server.currentShot.getShot()+ "@" +
											server.currentShot.getSave()+ "@" + 
											server.game.getShotNumber() + "@" +
											server.game.getScore2() + "@" + 
											server.game.getScore1() + "@" + 
											result;	
								server.broadcastMessage = temp;
								server.currentShot = new Shot();
								broadcastReceiver = false;
								result = server.broadcastMessage;
								message = new Message(Protocol.BROADCAST, result);
							}else {
								broadcastReceiver = true;
								message = new Message(Protocol.ACK, result);
							}
							break;
						case ACK:
						case NACK:
							message = new Message(Protocol.NACK, "The client sent a wrong input: "+message.getMessage());
							break;
						case END_CONNECTION:
							//TODO: The other client should be warned of this
							System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
							end_connection = true;
							server.game.endGameByForce();
							break;
						default:
							throw new IOException();
					}
					
					if(message != null && message.getOp() != Protocol.END_CONNECTION) {
						output.writeObject(message);
						System.out.println("A message of type "+message.getOp() +" that says: \""+message.getMessage()+"\" has been sent to the address "+connection.getInetAddress().toString());
					}else {
						broadcastReceiver = false;
					}
					
					if(broadcastReceiver) {
						do{
							//System.out.println();
							Thread.sleep(1000);
							if(server.broadcastMessage != null) {
								output.writeObject(new Message(Protocol.BROADCAST, server.broadcastMessage));
							}
						}while(server.broadcastMessage == null);
						server.broadcastMessage = null;
					}
					
					if(server.game.ended()) {
						output.writeObject(new Message(Protocol.ACK, "Game ended"));
						o = input.readObject();
						if(o instanceof Message) {
							message = (Message)o;
							if(message.getOp() == Protocol.ACK)
								server.game = new Game();
							else if(message.getOp() == Protocol.END_CONNECTION) {
								end_connection = true;
								System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
							}	
						}else {
							throw new IOException();
						}
					}
					else
						output.writeObject(new Message(Protocol.ACK, "Game not ended"));
				}else {
					throw new IOException();
				}
			}
			
			input.close();
			output.close();
			connection.close();
		}catch(IOException e) {
			System.out.println("An IOException has been thrown by the connection "+connection.getInetAddress().toString()+". The connection will be terminated.");
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