package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connessione extends Thread {

	private Socket connection;
	private ObjectInputStream input;	
	private ObjectOutputStream output;	
	private Shot shot;
	private Game game;
	
	public Connessione(Socket request) {
		
		try {
			
			connection = request;
			
			System.out.println("Connection requested by: "+connection.getInetAddress().toString()+":"+connection.getPort());	//stampiamo il nuovo client collegato
			
			//TODO: The connection request should be here???
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
			
			this.game = new Game();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void waitRequest() throws IOException {
		// TODO Auto-generated method stub
		Message op = null;
		op = new Message(Protocollo.WAITED, "The connection to the server was succesful, wait for another player to connect...");
		output.writeObject(op);
	}

	public void startGame() throws IOException {
		// TODO Auto-generated method stub
		Message op = null;
		op = new Message(Protocollo.ACCEPTED);
		output.writeObject(op);
		this.start();
	}

	public void connectionRejected() throws IOException {
		// TODO Auto-generated method stub
		Message op = null;
		op = new Message(Protocollo.REJECTED, "The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
		output.writeObject(op);
		
		input.close();
		output.close();
		connection.close();
	}
	
	public void run() {
		
		try {
			Message op = null;
			String position = null;
			String result = null;
			shot = new Shot();
			
			while(true) {	
				
				//TODO: Serve un controllo per la sincronizzazione?
				//RMB: Here it takes the operation
				System.out.println("Waiting for an operation...");
				
				Object o = input.readObject();

				if(o instanceof Message) {
				
					op = (Message)o;
					
					//RMB End connection
					if(op.getOp().equals(Protocollo.END_CONNECTION)) {
						System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
						break;	
					}
					
					if(!op.getOp().equals(Protocollo.ACK) && !op.getOp().equals(Protocollo.NACK)) {
						position = op.getMessage();
						
						try {
							if(op.getOp().equals(Protocollo.SHOOT)) {
								shot.setShot(position);
								result = "Shot received, waiting for save...";
								
								if(shot.getSave()!=null) {
									result = game.tryShot(shot);
									shot = new Shot();
								}
									
								op = new Message(Protocollo.ACK, result);
							}else if(op.getOp().equals(Protocollo.SAVE)) {
								shot.setSave(position);
								result = "Save received, waiting for shot...";
								
								if(shot.getShot()!=null) {
									result = game.tryShot(shot);
									shot = new Shot();
								}
								
								op = new Message(Protocollo.ACK, result);
							}else {
								op = new Message(Protocollo.NACK, "Error: there was a problem with the message receiving");
							}
						}
						catch(Exception e) {
							op = new Message(Protocollo.NACK, "Error: "+e.getMessage());
						}
					}else {
						op = new Message(Protocollo.NACK, "Error: there was a problem with the message receiving");
					}
					//TODO: Implement choosing to play a new game or ending connection
					output.writeObject(op);
				}
			}
			
			input.close();
			output.close();
			connection.close();
			
		}
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
}