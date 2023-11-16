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
	private Game game;
	private Semaphore lettura;
	private Semaphore scrittura;
	
	public Connessione(Socket request, Game game) {
		
		try {
			
			connection = request;
			
			System.out.println("Connection requested by: "+connection.getInetAddress().toString()+":"+connection.getPort());	//stampiamo il nuovo client collegato
			
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
			
			this.game = game;
			
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

	public void startGame() throws IOException {
		Message op = null;
		op = new Message(Protocollo.ACCEPTED);
		output.writeObject(op);
		//TODO: Remember this
		//this.start();
	}

	public void connectionRejected() throws IOException {
		Message op = null;
		op = new Message(Protocollo.REJECTED, "The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
		output.writeObject(op);
		
		input.close();
		output.close();
		connection.close();
	}

	public void receiveInput() throws IOException, ClassNotFoundException {
		System.out.println("Waiting for an operation...");
		
		Message op = null;
		String position = null;
		String result = null;
		Shot shot = new Shot();
		Object o = input.readObject();
		boolean nack = false;

		if(o instanceof Message) {
		
			op = (Message)o;
			
			if(op.getOp().equals(Protocollo.END_CONNECTION)) {
				//TODO: Find a way to warn the other player of this
				System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
				input.close();
				output.close();
				connection.close();
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
						
						//TODO: Implement method to stop game
						op = new Message(Protocollo.ACK, result);
					}else {
						op = new Message(Protocollo.NACK, "Error: there was a problem with the message receiving");
						nack = true;
					}
				}
				catch(Exception e) {
					op = new Message(Protocollo.NACK, "Error: "+e.getMessage());
					nack = true;
				}
			}else {
				op = new Message(Protocollo.NACK, "Error: there was a problem with the message receiving");
				nack = true;
			}
		}
		
		output.writeObject(op);
		if(nack) {
			input.close();
			output.close();
			connection.close();
		}
	}
		
	public void run() {
		/*
		try {
			Message op = null;
			String position = null;
			String result = null;
			Shot shot = new Shot();
			
			while(true) {	
				
				scrittura.acquire();
				

					lettura.release();
					scrittura.acquire();
					//TODO: Implement choosing to play a new game or ending connection
					
					lettura.release();
				}
			}
			
			input.close();
			output.close();
			connection.close();
			
		}
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Attendi il tuo turno!");
			//e.printStackTrace();
		} */
		
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