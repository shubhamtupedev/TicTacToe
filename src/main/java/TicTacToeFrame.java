import java.awt.*;
import java.awt.event.*;

public class TicTacToeFrame extends Frame implements ActionListener {

    private final Button[] buttons = new Button[9];
    private boolean playerXTurn = true;
    private Label statusLabel;
    private Button newGameButton;

    public TicTacToeFrame() {
        super("Tic-Tac-Toe Game");
        setupUI();
    }

    private void setupUI() {
        setSize(400, 530);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // ▼ Title Label
        Label title = new Label("Tic-Tac-Toe", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBackground(new Color(50, 50, 50));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(getWidth(), 60));
        add(title, BorderLayout.NORTH);

        // ▼ Button Grid
        Panel gridPanel = new Panel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBackground(new Color(30, 30, 30));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 38));
            buttons[i].setBackground(new Color(255, 203, 96));
            buttons[i].setForeground(Color.BLACK);
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }
        add(gridPanel, BorderLayout.CENTER);

        // ▼ South Panel for Status and New Game Button
        Panel southPanel = new Panel(new BorderLayout());
        southPanel.setBackground(new Color(40, 40, 40));
        southPanel.setPreferredSize(new Dimension(getWidth(), 80));

        statusLabel = new Label("Player X's Turn", Label.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        statusLabel.setForeground(Color.WHITE);
        southPanel.add(statusLabel, BorderLayout.CENTER);

        // ▼ New Game Button
        newGameButton = new Button("New Game");
        newGameButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        newGameButton.setBackground(new Color(70, 130, 180)); // SteelBlue
        newGameButton.setForeground(Color.BLUE);
        newGameButton.addActionListener(e -> resetGame());
        southPanel.add(newGameButton, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // ▼ Window Closing Behavior
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();

        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(playerXTurn ? "X" : "O");
        clicked.setForeground(playerXTurn ? Color.BLUE : new Color(204, 0, 0));

        if (checkWin()) {
            statusLabel.setText("Player " + (playerXTurn ? "X" : "O") + " Wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a Draw!");
        } else {
            playerXTurn = !playerXTurn;
            statusLabel.setText("Player " + (playerXTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWin() {
        String[][] grid = new String[3][3];
        for (int i = 0; i < 9; i++) {
            grid[i / 3][i % 3] = buttons[i].getLabel();
        }

        // ⬅ Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].equals("") &&
                    grid[i][0].equals(grid[i][1]) &&
                    grid[i][1].equals(grid[i][2])) return true;

            if (!grid[0][i].equals("") &&
                    grid[0][i].equals(grid[1][i]) &&
                    grid[1][i].equals(grid[2][i])) return true;
        }

        // ⬅ Diagonals
        if (!grid[0][0].equals("") &&
                grid[0][0].equals(grid[1][1]) &&
                grid[1][1].equals(grid[2][2])) return true;

        if (!grid[0][2].equals("") &&
                grid[0][2].equals(grid[1][1]) &&
                grid[1][1].equals(grid[2][0])) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (Button btn : buttons) {
            if (btn.getLabel().equals("")) return false;
        }
        return true;
    }

    private void disableButtons() {
        for (Button btn : buttons) {
            btn.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button btn : buttons) {
            btn.setEnabled(true);
            btn.setLabel("");
            btn.setForeground(Color.BLACK);
        }
        playerXTurn = true;
        statusLabel.setText("Player X's Turn");
    }
}
