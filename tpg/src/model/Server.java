package model;

import java.io.*; 
import java.net.*; 

public class Server extends Thread { 
	
	private ServerSocket server; 
	private Socket richiestaClient;
	
	public Server() { 
		try { 
			server = new ServerSocket(20000, 5); 
			System.out.println("Server attivo"); 
			this.start(); } 
		catch (IOException e) { e.printStackTrace(); 
		} 
	}
	
	public void run() { 
		try { 
			while (true) {
				//TODO: CONTROLLARE CHE CI SIANO 2 CLIENT CONNESSI PRIMA DI COMINCIARE LA PARTITA
				//TODO: ASSEGNARE RUOLI
				richiestaClient = server.accept();  
				//new Connessione(richiestaClient); 
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
}
