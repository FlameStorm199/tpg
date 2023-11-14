package main;

import java.awt.EventQueue;

import control.baseControl;
import view.baseView;

public class MainProva {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					baseView frame = new baseView();
					baseControl controller=new baseControl(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
