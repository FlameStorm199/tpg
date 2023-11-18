package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.Window;

public class ControllerProva implements MouseListener{
	Window frame;

	public ControllerProva(Window frame) {
		// TODO Auto-generated constructor stub
		this.frame=frame;
		frame.registraEvento(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==frame.getA1()) {
			frame.modificaGrafica("A1", "B2", "Saved!");
		}
		if(e.getSource()==frame.getA2()) {
			frame.modificaGrafica("A2", "B2", "Goal!");
		}
		if(e.getSource()==frame.getA3()) {
			frame.modificaGrafica("A3", "B3", "Saved!");
		}
		if(e.getSource()==frame.getA4()) {
			frame.modificaGrafica("A4", "B3", "Saved!");
		}
		if(e.getSource()==frame.getB1()) {
			frame.modificaGrafica("B1", "B2", "Saved!");
		}
		if(e.getSource()==frame.getB2()) {
			frame.modificaGrafica("B2", "C2", "Saved!");
		}
		if(e.getSource()==frame.getB3()) {
			frame.modificaGrafica("B3", "C3", "Saved!");
		}
		if(e.getSource()==frame.getB4()) {
			frame.modificaGrafica("B4", "B3", "Saved!");
		}
		if(e.getSource()==frame.getC1()) {
			frame.modificaGrafica("C1", "C2", "Saved!");
		}
		if(e.getSource()==frame.getC2()) {
			frame.modificaGrafica("C2", "C3", "Saved!");
		}
		if(e.getSource()==frame.getC3()) {
			frame.modificaGrafica("C3", "C2", "Saved!");
		}
		if(e.getSource()==frame.getC4()) {
			frame.modificaGrafica("C4", "C3", "Saved!");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
