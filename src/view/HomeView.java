package view;

import java.util.Iterator;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.HomeModel;
import model.ModelUser;
import view.LoginSignupView.Button;

import javax.swing.JLabel;

public class HomeView extends JPanel {
    private JFrame frame;
    private JPanel panel;
    private JPanel topPanel, belowPanel;
    // private HomeModel model;
    private ModelUser user;

    public HomeView(ModelUser user) {
        // this.model = model;
        this.user = user;
        initialize();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        GradientPaint gp =  new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(22, 116, 66));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    public void initialize() {
        frame = new JFrame("Draw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setVisible(true);
        frame.setResizable(true);
        panel = new JPanel(new GridLayout(0, 1));
        // GradientPaint gra = new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(22, 116, 66));
        
        // panel.setBackground(new Color(22, 117, 67));
        frame.getContentPane().add(panel);
        // GridBagLayout gridBagLayout = new GridBagLayout();
        // gridBagLayout.columnWidths = new int[]{0, 0, 0, 31, 45, 46, 67, 63, 61, 62,
        // 59, 52, 46, 0};
        // gridBagLayout.rowHeights = new int[]{26, 0};
        // gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        // 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        // gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        // setLayout(gridBagLayout);
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Adjust spacing as needed
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // 5px left margin
        topPanel.setBackground(new Color(22, 117, 67));
        belowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Adjust spacing as needed
        belowPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // 5px left margin
        belowPanel.setBackground(new Color(22, 117, 67));
        belowPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JLabel lab = new JLabel("Welcome Back " + user.getUserName().toUpperCase(), SwingConstants.LEFT);
        lab.setFont(new Font("", Font.BOLD | Font.ITALIC, 40));
        lab.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 45));
        lab.setForeground(Color.WHITE);
        topPanel.add(lab);
        panel.add(topPanel);
        panel.add(belowPanel);
    }

    public void addDrawing(String folderName, String imageName, ActionListener listener) {
        ImageIcon icon = new ImageIcon(
                "./save/" + user.getUserName() + user.getEmail() + "/" + folderName + "/" + imageName);
        addButton(folderName, icon, listener);
        // JButton button = new JButton(folderName);
        // button.setIcon(resizeImageIcon(icon,240,160));
        // button.setHorizontalTextPosition(SwingConstants.CENTER);
        // button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // button.addActionListener(listener);
        // panel.add(button);
        // frame.revalidate();
    }

    public void addButton(String buttonName, ImageIcon icon, ActionListener listener) {
        // JButton button = new JButton(buttonName);
        // button.addActionListener(listener);
        // panel.add(button);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Position in the first column (left)
        constraints.gridy = 0; // Position in the first row (top)
        constraints.fill = GridBagConstraints.BOTH;
        JButton button = new JButton(buttonName);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        if(buttonName=="New") button.setIcon(resizeImageIcon(icon, 160, 160));
        else button.setIcon(resizeImageIcon(icon, 240, 160));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.addActionListener(listener);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3), button.getBorder()));
        button.setSize(300, 200);
        belowPanel.add(button,constraints);
        frame.revalidate();
        // frame.revalidate();
    }

    public void addLogoutButton(String buttonName, ActionListener listener) {
        JButton button = new JButton(buttonName);
        button.addActionListener(listener);
        button.setBackground(new Color(100, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 15, 5, 5);
        button.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        topPanel.add(button);
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

    public void exit() {
        frame.dispose();
    }

}
