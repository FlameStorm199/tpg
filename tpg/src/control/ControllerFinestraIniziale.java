package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Client;
import view.FinestraIniziale;
import view.Window;

public class ControllerFinestraIniziale implements ActionListener{
	private FinestraIniziale fi;
	private Client client;
	
	public ControllerFinestraIniziale(FinestraIniziale fi) {
		this.fi=fi;
		fi.registraEvento(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fi.getButton()) {
			System.out.println("IP: "+fi.getIP());
			this.client = new Client(fi.getIP(), this);
		}
	}
	
	public void acceptedRequest() {
		Window frame = new Window();
		Controller controller = new Controller(frame, this.client);
		client.setController(controller);
		frame.setVisible(true);
	}
	
	public void waitRequest() {
		System.out.println("The connection to the server was succesful, wait for another player to connect...");
	}
	
	public void rejectedRequest() {
		System.out.println("The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
	}
}
