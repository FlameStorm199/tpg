package model;

import java.io.*; 
import java.net.*;

import control.ControllerFinestraIniziale; 

public class Server extends Thread { 
	
	private ServerSocket server; 
	private Socket richiestaClient;
	private boolean game_started;
	public Game game;
	public Shot currentShot;
	public String broadcastMessage;
	
	public Server() { 
		//TODO Sistemare i println del server
		try { 
			currentShot = null;
			server = new ServerSocket(20000, 2); 
			System.out.println("Server attivo");
			game_started = false;
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
				if(game_started) {
					richiestaClient = server.accept();
					Connessione temp = new Connessione(richiestaClient, game, this);
					temp.connectionRejected();
				}
				
				System.out.println(checkConn);
				if(game == null)
					game = new Game();
				richiestaClient = server.accept();
				connessioni[checkConn] = new Connessione(richiestaClient, game, this);
				checkConn++;
				
				if(connessioni[0] != null && connessioni[1] == null) {
					connessioni[0].waitRequest();
				}else if(connessioni[0] == null && connessioni[1] != null) {
					connessioni[1].waitRequest();
				}else if(connessioni[0] != null && connessioni[1] != null) {
					connessioni[0].startGame(0);
					connessioni[1].startGame(1);
					game_started = true;
					new Thread(connessioni[0]).start();
					new Thread(connessioni[1]).start();
				}
			}
		}catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
}