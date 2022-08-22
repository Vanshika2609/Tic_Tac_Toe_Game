import javax.swing.*;
import java.util.Scanner;

//main file

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe" + "\n Choose among the following");
        System.out.println("1.Play against Computer");
        System.out.println("2. Two Players");
        int ch = sc.nextInt();
        if(ch==1){
            System.out.println("Computer - X");
            System.out.println("Player - O");
            JFrame frame = new JFrame("TIC-TAC-TOE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new with_computer());
            frame.setBounds(500,500,500,500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); //to keep the frame at the centre of the window
        }
        else if(ch==2){
            JFrame frame = new JFrame("TIC-TAC-TOE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new two_players());
            frame.setBounds(500,500,500,500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); //to keep the frame at the centre of the window
        }
        else{
            System.out.println("Invalid choice");
        }

    }
}
