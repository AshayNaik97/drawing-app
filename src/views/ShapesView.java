package views;

import javax.swing.JPanel;


import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ShapesView extends JPanel {

	private JButton btnPoint,btnLine,btnSquare,btnRectangle,btnCircle,btnBrush,btnHexagon;
	/**
	 * Create the panel.
	 */
	public ShapesView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{73, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	
		btnLine = new JButton("Line");
		GridBagConstraints gbc_btnLine = new GridBagConstraints();
		gbc_btnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLine.insets = new Insets(0, 0, 5, 0);
		gbc_btnLine.gridx = 0;
		gbc_btnLine.gridy = 0;
		add(btnLine, gbc_btnLine);
		
		btnSquare = new JButton("Square");
		GridBagConstraints gbc_btnSquare = new GridBagConstraints();
		gbc_btnSquare.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSquare.insets = new Insets(0, 0, 5, 0);
		gbc_btnSquare.gridx = 0;
		gbc_btnSquare.gridy = 1;
		add(btnSquare, gbc_btnSquare);
		
		btnRectangle = new JButton("Rectangle");
		GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
		gbc_btnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_btnRectangle.gridx = 0;
		gbc_btnRectangle.gridy = 2;
		add(btnRectangle, gbc_btnRectangle);
		
		btnCircle = new JButton("Circle");
		GridBagConstraints gbc_btnCircle = new GridBagConstraints();
		gbc_btnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_btnCircle.gridx = 0;
		gbc_btnCircle.gridy = 3;
		add(btnCircle, gbc_btnCircle);
		
		btnBrush = new JButton("Brush");
		GridBagConstraints gbc_btnBrush = new GridBagConstraints();
		gbc_btnBrush.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrush.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrush.gridx = 0;
		gbc_btnBrush.gridy = 4;
		add(btnBrush, gbc_btnBrush);
	}
	// public JButton getBtnPoint() {
	// 	return btnPoint;
	// }
	// public void setBtnPoint(JButton btnPoint) {
	// 	this.btnPoint = btnPoint;
	// }
	public JButton getBtnLine() {
		return btnLine;
	}
	public void setBtnLine(JButton btnLine) {
		this.btnLine = btnLine;
	}
	public JButton getBtnSquare() {
		return btnSquare;
	}
	public void setBtnSquare(JButton btnSquare) {
		this.btnSquare = btnSquare;
	}
	public JButton getBtnRectangle() {
		return btnRectangle;
	}
	public void setBtnRectangle(JButton btnRectangle) {
		this.btnRectangle = btnRectangle;
	}
	public JButton getBtnCircle() {
		return btnCircle;
	}
	public void setBtnCircle(JButton btnCircle) {
		this.btnCircle = btnCircle;
	}	
	public JButton getBtnBrush() {
		return btnBrush;
	}
	public void setBtnBrush(JButton btnBrush) {
		this.btnBrush = btnBrush;
	}

}
