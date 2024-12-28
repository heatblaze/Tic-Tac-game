import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel;

    public TicTacToeGUI() {
        // Set up the main frame
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLayout(new BorderLayout());

        // Create the game board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }

        // Create the status label
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Inner class to handle button clicks
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // If the button is already clicked, do nothing
            if (!buttons[row][col].getText().equals("")) {
                return;
            }

            // Set the button's text to the current player's symbol
            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setEnabled(false);

            // Check for a winner or tie
            if (checkWinner(currentPlayer)) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("It's a tie!");
            } else {
                // Switch to the next player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    // Function to check if the current player has won
    private boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(String.valueOf(player)) &&
                    buttons[i][1].getText().equals(String.valueOf(player)) &&
                    buttons[i][2].getText().equals(String.valueOf(player))) {
                return true;
            }
            // Check columns
            if (buttons[0][i].getText().equals(String.valueOf(player)) &&
                    buttons[1][i].getText().equals(String.valueOf(player)) &&
                    buttons[2][i].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }
        return false;
    }

    // Function to check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to disable all buttons
    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
