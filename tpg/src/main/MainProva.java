package main;

import java.awt.EventQueue;

import control.ControllerFinestraIniziale;
import control.ControllerProva;
import view.FinestraIniziale;
import view.Window;

public class MainProva {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					ControllerProva controller = new ControllerProva(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
