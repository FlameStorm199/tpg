package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public Client(String IP) {
		
		Scanner keyboard = new Scanner(System.in);
		
		try { 	
			connection = new Socket(IP, 20000);	
			
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			
			try {
				while(true) {
					Message op = null;
					Object o = input.readObject();	
					
					if(o instanceof Message) {	
						
						op = (Message)o;	
						
						if(op.getOp().equals(Protocollo.WAITED)) {
							System.out.println("[SERVER] Waited.");
							if(op.getMessage()!=null)
								System.out.println(op.getMessage());
						}	
						else if(op.getOp().equals(Protocollo.REJECTED)) {
							System.out.println("[SERVER] Rejected.");
							if(op.getMessage()!=null)
								System.out.println(op.getMessage());
							input.close();	
							output.close();
							connection.close();
							break;
						}
						else if(op.getOp().equals(Protocollo.ACCEPTED)) {
							System.out.println("[SERVER] Accepted");
							if(op.getMessage()!=null)
								System.out.println(op.getMessage());
							sendMessage();
						}	
						
					}
					else {
						System.out.println("Fatal error: the class received was corrupted.");
						input.close();	
						output.close();
						connection.close();
						break;
					}
				}
				
			}
			catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public void sendMessage() throws IOException {
		System.out.println("[SERVER] Game started");
		input.close();	
		output.close();
		connection.close();
	}
	
	public void chat() {
		
		Scanner keyboard = new Scanner(System.in);
		
		int x = 0;
		boolean stop = false;
		
		Message op = null;
		String message = "";
		
		do {
			
			System.out.println("-- MENU --\n1) Shoot \n2) Save\n3) Research contact\n4) Edit contact\n0) Exit\n\nSelect choice:\t");
			x = keyboard.nextInt();
			
			stop = false;
			
			switch(x) {
			
				case 1:
					
					System.out.println("Type in the position where you want to shoot:\t");
					message = keyboard.next();
					
					op = new Message(Protocollo.SHOOT, message);
					
					break;
			
				case 2:
					
					System.out.println("Type in the position where you want to save:\t");
					message = keyboard.next();
					
					op = new Message(Protocollo.SAVE, message);
					
					break;
				
				case 3:
					
					System.out.println("Type in the name of the contact:\t");
					message = keyboard.next();
					
					//op = new Message(Action.SEARCH,message);
					
					break;
					
				case 4:
					
					System.out.println("Type in the name of the contact:\t");
					message = keyboard.next();
					
					System.out.println("Type in the new name of the contact:\t");
					message += ";"+keyboard.next();
					
					System.out.println("Type in the new number of the contact:\t");
					message += ";"+keyboard.next();
					
					//op = new Message(Action.EDIT,message);
					
					break;
					
				case 0:
					
					stop = true;
					
					op = new Message(Protocollo.END_CONNECTION);
					
					try {
						output.writeObject(op);
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					
					System.out.println("Closing the connection...");
					
					break;
				
				default:
					stop = true;
					System.out.println("You must select one of the options listed above.");
					break;
					
			}
			
			if(!stop) {
				
				try {
				
					output.writeObject(op);	
				
					System.out.println("Waiting for a response...");
					
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}while(x!=0);
		
	}

}

