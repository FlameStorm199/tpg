package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControllerFinestraIniziale;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class FinestraIniziale extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnConnect;
	private JLabel titolo;
	private JLabel sfondoLabel;
	private ImageIcon sfondo;
	
	/**
	 * Create the frame.
	 */
	public FinestraIniziale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		sfondo = new ImageIcon(new ImageIcon("src/immagini/sfondoHome.jpg").getImage().getScaledInstance(1600, 800, Image.SCALE_DEFAULT));
		
		textField = new JTextField();
		textField.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		textField.setBounds(638, 301, 264, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Arcade Normal", Font.PLAIN, 10));
		btnConnect.setBounds(684, 395, 172, 41);
	    btnConnect.setContentAreaFilled(true);
	    btnConnect.setBorderPainted(false);
	    btnConnect.setFocusPainted(false);
	    
		contentPane.add(btnConnect);
		
		titolo = new JLabel("THE PENALTY GAME");
		titolo.setForeground(new Color(255, 255, 255));
		titolo.setFont(new Font("Arcade Normal", Font.PLAIN, 50));
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setBounds(367, 180, 805, 110);
		contentPane.add(titolo);
		
		setContentPane(contentPane);
		
		sfondoLabel = new JLabel("");
		sfondoLabel.setBounds(0, 10, 1600, 800);
		sfondoLabel.setIcon(sfondo);
		contentPane.add(sfondoLabel);
	}
	
	public void registraEvento(ControllerFinestraIniziale fi) {
		textField.addActionListener(fi);
		btnConnect.addActionListener(fi);
	}
	
	public String getIP() {
		return textField.getText();
	}
	
	public JButton getButton() {
		return btnConnect;
	}
}
