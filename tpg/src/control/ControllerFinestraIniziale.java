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
	
	public ControllerFinestraIniziale(FinestraIniziale fi) {
		this.fi=fi;
		fi.registraEvento(this);
	}
	
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fi.getButton()) {
			System.out.println("IP: "+fi.getIP());
			this.client = new Client(fi.getIP(), this);
		}
	}*/
	
	public void acceptedRequest() {
		fi.dispose();
        Client c = this.client;
        Controller controller = new Controller(null, c);
        client.setController(controller);
        Window frame = new Window();
        controller.setWindow(frame);
        frame.setVisible(true);
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Window frame = new Window();
//                    controller.setWindow(frame);
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
	
	public void waitRequest() {
		System.out.println("The connection to the server was succesful, wait for another player to connect...");
	}
	
	public void rejectedRequest() {
		System.out.println("The server rejected your connection request. This usually happens because your request would exceed the maximum number of clients connected to that server.");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fi.getLblConnect()) {
			System.out.println("IP: "+fi.getIP());
			this.client = new Client(fi.getIP(), this);
			client.configureClient();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
