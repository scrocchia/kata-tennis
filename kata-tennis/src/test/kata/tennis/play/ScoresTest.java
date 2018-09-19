package test.kata.tennis.play;

import kata.tennis.entity.Player;
import kata.tennis.play.Scores;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoresTest {

    Scores scores;
    Player firstPlayer, secondPlayer;

    @Before
    public void beforeTest(){
        firstPlayer = new Player("Andrea");
        secondPlayer = new Player("Simone");
        scores = new Scores(firstPlayer, secondPlayer);
    }

    @Test
    public void initScoreTest(){
        assertEquals(String.format(Scores.GAME_SCORE, "Andrea","0", "Simone", "0"), scores.checkGamePoints());
    }

    @Test
    public void displayGameTest(){
        firstPlayer.winsPoint();
        firstPlayer.winsPoint();
        firstPlayer.winsPoint();
        secondPlayer.winsPoint();
        secondPlayer.winsPoint();
        assertEquals(String.format(Scores.GAME_SCORE, "Andrea","40", "Simone", "30"), scores.checkGamePoints());
    }

    @Test
    public void deuceGameScoreTest(){
        for (int i = 0; i<=2; i++){
            firstPlayer.winsPoint();
            secondPlayer.winsPoint();
        }
        assertEquals(Scores.DEUCE, scores.checkGamePoints());
    }

    @Test
    public void advantageGameScoreTest(){
        for (int i = 0; i<=2; i++){
            firstPlayer.winsPoint();
            secondPlayer.winsPoint();
        }
        firstPlayer.winsPoint();
        assertEquals(String.format("%s %s", Scores.ADVANTAGE, "Andrea"), scores.checkGamePoints());
    }

    @Test
    public void playerWinsAfterAdvantageTest(){
        for (int i = 0; i<3; i++){
            firstPlayer.winsPoint();
            secondPlayer.winsPoint();
        }
        firstPlayer.winsPoint();
        firstPlayer.winsPoint();
        // check that the game score is reset to 0/0 after a player wins a game
        assertEquals(String.format(Scores.GAME_SCORE, "Andrea","0", "Simone", "0"), scores.checkGamePoints());
    }

    @Test
    public void deuceScoreAfterAdvantageTest(){
        for (int i = 0; i<3; i++){
            firstPlayer.winsPoint();
            secondPlayer.winsPoint();
        }
        secondPlayer.winsPoint();
        firstPlayer.winsPoint();
        assertEquals(Scores.DEUCE, scores.checkGamePoints());
    }

    @Test
    public void tieBreakScoreTest(){
        for (int i=0; i<=5; i++){
            firstPlayer.winsPoint();
        }
        for (int i=0; i<=3; i++){
            secondPlayer.winsPoint();
        }
        assertEquals(String.format(Scores.GAME_SCORE, "Andrea","6", "Simone", "4"), scores.checkTieBreak());
    }

    @Test
    public void playerWinsSetWithSixGamesTest(){

        int incrementScoreFirstPlyer = 0;
        int incrementScoreSecondPlyer = 0;


        for (int i =0; i<=3; i++){
            incrementScoreFirstPlyer++;
            secondPlayer.setSetScore(incrementScoreFirstPlyer);
        }
        for (int i =0; i<=5; i++){
            incrementScoreSecondPlyer++;
            firstPlayer.setSetScore(incrementScoreSecondPlyer);
        }
        String expectedGameScore = String.format(Scores.GAME_SCORE, "Andrea","0", "Simone", "0");
        assertEquals(String.format(Scores.FULL_GAME_SCORE + "\n" + "Andrea" + " " + Scores.END_MATCH, "Andrea", "6", "Simone", "4", expectedGameScore), scores.getSetPoints());
    }

}
