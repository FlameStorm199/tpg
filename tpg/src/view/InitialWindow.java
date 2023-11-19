package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.InitialWindowController;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.UIManager;

public class InitialWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblConnect;
	private JLabel title;
	private JLabel backgroundLabel;
	private ImageIcon background;
	private JLabel lblConnection;

	public InitialWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		try {
		    //create the font to use. Specify the size!
		    Font arcade = Font.createFont(Font.TRUETYPE_FONT, new File("resources/ARCADE_N.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(arcade);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		background = new ImageIcon(new ImageIcon("src/immagini/sfondoHome.jpg").getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT));
		
		lblConnection = new JLabel("");
		lblConnection.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnection.setForeground(Color.WHITE);
		lblConnection.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		lblConnection.setBounds(208, 611, 1124, 41);
		contentPane.add(lblConnection);
		
		lblConnect = new JLabel("Connettiti al server");
		lblConnect.setForeground(new Color(255, 255, 255));
		lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnect.setFont(new Font("Arcade Normal", Font.PLAIN, 20));
		lblConnect.setBounds(524, 429, 490, 41);
		
		contentPane.add(lblConnect);
		
		textField = new JTextField("25.15.122.173");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		textField.setBounds(638, 332, 264, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		title = new JLabel("THE PENALTY GAME");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Arcade Normal", Font.PLAIN, 50));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(367, 180, 805, 110);
		contentPane.add(title);
		
		setContentPane(contentPane);
		
		backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 1600, 900);
		backgroundLabel.setIcon(background);
		contentPane.add(backgroundLabel);
	}
	
	public void setLblConnection(String conn) {
		lblConnection.setText(conn);
		lblConnection.setVisible(true);
	}

	public void recordEvent(InitialWindowController fi) {
		textField.addActionListener(fi);
		lblConnect.addMouseListener(fi);
	}
	
	public String getIP() {
		if(validateIPAddress(textField.getText()))
			return textField.getText();
		setLblConnection("L'indirizzo inserito non è un indirizzo IP valido.");
		return null;
	}
	
	public boolean validateIPAddress(String address) {
		String condition = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
		Pattern p = Pattern.compile(condition);
		Matcher m = p.matcher(address);
		return m.matches();
	}
	
	public JLabel getLblConnect() {
		return lblConnect;
	}
}
