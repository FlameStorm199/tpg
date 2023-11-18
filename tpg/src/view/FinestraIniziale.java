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
	private JLabel lblConnect;
	private JLabel titolo;
	private JLabel sfondoLabel;
	private ImageIcon sfondo;
	private JLabel lblConnessione;

	public FinestraIniziale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		sfondo = new ImageIcon(new ImageIcon("src/immagini/sfondoHome.jpg").getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT));
		
		lblConnessione = new JLabel("ciao");
		lblConnessione.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnessione.setForeground(Color.WHITE);
		lblConnessione.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		lblConnessione.setBounds(208, 611, 1124, 41);
		contentPane.add(lblConnessione);
		
		lblConnect = new JLabel("Connect");
		lblConnect.setForeground(new Color(255, 255, 255));
		lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnect.setFont(new Font("Arcade Normal", Font.PLAIN, 20));
		lblConnect.setBounds(684, 422, 172, 41);
		
		contentPane.add(lblConnect);
		
		textField = new JTextField("25.15.122.173");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		textField.setBounds(638, 332, 264, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		titolo = new JLabel("THE PENALTY GAME");
		titolo.setForeground(new Color(255, 255, 255));
		titolo.setFont(new Font("Arcade Normal", Font.PLAIN, 50));
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setBounds(367, 180, 805, 110);
		contentPane.add(titolo);
		
		setContentPane(contentPane);
		
		sfondoLabel = new JLabel("");
		sfondoLabel.setBounds(0, 0, 1600, 900);
		sfondoLabel.setIcon(sfondo);
		contentPane.add(sfondoLabel);
	}
	
	public void setLblConnessione(String conn) {
		System.out.println("AAA");
		lblConnessione.setText(conn);
		lblConnessione.setVisible(true);
	}

	public void registraEvento(ControllerFinestraIniziale fi) {
		textField.addActionListener(fi);
		lblConnect.addMouseListener(fi);
	}
	
	public String getIP() {
		return textField.getText();
	}
	
	public JLabel getLblConnect() {
		return lblConnect;
	}
	
	public void cambia(String text) {
		titolo.setText(text);
	}
}
