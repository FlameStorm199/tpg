package main;

import java.awt.EventQueue;

import control.Controller;
import control.ControllerFinestraIniziale;
import model.Client;
import view.FinestraIniziale;
import view.Window;

public class MainClient {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraIniziale frame = new FinestraIniziale();
					ControllerFinestraIniziale controller = new ControllerFinestraIniziale(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
