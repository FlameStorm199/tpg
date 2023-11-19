package model;

import java.io.*; 
import java.net.*; 

public class Server extends Thread { 
	
	private ServerSocket server; 
	private Socket clientRequest;
	private boolean game_started;
	private Game game;
	private Shot currentShot;
	private String broadcastMessage;
	
	public Server() { 
		try { 
			currentShot = null;
			server = new ServerSocket(20000, 2); 
			System.out.println("The server is up and running");
			game_started = false;
			game = null;
			broadcastMessage = null;
			this.start();
		} 
		catch (IOException e) {
			System.out.println("An IOException has been thrown while initializing the server. The program will be terminated.");
			//e.printStackTrace(); 
		} 
	}
	
	public void run() { 
		try {
			int connectionIndex = 0;
			Connection[] connections = new Connection[] {null, null};

			while (true) {
				if(game_started) {
					clientRequest = server.accept();
					Connection temp = new Connection(clientRequest, game, this);
					temp.connectionRejected();
				}
				
				if(game == null)
					game = new Game();
				clientRequest = server.accept();
				connections[connectionIndex] = new Connection(clientRequest, game, this);
				connectionIndex++;
				
				if(connections[0] != null && connections[1] == null) {
					connections[0].waitRequest();
				}else if(connections[0] == null && connections[1] != null) {
					connections[1].waitRequest();
				}else if(connections[0] != null && connections[1] != null) {
					connections[0].startGame(0);
					connections[1].startGame(1);
					game_started = true;
					new Thread(connections[0]).start();
					new Thread(connections[1]).start();
				}
			}
		}catch (IOException e) { 
			System.out.println("An IOException has been thrown while the server was running. The program will be terminated.");
			//e.printStackTrace(); 
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Shot getCurrentShot() {
		return currentShot;
	}

	public void setCurrentShot(Shot currentShot) {
		this.currentShot = currentShot;
	}

	public String getBroadcastMessage() {
		return broadcastMessage;
	}

	public void setBroadcastMessage(String broadcastMessage) {
		this.broadcastMessage = broadcastMessage;
	}
	
	
}