package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connessione extends Thread {

	private Socket connection;
	private ObjectInputStream input;	
	private ObjectOutputStream output;	
	//private Phonebook phonebook;
	
	public Connessione(Socket request/*, Phonebook phonebook*/) {
		
		try {
			
			connection = request;
			
			System.out.println("Connection requested by: "+connection.getInetAddress().toString()+":"+connection.getPort());	//stampiamo il nuovo client collegato
			
			input = new ObjectInputStream(connection.getInputStream());
			output = new ObjectOutputStream(connection.getOutputStream());
			
			//this.phonebook = phonebook;
			
			this.start();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*public void run() {
		
		try {
			
			Message op = null;
			String app[] = new String[3];	
			Contact c = null;
			
			while(true) {	
				
				System.out.println("Waiting for an operation...");
				
				Object o = input.readObject();

				if(o instanceof Message) {
				
					op = (Message)o;

					if(op.getOp().equals(Action.END_CONNECTION)) {
						System.out.println("Connection ended by: "+connection.getInetAddress().toString()+":"+connection.getPort());
						break;	
					}
					
					if(!op.getOp().equals(Action.ACK) && !op.getOp().equals(Action.NACK)) {	
						
						app = op.getMessage().split(";");	
						
						try {
							
							if(op.getOp().equals(Action.ADD))	
								phonebook.addContact(app[0],app[1]);
							else if(op.getOp().equals(Action.DELETE))
								phonebook.deleteContact(app[0]);
							else if(op.getOp().equals(Action.SEARCH))
								c = phonebook.searchContact(app[0]);
							else if(op.getOp().equals(Action.EDIT))
								phonebook.editContact(app[0],app[1],app[2]);
							
							System.out.println(phonebook.printContacts());	
							
							op = new Message(Action.ACK);
							
							if(op.getOp().equals(Action.SEARCH))
								op.setMessage(c.toString());
							
						}
						catch(Exception e) {
							op = new Message(Action.NACK,"Error: "+e.getMessage());	
						}
						
					}
					else
						op = new Message(Action.NACK,"Error: invalid operation.");	
				
				}	
				else
					op = new Message(Action.NACK,"Error: the client sent a wrong class.");
				
				output.writeObject(op);
				
			}
			
			input.close();
			output.close();
			connection.close();
			
		}
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	*/
}