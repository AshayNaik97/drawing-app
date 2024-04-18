package views;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;

public class ShapesView extends JPanel {

	private JButton btnLine, btnSquare, btnRectangle, btnCircle, btnBrush, btnEraser;

	private ImageIcon line = new ImageIcon(new ImageIcon("images/diagonal-line.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon square = new ImageIcon(new ImageIcon("images/square.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon rectangle = new ImageIcon(new ImageIcon("images/rectangle.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon circle = new ImageIcon(new ImageIcon("images/circle.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon brush = new ImageIcon(new ImageIcon("images/brush.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon eraser = new ImageIcon(new ImageIcon("images/eraser.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

	/**
	 * Create the panel.
	 */
	public ShapesView() {
		setBackground(new Color(22, 117, 67));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 73, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		btnLine = new JButton("Line");
		// btnLine = new JButton();
		btnLine.setIcon(line);
		btnLine.setOpaque(true);
		btnLine.setBackground(Color.RED);
		btnLine.setHorizontalTextPosition(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnLine = new GridBagConstraints();
		gbc_btnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLine.insets = new Insets(0, 0, 5, 0);
		gbc_btnLine.gridx = 0;
		gbc_btnLine.gridy = 0;
		add(btnLine, gbc_btnLine);

		btnSquare = new JButton("Square");
		// btnSquare = new JButton();
		btnSquare.setIcon(square);
		btnSquare.setBackground(Color.WHITE);
		btnSquare.setOpaque(true);
		GridBagConstraints gbc_btnSquare = new GridBagConstraints();
		gbc_btnSquare.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSquare.insets = new Insets(0, 0, 5, 0);
		gbc_btnSquare.gridx = 0;
		gbc_btnSquare.gridy = 1;
		add(btnSquare, gbc_btnSquare);

		btnRectangle = new JButton("Rectangle");
		// btnRectangle = new JButton();
		btnRectangle.setIcon(rectangle);
		btnRectangle.setBackground(Color.WHITE);
		btnRectangle.setOpaque(true);
		GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
		gbc_btnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_btnRectangle.gridx = 0;
		gbc_btnRectangle.gridy = 2;
		add(btnRectangle, gbc_btnRectangle);

		btnCircle = new JButton("Circle");
		// btnCircle = new JButton();
		btnCircle.setIcon(circle);
		btnCircle.setBackground(Color.WHITE);
		btnCircle.setOpaque(true);
		GridBagConstraints gbc_btnCircle = new GridBagConstraints();
		gbc_btnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_btnCircle.gridx = 0;
		gbc_btnCircle.gridy = 3;
		add(btnCircle, gbc_btnCircle);

		btnBrush = new JButton("Brush");
		// btnBrush = new JButton();
		btnBrush.setIcon(brush);
		btnBrush.setBackground(Color.WHITE);
		btnBrush.setOpaque(true);
		GridBagConstraints gbc_btnBrush = new GridBagConstraints();
		gbc_btnBrush.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrush.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrush.gridx = 0;
		gbc_btnBrush.gridy = 4;
		add(btnBrush, gbc_btnBrush);

		btnEraser = new JButton("Eraser");
		// btnEraser = new JButton();
		btnEraser.setIcon(eraser);
		btnEraser.setBackground(Color.WHITE);
		btnEraser.setOpaque(true);
		GridBagConstraints gbc_btnEraser = new GridBagConstraints();
		gbc_btnEraser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEraser.insets = new Insets(0, 0, 5, 0);
		gbc_btnEraser.gridx = 0;
		gbc_btnEraser.gridy = 5;
		add(btnEraser, gbc_btnEraser);

	}

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

	public JButton getBtnEraser() {
		return btnEraser;
	}

	public void setBtnEraser(JButton btnEraser) {
		this.btnEraser = btnEraser;
	}

}
