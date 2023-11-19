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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import control.Controller;
import control.InitialWindowController;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

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
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane=new JPanel();
	private ImageIcon background;
	private JLabel backgroundLabel;
	private JPanel panel;
	private JLabel A1, B1, C1, A2, B2, C2, A3, B3, C3, A4, B4, C4;
	private JLabel preparedGKLabel;
	private ImageIcon preparedGK;
	private BufferedImage divingGK;
	private JLabel divingGKLabel;
	private BufferedImage football;
	private JLabel footballLabel;
	private JLabel lblRole;
	private JButton btnCloseConnection;
	private JLabel lblInputReceived;
	private JPanel panelScore;
	private JLabel lblH;
	private JLabel lblN1;
	private JLabel lblN2;
	private JLabel lblA;
	private JLabel lblDuePunti;
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setIconImage(new ImageIcon("src/immagini/palla.png").getImage());
		
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
		
		backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(345, 0, 850, 781);
		background = new ImageIcon(new ImageIcon("src/immagini/sfondo.png").getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT));
		backgroundLabel.setIcon(background);
		
		preparedGK=new ImageIcon(new ImageIcon("src/immagini/portiereFermo.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
		
		divingGKLabel=new JLabel("");
		
		lblDuePunti = new JLabel(":");
		lblDuePunti.setForeground(Color.BLACK);
		lblDuePunti.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuePunti.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		lblDuePunti.setBounds(751, 74, 34, 13);
		contentPane.add(lblDuePunti);
		
		btnCloseConnection = new JButton("CHIUDI CONNESSIONE");
		btnCloseConnection.setFont(new Font("Arcade Normal", Font.PLAIN, 10));
		btnCloseConnection.setBounds(965, 58, 207, 32);
		contentPane.add(btnCloseConnection);
		
		panelScore = new JPanel();
		panelScore.setBounds(658, 58, 220, 46);
		contentPane.add(panelScore);
		panelScore.setLayout(new GridLayout(0, 4, 0, 0));
		panelScore.setBorder(BorderFactory.createLineBorder(Color.black));
		
		lblH = new JLabel("H");
		lblH.setForeground(Color.BLACK);
		lblH.setHorizontalAlignment(SwingConstants.CENTER);
		lblH.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		lblH.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		panelScore.add(lblH);
		
		lblN1 = new JLabel("0");
		lblN1.setForeground(Color.BLACK);
		lblN1.setHorizontalAlignment(SwingConstants.CENTER);
		lblN1.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		panelScore.add(lblN1);
		
		lblN2 = new JLabel("0");
		lblN2.setForeground(Color.BLACK);
		lblN2.setHorizontalAlignment(SwingConstants.CENTER);
		lblN2.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		panelScore.add(lblN2);
		
		lblA = new JLabel("A");
		lblA.setForeground(Color.BLACK);
		lblA.setFont(new Font("Arcade Normal", Font.PLAIN, 15));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panelScore.add(lblA);
		
		lblRole = new JLabel("RUOLO");
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setBackground(Color.WHITE);
		lblRole.setForeground(SystemColor.BLACK);
		lblRole.setFont(new Font("Arcade Normal", Font.PLAIN, 20));
		lblRole.setOpaque(true);
		lblRole.setBorder(BorderFactory.createLineBorder(Color.black));
		lblRole.setBounds(357, 58, 207, 46);
		contentPane.add(lblRole);
		
		lblInputReceived = new JLabel("Input ricevuto");
		lblInputReceived.setOpaque(true);
		lblInputReceived.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputReceived.setForeground(Color.BLACK);
		lblInputReceived.setFont(new Font("Arcade Normal", Font.PLAIN, 10));
		lblInputReceived.setBorder(BorderFactory.createLineBorder(Color.black));
		lblInputReceived.setBackground(Color.WHITE);
		lblInputReceived.setBounds(507, 713, 525, 46);
		lblInputReceived.setVisible(false);
		contentPane.add(lblInputReceived);
		footballLabel=new JLabel("");
		footballLabel.setLocation(35, 90);
		footballLabel.setSize(60, 60);
		contentPane.add(footballLabel);
		contentPane.add(divingGKLabel);
		
		preparedGKLabel=new JLabel("");
		preparedGKLabel.setBounds(620, 278, 300, 300);
		preparedGKLabel.setIcon(preparedGK);
		contentPane.add(preparedGKLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(104, 104, 104, 60));
		panel.setOpaque(true);
		panel.setBounds(447, 245, 646, 307);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 4, 0, 0));
		
		A1 = new JLabel("");
		A1.setHorizontalAlignment(SwingConstants.CENTER);
		A1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(A1);
		
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
		
		A4 = new JLabel("");
		A4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		A4.setBackground(new Color(255, 0, 0));
		A4.setForeground(new Color(0, 0, 0));
		A4.setHorizontalAlignment(SwingConstants.CENTER);
		A4.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(A4);
		
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
		
		B4 = new JLabel("");
		B4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		B4.setBackground(new Color(255, 0, 0));
		B4.setForeground(new Color(0, 0, 0));
		B4.setHorizontalAlignment(SwingConstants.CENTER);
		B4.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(B4);
		
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
		
		C4 = new JLabel("");
		C4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		C4.setBackground(new Color(255, 0, 0));
		C4.setForeground(new Color(0, 0, 0));
		C4.setHorizontalAlignment(SwingConstants.CENTER);
		C4.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(C4);
		contentPane.add(backgroundLabel);
		setContentPane(contentPane);
	}
	
	public void recordEvent(Controller controller) {
		A1.addMouseListener(controller);
		A2.addMouseListener(controller);
		A3.addMouseListener(controller);
		A4.addMouseListener(controller);
		B1.addMouseListener(controller);
		B2.addMouseListener(controller);
		B3.addMouseListener(controller);
		B4.addMouseListener(controller);
		C1.addMouseListener(controller);
		C2.addMouseListener(controller);
		C3.addMouseListener(controller);
		C4.addMouseListener(controller);
		btnCloseConnection.addActionListener(controller);
	}

	public JLabel getA1() {
		return A1;
	}

	public JLabel getB1() {
		return B1;
	}

	public JLabel getC1() {
		return C1;
	}

	public JLabel getA2() {
		return A2;
	}

	public JLabel getB2() {
		return B2;
	}

	public JLabel getC2() {
		return C2;
	}

	public JLabel getA3() {
		return A3;
	}

	public JLabel getB3() {
		return B3;
	}

	public JLabel getC3() {
		return C3;
	}

	public JLabel getA4() {
		return A4;
	}

	public JLabel getB4() {
		return B4;
	}

	public JLabel getC4() {
		return C4;
	}

	public void editGraphics(String gkPosition, String ballPosition, String message) {
		preparedGKLabel.setVisible(false);
		footballLabel.setVisible(true);
		divingGKLabel.setVisible(true);
		try {
			divingGK = ImageIO.read(new File("resources/portiereTuffo.png"));
		} catch (IOException e) {
			System.out.println("There was an error while loading the goalkeeper image. The program will be terminated.");
			System.exit(0);
			//e.printStackTrace();
		}
		try {
			football = ImageIO.read(new File("resources/palla.png"));
		} catch (IOException e) {
			System.out.println("There was an error while loading the football image. The program will be terminated.");
			System.exit(0);
			//e.printStackTrace();
		}
		
		editGK(gkPosition);
		editFootball(ballPosition);
		
		showShotResult(message);
	}
	
	private void showShotResult(String shotResult) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arcade Normal", Font.PLAIN, 14)));
		JOptionPane.showConfirmDialog(contentPane, shotResult, "", JOptionPane.PLAIN_MESSAGE);
		preparedGKLabel.setVisible(true);
		footballLabel.setVisible(false);
		divingGKLabel.setVisible(false);
	}

	private void editGK(String pos) {
		switch(pos){
			case "A1":
				divingGK=rotate(divingGK, -10);
				divingGKLabel.setBounds(440, 110, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(355, 355, Image.SCALE_DEFAULT)));
				break;
			case "A2":
				divingGK=rotate(divingGK, 38);
				divingGKLabel.setBounds(480, 108, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(415, 415, Image.SCALE_DEFAULT)));
				break;
			case "A3":
				divingGK=rotate(divingGK, 45);
				divingGKLabel.setBounds(650, 108, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(415, 415, Image.SCALE_DEFAULT)));
				break;
			case "A4":
				divingGK=rotate(divingGK, 105);
				divingGKLabel.setBounds(745, 110, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(365, 365, Image.SCALE_DEFAULT)));
				break;
			case "B1":
				divingGK=rotate(divingGK, -40);
				divingGKLabel.setBounds(410, 175, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(435, 435, Image.SCALE_DEFAULT)));
				break;
			case "B2":
				divingGK=rotate(divingGK, 38);
				divingGKLabel.setBounds(480, 180, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(415, 415, Image.SCALE_DEFAULT)));
				break;
			case "B3":
				divingGK=rotate(divingGK, 45);
				divingGKLabel.setBounds(650, 180, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(415, 415, Image.SCALE_DEFAULT)));
				break;
			case "B4":
				divingGK=rotate(divingGK, 127);
				divingGKLabel.setBounds(710, 175, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(420, 420, Image.SCALE_DEFAULT)));
				break;
			case "C1":
				divingGK=rotate(divingGK, -40);
				divingGKLabel.setBounds(410, 275, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(435, 435, Image.SCALE_DEFAULT)));
				break;
			case "C2":
				divingGK=rotate(divingGK, -40);
				divingGKLabel.setBounds(560, 275, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(435, 435, Image.SCALE_DEFAULT)));
				break;
			case "C3":
				divingGK=rotate(divingGK, 127);
				divingGKLabel.setBounds(560, 275, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(420, 420, Image.SCALE_DEFAULT)));
				break;
			case "C4":
				divingGK=rotate(divingGK, 127);
				divingGKLabel.setBounds(710, 275, 500, 500);
				divingGKLabel.setIcon(new ImageIcon(new ImageIcon(divingGK).getImage().getScaledInstance(420, 420, Image.SCALE_DEFAULT)));
				break;
		}
	}

	private void editFootball(String pos) {
		switch(pos){
			case "A1":			
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(480, 280, 60, 60);
				break;
			case "A2":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(640, 260, 60, 60);
				break;
			case "A3":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(850, 260, 60, 60);
				break;
			case "A4":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(980, 280, 60, 60);
				break;
			case "B1":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(480, 340, 60, 60);
				break;
			case "B2":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(640, 380, 60, 60);
				break;
			case "B3":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(850, 380, 60, 60);
				break;
			case "B4":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(980, 395, 60, 60);
				break;
			case "C1":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(480, 490, 60, 60);
				break;
			case "C2":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(640, 490, 60, 60);
				break;
			case "C3":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(850, 490, 60, 60);
				break;
			case "C4":
				footballLabel.setIcon(new ImageIcon(new ImageIcon(football).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
				footballLabel.setBounds(980, 490, 60, 60);
				break;
		}
	}

	public static BufferedImage rotate(BufferedImage buffered, int i) {
	    double sin = Math.abs(Math.sin(Math.toRadians(i))),
	           cos = Math.abs(Math.cos(Math.toRadians(i)));
	    int w = buffered.getWidth();
	    int h = buffered.getHeight();
	    int neww = (int) Math.floor(w*cos + h*sin),
	        newh = (int) Math.floor(h*cos + w*sin);
	    BufferedImage rotated = new BufferedImage(neww, newh, buffered.getType());
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.translate((neww-w)/2, (newh-h)/2);
	    graphic.rotate(Math.toRadians(i), w/2, h/2);
	    graphic.drawRenderedImage(buffered, null);
	    graphic.dispose();
	    return rotated;
	}
	
	public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }

	public void printMessage(String result) {
		JOptionPane.showMessageDialog(contentPane, result);
	}

	public void setLblRole(String role) {
		this.lblRole.setText(role);
	}

	public JButton getBtnCloseConnection() {
		return btnCloseConnection;
	}
	
	public boolean closeConnection() {
		int res = JOptionPane.showConfirmDialog(contentPane, "Stai per chiudere la connessione. Ne sei davvero sicuro?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(res == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	public boolean askForNewGame() {
		int res = JOptionPane.showConfirmDialog(contentPane, "La partita e' terminata, vuoi giocare ancora?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(res == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	public void printWaitMessage() {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arcade Normal", Font.PLAIN, 14)));
		JOptionPane.showConfirmDialog(contentPane, "Attendi il tuo turno!", "", JOptionPane.PLAIN_MESSAGE);
	}

	public void setLblInputReceived(String mess) {
		lblInputReceived.setVisible(true);
		lblInputReceived.setText(mess);
	}

	public void editScore(String homeGoals, String awayGoals) {
		lblN1.setText(homeGoals);
		lblN2.setText(awayGoals);
	}
	
	public void opponentDisconnected() {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arcade Normal", Font.PLAIN, 14)));
		JOptionPane.showConfirmDialog(contentPane, "L'altro giocatore si e' disconnesso dalla partita. Il programma verra' terminato.", "", JOptionPane.PLAIN_MESSAGE);
	}
}