import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// two players
public class two_players extends JPanel{
    char player='X';
    JButton[] buttons = new JButton[9]; //CREATING AN ARRAY OF 9 BUTTONS

    public two_players(){ //CONSTRUCTOR
        setLayout(new GridLayout(3,3)); //SETTING GRID LAYOUT OF 3 BY 3
        initializeButtons();
    }

    public void initializeButtons(){
        for(int i = 0; i < 9; i ++){
            buttons[i] =new JButton(); //CREATING BUTTONS
            buttons[i].setText("-"); //DEFAULT TEXT ON EACH BUTTON IS '-'
            buttons[i].setBackground(Color.WHITE); // ORIGINAL BG TO WHITE
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clicked = (JButton) e.getSource(); //GETTING THE BUTTON THAT WAS CLICKED
                    clicked.setText(String.valueOf(player)); //SETTING THE BUTTON LABEL AS THE CURRENT PLAYER
                    clicked.setFont(new Font("Arial", Font.BOLD, 40 ));

                    if (player == 'X'){ // PREVIOUSLY X PLAYED
                        player= 'O';  // NOW ITS O'S TURN
                        clicked.setBackground(Color.MAGENTA); //SETTING THE BG COLOR OF X BUTTON AS MAGENTA

                    }
                    else{ //PREVIOUSLY O PLAYED
                        player = 'X';
                        clicked.setBackground(Color.green); //SETTING THE BG COLOR OF O BUTTON AS GREEN

                    }
                    displayWinner();

                }
            });

            add(buttons[i]); //ADDING BUTTON TO THE PANEL
        }
    }
    public void displayWinner(){
        if (checkWinner()){ // IF PLAYER HAS ALL IT'S SYMBOL IN EITHER A SINGLE ROW, COLUMN OR DIAGONAL
            // player would have been changed // AS IN THE INITIALIZE FUNCTION WE ARE IMMEDIATELY CHANGING THE PLAYERS
            // inorder to display it we did the following
            if (player == 'X'){
                player = 'O';
            }
            else{
                player = 'X';
            }
            JOptionPane pane = new JOptionPane(); // PANE THAT GIVES AN OPTION
            int dialog_result = JOptionPane.showConfirmDialog(pane,player + " wins " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
            if(dialog_result == JOptionPane.YES_OPTION){ //IF USER WANTS TO PLAY AGAIN
                reset(); //RESETTING THE BOARD
            }
            else{
                System.exit(0); //successful termination
            }
        }

        else if(checkDraw()){ // IF NONE OF THE ROWS, COLUMNS OR DIAGONAL ARE FILLED WITH A SINGLE PLAYER WE CHECK FOR A DRAW
            JOptionPane pane = new JOptionPane();
            int dialog_result = JOptionPane.showConfirmDialog(pane," Draw " + "\nPlay Again", " Game Over ", JOptionPane.YES_NO_OPTION);
            if(dialog_result == JOptionPane.YES_OPTION){
                reset();
            }
            else{
                System.exit(0); //successful termination
            }
        }

    }
    public void reset(){
        player = 'X';
        for (int i=0;i<9; i++){ // INITIALIZING THE BOARD AS IN THE DEFAULT CASE
            buttons[i].setText("-");
            buttons[i].setBackground(Color.WHITE);
        }
    }
    public boolean checkWinner(){
        if (checkRows()==true || checkColumns() == true || checkDiagonals() == true){
            return true;
        }
        return false;

    }
    public boolean checkRows(){ //CHECKING IF A PLAYER HAS ALL IT'S SYMBOL IN A SINGLE ROW
        int i=0;
        for(int j=0; j<3; j++){
            if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && buttons[i].getText().charAt(0) != '-'){
                return true;
            }
            i+=3;
        }
        return false;
    }

    public boolean checkColumns(){ //CHECKING IF A PLAYER HAS ALL IT'S SYMBOL IN A SINGLE COLUMN
        int i=0;
        for(int j=0; j<3; j++){ // TRAVERSING THROUGH EACH COLUMN TO CHECK IF A PLAYER WON
            if(buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && buttons[i].getText().charAt(0) != '-'){
                return true;
            }
            i+=1;
        }
        return false;
    }

    public boolean checkDiagonals(){ //CHECKING IF A PLAYER HAS ALL IT'S SYMBOL IN A SINGLE DIAGONAL
        //there are only two diagonal
        if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && buttons[0].getText().charAt(0) != '-'){
            return true;
        }
        else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && buttons[2].getText().charAt(0) != '-'){
            return true;
        }
        return false;
    }
    public boolean checkDraw(){
        boolean full=true; //BOARD IS FULL
        for(int i=0; i<9; i++){
            if(buttons[i].getText().charAt(0) == '-'){
                full = false; //MEANS PLACES ARE STILL LEFT TO BE FILLED
            }
        }
        return full;
    }
}

