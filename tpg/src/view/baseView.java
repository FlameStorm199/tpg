package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class baseView extends JFrame {

	private JPanel contentPane;
	private ImageIcon sfondo;
	private JLabel sfondoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					baseView frame = new baseView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public baseView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Image image = null;
	    try {
	        image = ImageIO.read(new File("src/immagini/sfondo.png")).getScaledInstance(800, 800, Image.SCALE_SMOOTH);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    JLabel sfondoLabel = new JLabel(new ImageIcon(image));
		contentPane.add(sfondoLabel);
		
		setContentPane(contentPane);
	}

}
