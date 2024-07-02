import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BingoSlot {
    int boardWidth = 600;
    int boardHeight = 600;

    JFrame frame = new JFrame("BINGO Slot");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton spinButton = new JButton("SPIN TO WIN!");

    // 2D array
    JButton[][] board = new JButton[3][3];

    int currentMoney = 20;
    boolean SPIN = false;
    boolean GAMEOVER = false;
    boolean BINGO = false;
    ArrayList<Integer> bingoNumbers;
    String bingoCombination;
    int count = 0;

    BingoSlot() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Current Cash (" + currentMoney + "$)");
        textLabel.setOpaque(true);

        //result text frame
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        ///spin button
        spinButton.setFocusable(false);
        buttonPanel.add(spinButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        //table frame
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;

                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
            }
        }

        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gatekeeper
                if (GAMEOVER == true) {
                    return;
                }

                //Paying method
                Score();

                //RNG
                RNG();

                //Bingo logic
                checkBingo();

                // Winning combination check + gains
                checkWinner();

                // Game over check
                checkOver();
            }
        });
    }

    void checkBingo() {
        int b = 0;
        int b1 = 0;
        int b2 = 0;
        int b3 = 0;
        int b4 = 0;
        int b5 = 0;
        int temp = 0;
        bingoCombination = "";

        for (int i = 0; i < bingoNumbers.size(); i++) {
            if (bingoNumbers.get(i) == 0) {
                b++;
                if (b >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
            if (bingoNumbers.get(i) == 1) {
                b1++;
                if (b1 >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
            if (bingoNumbers.get(i) == 2) {
                b2++;
                if (b2 >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
            if (bingoNumbers.get(i) == 3) {
                b3++;
                if (b3 >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
            if (bingoNumbers.get(i) == 4) {
                b4++;
                if (b4 >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
            if (bingoNumbers.get(i) == 5) {
                b5++;
                if (b5 >= 5) {
                    temp = bingoNumbers.get(i);
                }
            }
        }
        if (b >= 5 || b1 >= 5 || b2 >= 5 || b3 >= 5 || b4 >= 5 || b5 >= 5) {
            BINGO = true;
            bingoCombination = String.valueOf(temp);
        }
        ///// Bingo colors
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().equals(bingoCombination)) {
                    board[r][c].setBackground(Color.gray);
                    board[r][c].setForeground(Color.yellow);
                }
            }
        }
    }

    void checkWinner() {
        count = 0;
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals(board[r][1].getText()) &&
                    board[r][1].getText().equals(board[r][2].getText())) {
                count++;
                SPIN = true;
                //first row
                if (board[0][0].getText().equals(board[0][1].getText()) &&
                        board[0][1].getText().equals(board[0][2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[0][i].setBackground(Color.gray);
                        board[0][i].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
                //second row
                if (board[1][0].getText().equals(board[1][1].getText()) &&
                        board[1][1].getText().equals(board[1][2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[1][i].setBackground(Color.gray);
                        board[1][i].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
                //third row
                if (board[2][0].getText().equals(board[2][1].getText()) &&
                        board[2][1].getText().equals(board[2][2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[2][i].setBackground(Color.gray);
                        board[2][i].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
            }
        }
        ///vertical (mirrored horizontal)
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals(board[1][c].getText()) &&
                    board[1][c].getText().equals(board[2][c].getText())) {
                count++;
                SPIN = true;
                //first column
                if (board[0][0].getText().equals(board[1][0].getText()) &&
                        board[1][0].getText().equals(board[2][0].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[i][0].setBackground(Color.gray);
                        board[i][0].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
                //first column
                if (board[0][1].getText().equals(board[1][1].getText()) &&
                        board[1][1].getText().equals(board[2][1].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[i][1].setBackground(Color.gray);
                        board[i][1].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
                //first column
                if (board[0][2].getText().equals(board[1][2].getText()) &&
                        board[1][2].getText().equals(board[2][2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        board[i][2].setBackground(Color.gray);
                        board[i][2].setForeground(Color.yellow);
                        textLabel.setBackground(Color.orange);
                        textLabel.setForeground(Color.white);
                    }
                }
            }
        }
        //// clockwise diagonally
        if (board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            count++;
            SPIN = true;
            textLabel.setBackground(Color.orange);
            textLabel.setForeground(Color.white);
            for (int i = 0; i < 3; i++) {
                board[i][i].setBackground(Color.gray);
                board[i][i].setForeground(Color.yellow);
            }

        }
        //// counter-clockwise diagonally
        if (board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            count++;
            SPIN = true;
            textLabel.setBackground(Color.orange);
            textLabel.setForeground(Color.white);
            board[0][2].setForeground(Color.yellow);
            board[1][1].setForeground(Color.yellow);
            board[2][0].setForeground(Color.yellow);
            board[0][2].setBackground(Color.gray);
            board[1][1].setBackground(Color.gray);
            board[2][0].setBackground(Color.gray);
        }
        // Results (top banner)
        if (BINGO == false) {
            if (SPIN == true) {
                currentMoney = currentMoney + (2 * count);
                textLabel.setText("SCORE!!! (" + currentMoney + "$) +" + (2 * count) + "$");
            }
        } else {
            textLabel.setBackground(Color.orange);
            textLabel.setForeground(Color.white);
            currentMoney = currentMoney + ((10) + (2 * count));
            textLabel.setText("B I N G O !!! (" + currentMoney + "$) +" + ((10) + (2 * count)) + "$");
        }
    }

    void checkOver() {
        if (0 >= currentMoney) {
            textLabel.setText("GAME OVER! (" + currentMoney + "$)");
            GAMEOVER = true;
            ///// Colors for gameover
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    board[r][c].setBackground(Color.gray);
                    board[r][c].setForeground(Color.white);
                    textLabel.setBackground(Color.black);
                    textLabel.setForeground(Color.white);
                }
            }
        }
    }

    void Score() {
        // Spin reset
        SPIN = false;
        BINGO = false;
        // Resets colors
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
                textLabel.setBackground(Color.darkGray);
                textLabel.setForeground(Color.white);
            }
        }
        currentMoney = currentMoney - 1;
        textLabel.setText("Current Cash (" + currentMoney + "$)");
    }

    void RNG() {
        bingoNumbers = new ArrayList<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Random rando = new Random();
                int numberGen;
                numberGen = rando.nextInt(0, 6);
                bingoNumbers.add(numberGen);
                String combination = String.valueOf(numberGen);
                board[r][c].setText(combination);
            }
        }
    }
}
