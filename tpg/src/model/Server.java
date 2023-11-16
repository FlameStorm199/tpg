package model;

import java.io.*; 
import java.net.*;

import control.ControllerFinestraIniziale; 

public class Server extends Thread { 
	
	private ServerSocket server; 
	private Socket richiestaClient;
	private boolean game_started;
	private Connessione connessione1;
	private Connessione connessione2;
	private Game game;
	
	public Server() { 
		//TODO Sistemare i println del server
		try { 
			server = new ServerSocket(20000, 2); 
			System.out.println("Server attivo");
			game_started = false;
			connessione1 = null;
			connessione2 = null;
			game = null;
			this.start();
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void run() { 
		try { 
			int checkConn = 0;
			Connessione[] connessioni = new Connessione[] {null, null};
			while (true) {
				/*if(game_started) {
					richiestaClient = server.accept();
					Connessione temp = new Connessione(richiestaClient, game);
					temp.connectionRejected();
				}*/
				
				System.out.println(checkConn);
				if(game == null)
					game = new Game();
				richiestaClient = server.accept();
				connessioni[checkConn] = new Connessione(richiestaClient, game);
				checkConn++;
				
				if(connessioni[0] != null && connessioni[1] == null) {
					connessioni[0].waitRequest();
				}else if(connessioni[0] == null && connessioni[1] != null) {
					connessioni[1].waitRequest();
				}else if(connessioni[0] != null && connessioni[1] != null) {
					connessioni[0].startGame();
					connessioni[1].startGame();
					game_started = true;
					
					while(true) {
						roleAssignment(connessioni[0], connessioni[1]);
						getShot(connessioni[0], connessioni[1]);
						if(game.ended())
							break; //partita finisce
						roleAssignment(connessioni[1], connessioni[0]);
						getShot(connessioni[1], connessioni[0]);
						if(game.ended())
							break; //partita finisce
					}
				}
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	public void roleAssignment(Connessione attaccante, Connessione portiere) {
		try {
			attaccante.getOutput().writeObject(new Message(Protocollo.ASSIGNMENT, "Attaccante"));
			Object o = attaccante.getInput().readObject();
			if(o instanceof Message) {
				Message mess = (Message)o;
				if(mess.getOp() == Protocollo.ACK) {
					System.out.println("OK");
				}else {
					System.out.println("NOT OK");
					//attaccante.connectionRejected();
					return;
				}
				
				portiere.getOutput().writeObject(new Message(Protocollo.ASSIGNMENT, "Portiere"));
				o = portiere.getInput().readObject();
				if(o instanceof Message) {
					mess = (Message)o;
					if(mess.getOp() == Protocollo.ACK) {
						System.out.println("OK");
					}else {
						System.out.println("NOT OK");
						portiere.connectionRejected();
						return;
					}
				}else {
					System.out.println("NOT OK");
					portiere.connectionRejected();
					return;
				}
			}else {
				System.out.println("NOT OK");
				attaccante.connectionRejected();
				return;
			}
		}catch (IOException e) { 
			e.printStackTrace(); 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getShot(Connessione attaccante, Connessione portiere) throws IOException {
		//TODO: Testare + Gestione errori ed eccezioni + Mutex + implementare wait turn?
		//TODO: Controllare che tutti i write e read siano ben sincronizzati per evitare il 2 starts incident
		attaccante.getOutput().writeObject(new Message(Protocollo.SERVER_READY));
		portiere.getOutput().writeObject(new Message(Protocollo.WAIT_TURN));
		
		attaccante.receiveInput();
		
		portiere.getOutput().writeObject(new Message(Protocollo.SERVER_READY));
		attaccante.getOutput().writeObject(new Message(Protocollo.WAIT_TURN));
		
		portiere.receiveInput();
		//attaccante.start();
		
	}
}