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
	private boolean stop;
	private String role;
	
	public Controller(Window v, Client client) {
		this.v = v;
		this.client = client;
		stop = false;
		role = "Not assigned";
	}
	
	public void setInitialRole(String role) {
		this.role = role;
		mostraRuolo();
	}
	
	public void mostraRuolo() {
		v.setLblRuolo(role);
	}
	
	public void displayShotResult(String result) {
		//TODO: Mostra in una label questo messaggio, che mostra il risultato del tiro, un messaggio
		//di aspettare la parata (o il tiro), e/o un messaggio con il risultato finale della partita
		
		String posizionePalla = String.valueOf(result.charAt(0)) + String.valueOf(result.charAt(1));
		System.out.println("Posizione palla: "+posizionePalla);
		String posizionePortiere = String.valueOf(result.charAt(2)) + String.valueOf(result.charAt(3));
		System.out.println("Posizione portiere: "+posizionePortiere);
		String esito = result.substring(4);
		System.out.println("Esito: "+esito);
		v.modificaGrafica(posizionePortiere, posizionePalla, esito);
	}
	
	public void waitForTurn(String mess) {
		v.setLblInputRicevuto(mess);
	}
	
	public void gestisciInput(String position) {
		client.scrivi(this.role, position);
		stop = true;
		String mess = client.leggi();
		if(mess.equals("Save received, waiting for shot...") || mess.equals("Shot received, waiting for save...")) {
			waitForTurn(mess);
			mess = client.leggi();
		}
			
		displayShotResult(mess);
		if(this.role.equals("Portiere"))
			this.role = "Attaccante";
		else
			this.role = "Portiere";
		mostraRuolo();
		stop = false;
	}

	@Override
    public void mouseClicked(MouseEvent e) {
        if(stop) {
        	v.stampaAttesa();
            return;
        }

        if (e.getSource() == v.getA1()) {
            gestisciInput("A1");
        }

        if (e.getSource() == v.getA2()) {
            gestisciInput("A2");
        }

        if (e.getSource() == v.getA3()) {
            gestisciInput("A3");
        }

        if (e.getSource() == v.getA4()) {
            gestisciInput("A4");
        }

        if (e.getSource() == v.getB1()) {
            gestisciInput("B1");
        }

        if (e.getSource() == v.getB2()) {
            gestisciInput("B2");
        }

        if (e.getSource() == v.getB3()) {
            gestisciInput("B3");
        }
        
        if (e.getSource() == v.getB4()) {
            gestisciInput("B4");
        }

        if (e.getSource() == v.getC1()) {
            gestisciInput("C1");
        }

        if (e.getSource() == v.getC2()) {
            gestisciInput("C2");
        }

        if (e.getSource() == v.getC3()) {
            gestisciInput("C3");
        }

        if (e.getSource() == v.getC4()) {
            gestisciInput("C4");
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

	public void setWindow(Window frame) {
		// TODO Auto-generated method stub
		this.v = frame;
		v.registraEvento(this);
		this.client.leggi();
	}
}
