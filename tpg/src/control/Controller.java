package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Client;
import view.MainWindow;

public class Controller implements ActionListener, MouseListener, KeyListener{
	
	private MainWindow v;
	private Client client;
	private boolean stop;
	private String role;
	private String initialRole;
	
	public Controller(MainWindow v, Client client) {
		this.v = v;
		this.client = client;
		stop = false;
		role = "Not assigned";
		initialRole = "Not assigned";
	}
	
	public void setInitialRole(String role) {
		this.role = role;
		this.initialRole = role;
		showRole();
	}
	
	public void showRole() {
		v.setLblRole(role);
	}
	
	public void displayShotResult(String result) {
		String[] messages = result.split("@");
		
		String ballPosition = messages[0];
		System.out.println("Posizione palla: "+ballPosition);
		String gkPosition = messages[1];
		System.out.println("Posizione portiere: "+gkPosition);
		String shotNumber = messages[2];
		System.out.println("Entrambi i giocatori hanno effettuato "+shotNumber+" tiri.");
		String playerGoals = messages[3];
		System.out.println("Goal giocatore: "+playerGoals);
		String opponentGoals = messages[4];
		System.out.println("Goal avversario: "+opponentGoals);
		String shotResult = messages[5];
		System.out.println("Esito: "+shotResult);
		v.editGraphics(gkPosition, ballPosition, shotResult);
	}
	
	public void waitForTurn(String mess) {
		if(mess.equals("Save received, waiting for shot..."))
			mess = "Parata ricevuta, in attesa del tiro...";
		else
			mess = "Tiro ricevuto, in attesa della parata...";
		v.setLblInputReceived(mess);
	}
	
	public void manageInput(String position) {
		client.sendInput(this.role, position);
		stop = true;
		String mess = client.read();
		if(mess.equals("Save received, waiting for shot...") || mess.equals("Shot received, waiting for save...")) {
			waitForTurn(mess);
			mess = client.read();
		}
			
		displayShotResult(mess);
		mess = client.read();
		if(mess.equals("Game ended")) {
			boolean ok = v.askForNewGame();
			if(ok) {
				client.newGame();
				this.role = this.initialRole;
				showRole();
			}else {
				v.dispose();
				client.closeConnection();
				System.exit(0);
			}
		}
		
		if(this.role.equals("Portiere"))
			this.role = "Attaccante";
		else
			this.role = "Portiere";
		showRole();
		stop = false;
	}

	@Override
    public void mouseClicked(MouseEvent e) {
        if(stop) {
        	v.printWaitMessage();
            return;
        }

        if (e.getSource() == v.getA1()) {
            manageInput("A1");
        }

        if (e.getSource() == v.getA2()) {
            manageInput("A2");
        }

        if (e.getSource() == v.getA3()) {
            manageInput("A3");
        }

        if (e.getSource() == v.getA4()) {
            manageInput("A4");
        }

        if (e.getSource() == v.getB1()) {
            manageInput("B1");
        }

        if (e.getSource() == v.getB2()) {
            manageInput("B2");
        }

        if (e.getSource() == v.getB3()) {
            manageInput("B3");
        }
        
        if (e.getSource() == v.getB4()) {
            manageInput("B4");
        }

        if (e.getSource() == v.getC1()) {
            manageInput("C1");
        }

        if (e.getSource() == v.getC2()) {
            manageInput("C2");
        }

        if (e.getSource() == v.getC3()) {
            manageInput("C3");
        }

        if (e.getSource() == v.getC4()) {
            manageInput("C4");
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == v.getBtnCloseConnection()) {
			boolean ok = v.closeConnection();
			if(ok) {
				v.dispose();
				client.closeConnection();
				System.exit(0);
			}
			
		}
	}

	public void setWindow(MainWindow frame) {
		this.v = frame;
		v.recordEvent(this);
		this.client.read();
	}
}
