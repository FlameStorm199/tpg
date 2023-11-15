package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControllerFinestraIniziale;

import javax.swing.JTextField;
import javax.swing.JButton;

public class FinestraIniziale extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	
	/**
	 * Create the frame.
	 */
	public FinestraIniziale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnNewButton = new JButton("Connect");
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	public void registraEvento(ControllerFinestraIniziale fi) {
		textField.addActionListener(fi);
		btnNewButton.addActionListener(fi);
	}
	
	public String getIP() {
		return textField.getText();
	}
	
	public JButton getButton() {
		return btnNewButton;
	}
	
}
