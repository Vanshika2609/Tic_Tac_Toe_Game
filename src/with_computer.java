import javax.swing.*;
import java.util.Random;
import java.util.Random.*;
import java.awt.*;
import java.awt.event.*;
//with computer
public class with_computer extends JPanel implements MouseListener{
    //computer-'x'
    //player-'o'
    char player = 'O';
    boolean[] visited = new boolean[9]; //TO KEEP THE TRACK OF THE BUTTON THAT IS ALREADY VISITED
    JButton[] buttons = new JButton[9]; // CREATING ARRAY FOR 9 BUTTONS

    public with_computer() { //CONSTRUCTOR
        setLayout(new GridLayout(3, 3)); //3 x 3 layout
        initializeButtons();
    }

    public void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("-"); //DEFAULT TEXT
            buttons[i].setBackground(Color.WHITE); //DEFAULT BG COLOR
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addMouseListener(this); //the mouse click
            add(buttons[i]);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton clicked = (JButton) e.getSource();
        for(int i=0;i<9;i++){
            if(buttons[i]==clicked){
                if(visited[i]==false){
                    clicked.setText("O");
                    clicked.setBackground(Color.green);
                    visited[i]=true;
                }
            }
        }
        putX();
    }

    public void putX() {
        // Check if game is over
        if (checkWinner()) { // if any player has all it's symbol in a single row, column or diagnol
            if (player == 'X'){
                player = 'O';
            }
            else{
                player = 'X';
            }

            JOptionPane pane = new JOptionPane();
            int dialog_result = JOptionPane.showConfirmDialog(pane, player + " wins " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
            if (dialog_result == JOptionPane.YES_OPTION) {
                reset(); //resetting the game as player chose to play again
            } else { //exiting as player chose not to play again
                System.exit(0); //successful termination
            }
        } else if (checkDraw()) {
            JOptionPane pane = new JOptionPane();
            int dialog_result = JOptionPane.showConfirmDialog(pane, " Draw " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
            if (dialog_result == JOptionPane.YES_OPTION) {
                reset();
            } else {
                System.exit(0); //successful termination
            }
        }
        // Play X, possibly ending the game
        else {
            nextMove();
            if (checkWinner()) {
                JOptionPane pane = new JOptionPane();
                int dialog_result = JOptionPane.showConfirmDialog(pane, player + " wins " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
                if (dialog_result == JOptionPane.YES_OPTION) {
                    reset();
                } else {
                    System.exit(0); //successful termination
                }
            } else if (checkDraw()) {
                JOptionPane pane = new JOptionPane();
                int dialog_result = JOptionPane.showConfirmDialog(pane, " Draw " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
                if (dialog_result == JOptionPane.YES_OPTION) {
                    reset();
                } else {
                    System.exit(0); //successful termination
                }
            }
        }
    }

    public boolean checkWinner() {
        if (checkRows() == true || checkColumns() == true || checkDiagonals() == true) {
            return true;
        }
        return false;

    }

    public boolean checkRows() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText()) && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
            i += 3;
        }
        return false;
    }

    public boolean checkColumns() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText()) && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
            i += 1;
        }
        return false;
    }

    public boolean checkDiagonals() {
        //there are only two diagonal
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && buttons[0].getText().charAt(0) != '-') {
            return true;
        } else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && buttons[2].getText().charAt(0) != '-') {
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        boolean full = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().charAt(0) == '-') {
                full = false;
            }
        }
        return full;
    }

    void nextMove() {
        Random random = new Random();
        int r = findRow('X');  // complete a row of X and win if possible
        if (r < 0) //when none of the combinations of end points are of 'X'
            r = findRow('O');  // or try to block O from winning
        if (r < 0) {  // otherwise move randomly
            do
                r = random.nextInt(9);
            while (buttons[r].getText().charAt(0) != '-');
        }
        if(visited[r] == false) {
            buttons[r].setText("X");
            buttons[r].setBackground(Color.MAGENTA);
            visited[r] = true;
        }
    }

    // Return 0-8 for the position of a blank spot in a row if the
    // other 2 spots are occupied by player, or -1 if no spot exists
    int findRow(char player) {
        int rows[][] = {{0, 2}, {3, 5}, {6, 8}, {0, 6}, {1, 7}, {2, 8}, {0, 8}, {2, 6}}; //end points of all possible rows,columns and diagonals available to play
        for (int i = 0; i < 8; i++) {
            int result = find1Row(player, rows[i][0], rows[i][1]); //(player - X or O , starting pos, end pos)
            if (result >= 0)
                return result;
        }
        return -1; //it will return -1 if not any of the places are fulfilling the condition for the computer to win so try to block the player
    }

    // If 2 of 3 spots in the row from position[a] to position[b]
    // are occupied by player and the third is blank, then return the
    // index of the blank spot, else return -1.
    int find1Row(char player, int a, int b) {
        int c = (a + b) / 2;  // middle spot
        if (buttons[a].getText().charAt(0) == player && buttons[b].getText().charAt(0) == player && buttons[c].getText().charAt(0) == '-')
            return c;
        if (buttons[a].getText().charAt(0) == player && buttons[c].getText().charAt(0) == player && buttons[b].getText().charAt(0) == '-')
            return b;
        if (buttons[b].getText().charAt(0) == player && buttons[c].getText().charAt(0) == player && buttons[a].getText().charAt(0) == '-')
            return a;
        return -1;
    } //it will return -1 if any of the rows, columns or diagnols are not of the player
    //i.e for eg is find1Row('X') then it will try to block the '0' if it has already filled any of the places.

    private void reset() {
        player = 'X';
        for (int i = 0; i < 9; i++) {
            visited[i] = false;
            buttons[i].setText("-");
            buttons[i].setBackground(Color.WHITE);
        }
        //Computer starts
        nextMove();

    }
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
