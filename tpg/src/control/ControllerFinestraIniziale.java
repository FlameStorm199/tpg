package control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Client;
import view.FinestraIniziale;
import view.Window;

public class ControllerFinestraIniziale implements ActionListener, MouseListener{
	private FinestraIniziale fi;
	private Client client;
	private Window frame;
	
	public ControllerFinestraIniziale(FinestraIniziale fi) {
		this.fi=fi;
		fi.registraEvento(this);
		this.frame = new Window();
		frame.setVisible(false);
	}
	
	public void acceptedRequest() {
		fi.dispose();
		Client c = this.client;
		Controller controller = new Controller(null, c);
		client.setController(controller);
		controller.setWindow(frame);
		frame.setVisible(true);
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
	
	public void waitRequest() {
		fi.setLblConnessione("The connection to the server was succesful, wait for another player to connect...");
	}
	
	public void rejectedRequest() {
		fi.setLblConnessione("The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == fi.getLblConnect()) {
			System.out.println("IP: "+fi.getIP());
			this.client = new Client(fi.getIP(), this);
			String mess = "";
			while(!mess.equals("Game started"))
				mess = client.leggi();
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
