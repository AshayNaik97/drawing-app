package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import javax.swing.border.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.lang.Object;
import shapes.Brush;

import frame.DrawingFrame;

public class SelectionView extends JPanel {

	private JButton btnUndo,btnRedo,btnInner,btnOutter;
	private JButton btnSelect;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnOpen;
	private JButton btnSave;

	private JRadioButton btnFill;
	private JLabel brushStrokeSize;

	private JButton btnPlus;
	private JButton btnMinus;

	private ImageIcon new_file = new ImageIcon(new ImageIcon("images/new-document.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon open_file = new ImageIcon(new ImageIcon("images/icons8-open-file-50.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon save_file = new ImageIcon(new ImageIcon("images/icons8-save-file-100.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon undo = new ImageIcon(new ImageIcon("images/icons8-undo-100.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon redo = new ImageIcon(new ImageIcon("images/icons8-redo-100.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon swatch = new ImageIcon(new ImageIcon("images/icons8-color-swatch-100.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon select = new ImageIcon(new ImageIcon("images/click.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon modify = new ImageIcon(new ImageIcon("images/edit.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon delete = new ImageIcon(new ImageIcon("images/delete.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

	Brush brush;
	/**
	 * Create the panel.
	 */
	public SelectionView() {
		setBackground(new Color(22, 117, 67));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 31, 45, 46, 67, 63, 61, 62, 59, 52, 46, 0};
		gridBagLayout.rowHeights = new int[]{26, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		// gridBagLayout.setBackground(Color.green);
		setLayout(gridBagLayout);
		
		// btnNew = new JButton("new");
		btnNew = new JButton();
		btnNew.setIcon(new_file);
		// btnNew.setIcon(new ImageIcon(SelectionView.class.getResource("../../resources/images/new.png")));
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.insets = new Insets(0, 0, 0, 5);
		gbc_btnNew.gridx = 0;
		gbc_btnNew.gridy = 0;
		btnNew.setBackground(new Color(22, 117, 67));
		// btnNew.setForeground(new Color(250, 250, 250));
		add(btnNew, gbc_btnNew);
		
		// btnOpen = new JButton("open");
		btnOpen = new JButton();
		btnOpen.setIcon(open_file);
		// btnOpen.setIcon(new ImageIcon(SelectionView.class.getResource("../../resources/images/open.png")));
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpen.gridx = 1;
		gbc_btnOpen.gridy = 0;
		btnOpen.setBackground(new Color(22, 117, 67));
		// btnOpen.setForeground(new Color(250, 250, 250));
		add(btnOpen, gbc_btnOpen);
		
		// btnSave = new JButton("save");
		btnSave = new JButton();
		btnSave.setIcon(save_file);
		// btnSave.setIcon(new ImageIcon(SelectionView.class.getResource("../../resources/images/save.png")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		btnSave.setBackground(new Color(22, 117, 67));
		// btnSave.setForeground(new Color(250, 250, 250));
		add(btnSave, gbc_btnSave);
		
		// btnUndo = new JButton("Undo"); //new ImageIcon(SelectionView.class.getResource("../../resources/images/pok4.png"))
		btnUndo = new JButton();
		btnUndo.setIcon(undo);
		btnUndo.setEnabled(false);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.BOTH;
		gbc_btnUndo.insets = new Insets(0, 0, 0, 5);
		gbc_btnUndo.gridx = 4;
		gbc_btnUndo.gridy = 0;
		btnUndo.setBackground(new Color(22, 117, 67));
		// btnUndo.setForeground(new Color(250, 250, 250));
		add(btnUndo, gbc_btnUndo);
		
		// btnRedo = new JButton("Redo"); //new ImageIcon(SelectionView.class.getResource("../../resources/images/pok42.png"))
		btnRedo = new JButton();
		btnRedo.setIcon(redo);
		btnRedo.setEnabled(false);
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.fill = GridBagConstraints.BOTH;
		gbc_btnRedo.insets = new Insets(0, 0, 0, 5);
		gbc_btnRedo.gridx = 5;
		gbc_btnRedo.gridy = 0;
		btnRedo.setBackground(new Color(22, 117, 67));
		// btnRedo.setForeground(new Color(250, 250, 250));
		add(btnRedo, gbc_btnRedo);
		
		JLabel lblColors = new JLabel("Colors:");
		GridBagConstraints gbc_lblColors = new GridBagConstraints();
		gbc_lblColors.anchor = GridBagConstraints.EAST;
		gbc_lblColors.insets = new Insets(0, 0, 0, 5);
		gbc_lblColors.gridx = 6;
		gbc_lblColors.gridy = 0;
		add(lblColors, gbc_lblColors);
		
		btnOutter = new JButton("");
		btnOutter.setIcon(swatch);
		GridBagConstraints gbc_btnOutter = new GridBagConstraints();
		gbc_btnOutter.fill = GridBagConstraints.BOTH;
		gbc_btnOutter.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutter.gridx = 7;
		gbc_btnOutter.gridy = 0;
		btnOutter.setBackground(new Color(22, 117, 67));
		// btnOutter.setForeground(new Color(250, 250, 250));
		add(btnOutter, gbc_btnOutter);
		
		btnInner = new JButton("");
		btnInner.setIcon(swatch);
		GridBagConstraints gbc_btnInner = new GridBagConstraints();
		gbc_btnInner.insets = new Insets(0, 0, 0, 5);
		gbc_btnInner.fill = GridBagConstraints.BOTH;
		gbc_btnInner.gridx = 8;
		gbc_btnInner.gridy = 0;
		btnInner.setBackground(new Color(22, 117, 67));
		// btnInner.setForeground(new Color(250, 250, 250));
		add(btnInner, gbc_btnInner);
		
		JLabel strokeSize = new JLabel("StrokeSize");
		GridBagConstraints gbc_strokeSize = new GridBagConstraints();
		gbc_strokeSize.anchor = GridBagConstraints.EAST;
		// gbc_strokeSize.insets = new Insets(0, 0, 0, 0);
		gbc_strokeSize.gridx = 9;
		gbc_strokeSize.gridy = 0;
		// gbc_strokeSize.weightx = 1;
		add(strokeSize, gbc_strokeSize);
		
		btnPlus = new JButton("+");
		btnPlus.setEnabled(true);
		GridBagConstraints gbc_btnPlus = new GridBagConstraints();
		// gbc_btnPlus.insets = new Insets(0, 0, 0, 0);
		gbc_btnPlus.gridx = 10;
		gbc_btnPlus.gridy = 0;
		// gbc_btnPlus.weightx = 1;
		btnPlus.setBackground(new Color(22, 117, 67));
		// btnPlus.setForeground(new Color(250, 250, 250));
		add(btnPlus, gbc_btnPlus);

		brushStrokeSize = new JLabel("1");
		GridBagConstraints gbc_brushStrokeSize = new GridBagConstraints();
		gbc_brushStrokeSize.anchor = GridBagConstraints.EAST;
		// gbc_brushStrokeSize.insets = new Insets(0, 0, 0, 0);
		gbc_brushStrokeSize.gridx = 10;
		gbc_brushStrokeSize.gridy = 0;
		// gbc_brushStrokeSize.weightx = 1;
		add(brushStrokeSize, gbc_brushStrokeSize);

		btnMinus = new JButton("-");
		btnMinus.setEnabled(true);
		GridBagConstraints gbc_btnMinus = new GridBagConstraints();
		// gbc_btnMinus.insets = new Insets(0, 0, 0, 5);
		gbc_btnMinus.gridx = 11;
		gbc_btnMinus.gridy = 0;
		// gbc_btnMinus.weightx = 1;
		btnMinus.setBackground(new Color(22, 117, 67));
		// btnMinus.setForeground(new Color(250, 250, 250));
		add(btnMinus, gbc_btnMinus);
		
		// btnSelect = new JButton("Select");
		btnSelect = new JButton();
		btnSelect.setIcon(select);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 12;
		gbc_btnSelect.gridy = 0;
		// gbc_btnSelect.weightx = 1;
		btnSelect.setBackground(new Color(22, 117, 67));
		// btnSelect.setForeground(new Color(250, 250, 250));
		add(btnSelect, gbc_btnSelect);
		
		// btnModify = new JButton("Modify");
		btnModify = new JButton();
		btnModify.setIcon(modify);
		btnModify.setEnabled(false);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.insets = new Insets(0, 0, 0, 5);
		gbc_btnModify.gridx = 13;
		gbc_btnModify.gridy = 0;
		// gbc_btnModify.weightx = 1;
		btnModify.setBackground(new Color(22, 117, 67));
		// btnModify.setForeground(new Color(250, 250, 250));
		add(btnModify, gbc_btnModify);
		
		// btnDelete = new JButton("Delete");
		btnDelete = new JButton();
		btnDelete.setIcon(delete);
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 14;
		gbc_btnDelete.gridy = 0;
		// gbc_btnDelete.weightx = 1;
		btnDelete.setBackground(new Color(22, 117, 67));
		// btnDelete.setForeground(new Color(250, 250, 250));
		add(btnDelete, gbc_btnDelete);

		btnFill = new JRadioButton("fill"); //new ImageIcon(SelectionView.class.getResource("../../resources/images/pok4.png"))
		btnFill.setSelected(false);
		// btnFill.setForeground(Color.WHITE);
		add(btnFill);
		
	}
	public void setBtnfill(JRadioButton btnFill) {
		this.btnFill = btnFill;
	}
	public JRadioButton getBtnfill() {
		return btnFill;

	}
	public JButton getBtnUndo() {
		return btnUndo;
	}
	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}
	public JButton getBtnRedo() {
		return btnRedo;
	}
	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}
	public JButton getBtnInner() {
		return btnInner;
	}
	public void setBtnInner(JButton btnInner) {
		this.btnInner = btnInner;
	}
	public JButton getBtnOutter() {
		return btnOutter;
	}
	public void setBtnOutter(JButton btnOutter) {
		this.btnOutter = btnOutter;
	}
	public JButton getBtnSelect() {
		return btnSelect;
	}
	public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}
	public JButton getBtnModify() {
		return btnModify;
	}
	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	public JButton getBtnNew() {
		return btnNew;
	}
	public void setBtnNew(JButton btnNew) {
		this.btnNew = btnNew;
	}
	public JButton getBtnOpen() {
		return btnOpen;
	}
	public void setBtnOpen(JButton btnOpen) {
		this.btnOpen = btnOpen;
	}
	public JButton getBtnSave() {
		return btnSave;
	}
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}
	public JButton getBtnPlus() {
		return btnPlus;
	}
	public void setBtnPlus(JButton btnPlus) {
		this.btnPlus = btnPlus;
	}
	public JButton getBtnMinus() {
		return btnMinus;
	}
	public void setBtnMinus(JButton btnMinus) {
		this.btnMinus = btnMinus;
	}

	public JLabel getBrushStrokeSize(){
		return brushStrokeSize;
	}

}
