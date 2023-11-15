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
	
	public Server() { 
		//TODO Sistemare i println del server
		try { 
			server = new ServerSocket(20000, 5); 
			System.out.println("Server attivo");
			game_started = false;
			connessione1 = null;
			connessione2 = null;
			this.start();
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void run() { 
		try { 
			while (true) {
				//TODO: CONTROLLARE CHE CI SIANO 2 CLIENT CONNESSI PRIMA DI COMINCIARE LA PARTITA
				//TODO: ASSEGNARE RUOLI
				if(game_started) {
					richiestaClient = server.accept();
					Connessione temp = new Connessione(richiestaClient);
					temp.connectionRejected();
				}
				
				if(this.connessione1 == null) {
					richiestaClient = server.accept();
					connessione1 = new Connessione(richiestaClient);
				}else if(this.connessione2 == null) {
					richiestaClient = server.accept();
					connessione2 = new Connessione(richiestaClient);
				}
				
				if(connessione1 != null || connessione2 == null) {
					connessione1.waitRequest();
				}else if(connessione1 == null || connessione2 != null) {
					connessione2.waitRequest();
				}else if(connessione1 != null || connessione2 != null) {
					connessione1.startGame();
					connessione2.startGame();
					game_started = true;
				}
				
				/*if(this.checkConnections == 1) {
					//start game
					
					System.out.println("Accepted");
					checkConnections++;
					richiestaClient = server.accept();
					new Connessione(richiestaClient);
				}else if(this.checkConnections == 0) {
					System.out.println("Waited");
					checkConnections++;
					richiestaClient = server.accept();
					new Connessione(richiestaClient);
					
				}else {
					System.out.println("Rejected");
				}*/
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}

	/*public int getCheckConnections() {
		return checkConnections;
	}

	public void setCheckConnections(int checkConnections) {
		this.checkConnections = checkConnections;
	}*/
}
