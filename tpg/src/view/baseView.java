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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import java.awt.event.MouseMotionAdapter;

public class baseView extends JFrame {

	private JPanel contentPane;
	private ImageIcon sfondo;
	private JLabel sfondoLabel;
	private JPanel panel;
	private JLabel A2;
	private JLabel A1;
	private JLabel B1;
	private JLabel D1;
	private JLabel C1;
	private JLabel B2;
	private JLabel B3;
	private JLabel C2;
	private JLabel D2;
	private JLabel A3;
	private JLabel C3;
	private JLabel D3;
	private JLabel portiereFermoLabel;
	private ImageIcon portiereFermo;

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
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		sfondoLabel = new JLabel("");
		sfondoLabel.setBounds(350, 10, 850, 781);
		sfondo = new ImageIcon(new ImageIcon("src/immagini/sfondo.png").getImage().getScaledInstance(880, 830, Image.SCALE_DEFAULT));
		sfondoLabel.setIcon(sfondo);
		
		
		portiereFermo=new ImageIcon(new ImageIcon("src/immagini/portiereFermo.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
		portiereFermoLabel=new JLabel("");
		portiereFermoLabel.setBounds(646, 53, 850, 781);
		portiereFermoLabel.setIcon(portiereFermo);
		contentPane.add(portiereFermoLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(104, 104, 104, 60));
		panel.setOpaque(true);
		panel.setBounds(455, 251, 670, 315);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 4, 0, 0));
		
		A1 = new JLabel("");
		A1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				A1.setOpaque(true);
				A1.setBackground(Color.YELLOW);
			}
		});
		A1.setHorizontalAlignment(SwingConstants.CENTER);
		A1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(A1);
		
		B1 = new JLabel("");
		B1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		B1.setBackground(new Color(255, 0, 0));
		B1.setForeground(new Color(0, 0, 0));
		B1.setHorizontalAlignment(SwingConstants.CENTER);
		B1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(B1);
		
		C1 = new JLabel("");
		C1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		C1.setBackground(new Color(255, 0, 0));
		C1.setForeground(new Color(0, 0, 0));
		C1.setHorizontalAlignment(SwingConstants.CENTER);
		C1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(C1);
		
		D1 = new JLabel("");
		D1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		D1.setBackground(new Color(255, 0, 0));
		D1.setForeground(new Color(0, 0, 0));
		D1.setHorizontalAlignment(SwingConstants.CENTER);
		D1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(D1);
		
		A2 = new JLabel("");
		A2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		A2.setBackground(new Color(255, 0, 0));
		A2.setForeground(new Color(0, 0, 0));
		A2.setHorizontalAlignment(SwingConstants.CENTER);
		A2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(A2);
		
		B2 = new JLabel("");
		B2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		B2.setBackground(new Color(255, 0, 0));
		B2.setForeground(new Color(0, 0, 0));
		B2.setHorizontalAlignment(SwingConstants.CENTER);
		B2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(B2);
		
		C2 = new JLabel("");
		C2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		C2.setBackground(new Color(255, 0, 0));
		C2.setForeground(new Color(0, 0, 0));
		C2.setHorizontalAlignment(SwingConstants.CENTER);
		C2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(C2);
		
		D2 = new JLabel("");
		D2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		D2.setBackground(new Color(255, 0, 0));
		D2.setForeground(new Color(0, 0, 0));
		D2.setHorizontalAlignment(SwingConstants.CENTER);
		D2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(D2);
		
		A3 = new JLabel("");
		A3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		A3.setBackground(new Color(255, 0, 0));
		A3.setForeground(new Color(0, 0, 0));
		A3.setHorizontalAlignment(SwingConstants.CENTER);
		A3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(A3);
		
		B3 = new JLabel("");
		B3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		B3.setBackground(new Color(255, 0, 0));
		B3.setForeground(new Color(0, 0, 0));
		B3.setHorizontalAlignment(SwingConstants.CENTER);
		B3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(B3);
		
		C3 = new JLabel("");
		C3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		C3.setBackground(new Color(255, 0, 0));
		C3.setForeground(new Color(0, 0, 0));
		C3.setHorizontalAlignment(SwingConstants.CENTER);
		C3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(C3);
		
		D3 = new JLabel("");
		D3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		D3.setBackground(new Color(255, 0, 0));
		D3.setForeground(new Color(0, 0, 0));
		D3.setHorizontalAlignment(SwingConstants.CENTER);
		D3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(D3);
		contentPane.add(sfondoLabel);
		
		setContentPane(contentPane);
	}
}
