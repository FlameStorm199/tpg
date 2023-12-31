package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Client;
import view.MainWindow;

public class Controller implements ActionListener, MouseListener, KeyListener, WindowListener{
	
	private MainWindow v;
	private Client client;
	private boolean stop;
	private String role;
	private String initialRole;
	private String position;
	
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
		if(this.role.equals("Portiere"))
			v.setLblInputReceived("Scegli un punto in cui tentare la parata");
		else
			v.setLblInputReceived("Scegli un punto in cui effettuare il tiro");
		stop = false;
	}
	
	public void displayShotResult(String result) {
		String[] messages = result.split("@");
		
		String ballPosition = messages[0];
		String gkPosition = messages[1];
		//String shotNumber = messages[2];
		String homeGoals = messages[3];
		String awayGoals = messages[4];
		String shotResult = messages[5];
		v.editGraphics(gkPosition, ballPosition, shotResult);
		v.editScore(homeGoals, awayGoals);
	}
	
	public void waitForTurn(String mess) {
		if(mess.equals("Save received, waiting for shot..."))
			mess = "Parata ricevuta, in attesa del tiro...";
		else
			mess = "Tiro ricevuto, in attesa della parata...";
		v.setLblInputReceived(mess);
	}
	
	public void askForNewGame() {
		boolean ok = v.askForNewGame();
		if(ok) {
			v.editScore("0","0");
			client.newGame();
			this.role = this.initialRole;
			showRole();
		}else {
			v.dispose();
			client.closeConnection();
			System.exit(0);
		}
	}
	
	public void changeRole() {
		if(this.role.equals("Portiere"))
			this.role = "Attaccante";
		else
			this.role = "Portiere";
	}
	
	public void manageInput(String position) {
		this.position = position;
		stop = true;
		client.sendInput();
	}
	
	public void opponentDisconnected() {
		v.opponentDisconnected();
		v.dispose();
		System.exit(0);
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
	
	@Override
	public void windowClosing(WindowEvent e) {
		boolean ok = v.closeConnection();
		if(ok) {
			v.dispose();
			client.closeConnection();
			System.exit(0);
		}
	}

	public void setWindow(MainWindow frame) {
		this.v = frame;
		v.recordEvent(this);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
	public void windowOpened(WindowEvent e) { }

	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }
}
