import java.awt.*;
import java.awt.event.*;

public class TicTacToeFrame extends Frame implements ActionListener {

    private final Button[] buttons = new Button[9];

    public TicTacToeFrame() {
        super("Tic-Tac-Toe Game");
        setupFrame();
    }

    private void setupFrame() {
        setSize(400, 500);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // Dark Mode Background

        // Title Label
        Label headerLabel = new Label("Tic-Tac-Toe", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBackground(new Color(50, 50, 50));
        headerLabel.setPreferredSize(new Dimension(getWidth(), 70));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for Buttons
        Panel gridPanel = new Panel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBackground(new Color(30, 30, 30));
        gridPanel.setPreferredSize(new Dimension(300, 300));
        gridPanel.setFont(new Font("Arial", Font.BOLD, 40));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].setBackground(new Color(255, 203, 96)); // Soft Orange
            buttons[i].setForeground(new Color(50, 50, 50));   // Darker text
            buttons[i].addActionListener(this);
            buttons[i].setLabel("");
            gridPanel.add(buttons[i]);
        }

        add(gridPanel, BorderLayout.CENTER);

        // Event to close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closed.");
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button buttonClicked = (Button) e.getSource();
        System.out.println(buttonClicked);
        if (buttonClicked.getLabel().equals("")) {
            buttonClicked.setLabel("X"); // Placeholder for gameplay logic
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}
