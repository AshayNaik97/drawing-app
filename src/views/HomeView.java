package views;

import java.awt.Graphics;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.HomeModel;
import model.ModelUser;
import java.awt.GridBagLayout;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;


public class HomeView extends JPanel {
    private JFrame frame;
    private JPanel panel;
    private HomeModel model;
    private String user;
    private int i =0;
    private int j =0;
	public HomeView(HomeModel model,String user) {
        this.model = model;
        this.user = user;
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Image Buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
		frame.setVisible(true);
		frame.setResizable(true);
        panel = new JPanel(new GridLayout(0, 1));
        frame.getContentPane().add(panel);
        GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 31, 45, 46, 67, 63, 61, 62, 59, 52, 46, 0};
		gridBagLayout.rowHeights = new int[]{26, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		// gridBagLayout.setBackground(Color.green);
		setLayout(gridBagLayout);
        // JButton create = new JButton("new draw");
        // JButton logout = new JButton("Log Out");
        JLabel lab = new JLabel("Welcome Back "+user.toUpperCase(), SwingConstants.CENTER);
        panel.add(lab);
        // panel.add(logout);
        // logout.addActionListener(listener);
        // panel.add(create);
        // create.addActionListener(listener);
    }

    public void addDrawing(String folderName, String imageName, ActionListener listener) {
            ImageIcon icon = new ImageIcon("./save/" + folderName + "/" + imageName);
            JButton button = new JButton(folderName,resizeImageIcon(icon,300,200));
            button.addActionListener(listener);
            // if(j==3){
            //     j=0;
            // }
            panel.add(button);
            frame.revalidate();
    }
    public void addButton(String buttonName,ActionListener listener){
        JButton button = new JButton(buttonName);
        button.addActionListener(listener);
        panel.add(button);
        // frame.revalidate();
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
    
    public JFrame getFrame() {
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void exit(){
        frame.dispose();
    }
	
}


