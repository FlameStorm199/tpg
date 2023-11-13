package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.CardLayout;

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
		contentPane.setLayout(null);
		
		sfondoLabel = new JLabel("");
		sfondoLabel.setBounds(350, 10, 850, 781);
		sfondo = new ImageIcon(new ImageIcon("src/immagini/sfondo.png").getImage().getScaledInstance(880, 830, Image.SCALE_DEFAULT));
		sfondoLabel.setIcon(sfondo);
		contentPane.add(sfondoLabel);
		
		setContentPane(contentPane);
	}

}
