package main;

import java.awt.EventQueue;

import control.Controller;
import control.InitialWindowController;
import model.Client;
import view.InitialWindow;
import view.MainWindow;

public class MainClient {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialWindow frame = new InitialWindow();
					InitialWindowController controller = new InitialWindowController(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("An error occurred while loading the client program. The program will be terminated.");
					//e.printStackTrace();
				}
			}
		});
	}
}
