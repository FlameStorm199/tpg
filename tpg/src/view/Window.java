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

import control.Controller;
import control.ControllerFinestraIniziale;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane=new JPanel();
	private ImageIcon sfondo;
	private JLabel sfondoLabel;
	private JPanel panel;
	private JLabel A1, B1, C1, A2, B2, C2, A3, B3, C3, A4, B4, C4;
	private JLabel portiereFermoLabel;
	private ImageIcon portiereFermo;
	private BufferedImage portiereTuffo;
	private JLabel labelPortiereTuffo;
	
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		sfondoLabel = new JLabel("");
		sfondoLabel.setBounds(350, 10, 850, 781);
		sfondo = new ImageIcon(new ImageIcon("src/immagini/sfondo.png").getImage().getScaledInstance(880, 830, Image.SCALE_DEFAULT));
		sfondoLabel.setIcon(sfondo);
		
		portiereFermo=new ImageIcon(new ImageIcon("src/immagini/portiereFermo.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
		
		labelPortiereTuffo=new JLabel("");
		contentPane.add(labelPortiereTuffo);
		
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
//		A1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				A1.setOpaque(true);
//				A1.setBackground(Color.YELLOW);
//			}
//		});
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
		contentPane.add(sfondoLabel);
		
		setContentPane(contentPane);
	}
	
	public void registraEvento(Controller c) {
		A1.addMouseListener(c);
		A2.addMouseListener(c);
		A3.addMouseListener(c);
		A4.addMouseListener(c);
		B1.addMouseListener(c);
		B2.addMouseListener(c);
		B3.addMouseListener(c);
		B4.addMouseListener(c);
		C1.addMouseListener(c);
		C2.addMouseListener(c);
		C3.addMouseListener(c);
		C4.addMouseListener(c);
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

	public void muoviPortiere(String pos) {
		portiereFermoLabel.setVisible(false);
		if(pos.equals("A1")) {
			try {
				portiereTuffo = ImageIO.read(new File("portiereTuffo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//portiereTuffo=rotate(portiereTuffo, 180);
			labelPortiereTuffo.setIcon(new ImageIcon(new ImageIcon(portiereTuffo).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
			//labelPortiereTuffo.setIcon(new ImageIcon(portiereTuffo));
			labelPortiereTuffo.setBounds(300, 53, 850, 781);
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
}
