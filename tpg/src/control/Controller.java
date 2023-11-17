package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Client;
import view.Window;

public class Controller implements ActionListener, MouseListener, KeyListener{
	
	private Window v;
	private Client client;
	private boolean ready;
	private String role;
	
	public Controller(Window v, Client client) {
		this.v = v;
		this.client = client;
		v.registraEvento(this);
		ready = false;
		role = "Not assigned";
	}
	
	public void setRole(String role) {
		this.role = role;
		//TODO: Mostra sulla finestra il ruolo del giocatore
	}
	
	public void readyForShot() {
		ready = true;
	}
	
	public void flawedConnection(String error) {
		//TODO: Mostra in una label il messaggio di errore, avvisa che la connessione verrà chiusa
	}
	
	public void displayShotResult(String result) {
		//TODO: Mostra in una label questo messaggio, che mostra il risultato del tiro, un messaggio
		//di aspettare la parata (o il tiro), e/o un messaggio con il risultato finale della partita
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!ready) {
			//TODO: Avvisa (con un metodo che cambia una label) che non è il suo turno
			return;
		}
	
		if (e.getSource() == v.getA1()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "A1");
			//RMB: Metodo da richiamare solo se è portiere! Ricorda a celotto di aggiungere la palla per gli attaccanti!
			v.muoviPortiere("A1");
		}
		
		if (e.getSource() == v.getA2()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "A2");
			v.muoviPortiere("A2");
		}
		
		if (e.getSource() == v.getA3()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "A3");
			v.muoviPortiere("A3");
		}
		
		if (e.getSource() == v.getA4()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "A4");
			v.muoviPortiere("A4");
		}
		
		if (e.getSource() == v.getB1()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "B1");
			v.muoviPortiere("B1");
		}
		
		if (e.getSource() == v.getB2()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "B2");
			v.muoviPortiere("B2");
		}
		
		if (e.getSource() == v.getB3()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "B3");
			v.muoviPortiere("B3");
		}
		
		if (e.getSource() == v.getB4()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "B4");
			v.muoviPortiere("B4");
		}
		
		if (e.getSource() == v.getC1()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "C1");
			v.muoviPortiere("C1");
		}
		
		if (e.getSource() == v.getC2()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "C2");
			v.muoviPortiere("C2");
		}
		
		if (e.getSource() == v.getC3()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "C3");
			v.muoviPortiere("C3");
		}
		
		if (e.getSource() == v.getC4()) {
			//TODO: Implementare pulsante per chiudere la connessione
			client.sendShot(role, "C4");
			v.muoviPortiere("C4");
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
	
	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void actionPerformed(ActionEvent e) { }
}
