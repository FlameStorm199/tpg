package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import control.Controller;
import control.ControllerFinestraIniziale;

public class Client{

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
	
	public String leggi() {
		String res = "";
		try {
			
			Object o = input.readObject();
			if(o instanceof Message) {
				Message op = (Message)o;
				switch(op.getOp()) {
					case WAITED:
						System.out.println("[SERVER] Waited.");
						cfi.waitRequest();
						break;
					case ACCEPTED:
						System.out.println("[SERVER] Accepted");
						cfi.acceptedRequest();
						System.out.println("[SERVER] Game started");
						res = "Game started";
						break;
					case REJECTED:
						System.out.println("[SERVER] Rejected.");
						cfi.rejectedRequest();
						input.close();	
						output.close();
						connection.close();
						break;
					case ASSIGNMENT:
						System.out.println("[SERVER] You were assigned to this role: "+op.getMessage());
						controller.setInitialRole(op.getMessage());
						break;
					case BROADCAST:
						System.out.println("[SERVER] "+op.getMessage());
						res = op.getMessage();
						break;
					case ACK:
						if(op.getMessage() != null)
							System.out.println("[SERVER] "+op.getMessage());
						else
							System.out.println("[SERVER] Ack received.");
						
						res = op.getMessage();
						break;
					case NACK:
						if(op.getMessage() != null)
							System.out.println("[SERVER] "+op.getMessage());
						else
							System.out.println("[SERVER] Nack received.");
						break;
					default:
						throw new IOException();	
				}
			}else {
				throw new IOException();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public void scrivi(String role, String position) {
		try {
			System.out.println(role);
			if(role.equals("Portiere"))
				output.writeObject(new Message(Protocollo.SAVE, position));
			else
				output.writeObject(new Message(Protocollo.SHOOT, position));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}

