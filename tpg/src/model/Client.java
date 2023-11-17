package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import control.Controller;
import control.ControllerFinestraIniziale;

public class Client {

	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ControllerFinestraIniziale cfi;
	private Controller controller;

	public Client(String IP, ControllerFinestraIniziale cfi) {

        this.cfi = cfi;

        try {
            connection = new Socket(IP, 20000);

            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void sendMessage() throws IOException, ClassNotFoundException {
		System.out.println("[SERVER] Game started");
		
		receiveRole();
	}
	
	public void receiveRole() throws ClassNotFoundException, IOException {
		Message op = null;
		String message = "";
		Object o = input.readObject();
		if(o instanceof Message) {
			op = (Message)o;
			if(op.getOp() == Protocollo.ASSIGNMENT) {
				System.out.println("All'utente è stato assegnato il seguente ruolo: "+op.getMessage());
				controller.setRole(op.getMessage());
				output.writeObject(new Message(Protocollo.ACK));
			}else {
				output.writeObject(new Message(Protocollo.NACK));
			}
		}
		prepareShot();
	}
	
	public void prepareShot() throws ClassNotFoundException, IOException {
		Message op = null;
		String message = "";
		Object o = input.readObject();
		if(o instanceof Message) {
			op = (Message)o;
			if(op.getOp() == Protocollo.SERVER_READY) {
				controller.readyForShot();
			}else if(op.getOp() == Protocollo.WAIT_TURN) {
				//TODO: Implement this method?
				//controller.waitForTurn();
			}else {
				//TODO: ???
				output.writeObject(new Message(Protocollo.NACK));
			}
		}
	}
	
	public void sendShot(String role, String position){
		try {
			if(role == "Portiere")
				output.writeObject(new Message(Protocollo.SAVE, position));
			else
				output.writeObject(new Message(Protocollo.SHOOT, position));
			
			Object o = input.readObject();
			if(o instanceof Message) {
				Message op = (Message)o;
				if(op.getOp() == Protocollo.ACK) {
					controller.displayShotResult(op.getMessage());
				}else if(op.getOp() == Protocollo.NACK) {
					controller.flawedConnection(op.getMessage());
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	
	public void configureClient() {
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
                        cfi.acceptedRequest();
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
            e.printStackTrace();
        }
    }

}

