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
			server.setCurrentShot(new Shot());
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
							server.getCurrentShot().setShot(message.getMessage());
							result = "Shot received, waiting for save...";
							if(server.getCurrentShot().getSave() != null) {
								String temp = "";
								result = server.getGame().tryShot(server.getCurrentShot());
								temp = server.getCurrentShot().getShot()+ "@" +
										server.getCurrentShot().getSave()+ "@" + 
										server.getGame().getShotNumber() + "@" +
										server.getGame().getScore1() + "@" + 
										server.getGame().getScore2() + "@" + 
										result;							
								server.setBroadcastMessage(temp);
								server.setCurrentShot(new Shot());
								broadcastReceiver = false;
								result = server.getBroadcastMessage();
								message = new Message(Protocol.BROADCAST, result);
							}else {
								broadcastReceiver = true;
								message = new Message(Protocol.ACK, result);
							}
							
							break;
						case SAVE:
							server.getCurrentShot().setSave(message.getMessage());
							result = "Save received, waiting for shot...";
							if(server.getCurrentShot().getShot()!=null) {
								String temp = "";
								result = server.getGame().tryShot(server.getCurrentShot());
								temp = server.getCurrentShot().getShot()+ "@" +
										server.getCurrentShot().getSave()+ "@" + 
										server.getGame().getShotNumber() + "@" +
										server.getGame().getScore1() + "@" + 
										server.getGame().getScore2() + "@" + 
										result;		
								server.setBroadcastMessage(temp);
								server.setCurrentShot(new Shot());
								broadcastReceiver = false;
								result = server.getBroadcastMessage();
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
							server.getGame().endGameByForce();
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
							if(server.getBroadcastMessage() != null) {
								output.writeObject(new Message(Protocol.BROADCAST, server.getBroadcastMessage()));
							}
						}while(server.getBroadcastMessage() == null);
						server.setBroadcastMessage(null);
					}
					
					if(server.getGame().ended()) {
						output.writeObject(new Message(Protocol.ENDED));
						o = input.readObject();
						if(o instanceof Message) {
							message = (Message)o;
							if(message.getOp() == Protocol.ACK)
								server.setGame(new Game());
							else if(message.getOp() == Protocol.END_CONNECTION) {
								end_connection = true;
								System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
							}	
						}else {
							throw new IOException();
						}
					}
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