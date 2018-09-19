package kata.tennis.main;

import kata.tennis.entity.Player;
import kata.tennis.play.Scores;

import java.util.Scanner;

public class KataTennisMain {

    public static void main(String[] args){

        System.out.println("Insert first player : ");
        String playerOneName = new Scanner(System.in).nextLine();
        System.out.println("Insert second player : ");
        String playerTwoName = new Scanner(System.in).nextLine();

        // Instantiate players
        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);

        Scores scores = new Scores(playerOne,playerTwo);

        String gamePoints = scores.getSetPoints();

        while (!gamePoints.contains(Scores.END_MATCH)) {
            System.out.println(gamePoints + "\n");
            scores.setRandomPoints();
            gamePoints = scores.getSetPoints();
        }

        System.out.println(gamePoints + "\n");
    }
}
