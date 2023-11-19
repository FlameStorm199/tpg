package control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Client;
import view.InitialWindow;
import view.MainWindow;

public class InitialWindowController implements ActionListener, MouseListener{
	private InitialWindow fi;
	private Client client;
	private MainWindow frame;
	private boolean waiting;
	
	public InitialWindowController(InitialWindow fi) {
		this.fi=fi;
		fi.recordEvent(this);
		this.frame = new MainWindow();
		frame.setVisible(false);
		waiting = false;
	}
	
	public void acceptedRequest() {
		fi.dispose();
		Client c = this.client;
		Controller controller = new Controller(null, c);
		client.setController(controller);
		controller.setWindow(frame);
		frame.setVisible(true);
	}
	
	public void waitRequest() {
		waiting = true;
		fi.setLblConnection("La connessione al server è avvenuta con successo, in attesa di un altro giocatore...");
	}
	
	public void rejectedRequest() {
		fi.setLblConnection("La connessione al server è stata rifiutata oppure è fallita.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(waiting)
			return;
		
		if(e.getSource() == fi.getLblConnect()) {
			if(fi.getIP() == null)
				return;
			this.client = new Client(fi.getIP(), this);
			String mess = "";
			mess = client.read();
			if(!mess.equals("Game started")) {
				try {
					System.out.println("Entra");
					fi.setLblConnection("La connessione al server è avvenuta con successo, in attesa di un altro giocatore...");
					System.out.println("Entra x2");
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				client.read();
			}
			/*while(!mess.equals("Game started")) {
				try {
					fi.setLblConnection("La connessione al server è avvenuta con successo, in attesa di un altro giocatore...");
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				mess = client.read();
			}*/
				
		}
	}

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
