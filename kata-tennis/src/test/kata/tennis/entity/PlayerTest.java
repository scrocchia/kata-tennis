package test.kata.tennis.entity;

import kata.tennis.entity.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player firstPlayer,secondPlayer;

    @Before
    public void beforeTests(){
        firstPlayer = new Player("Andrea");
        secondPlayer = new Player("Simone");
    }

    @Test
    public void initPlayerTest(){
        assertEquals("Andrea", firstPlayer.getName());
        assertEquals(0, firstPlayer.checkGamePoints());
        assertEquals(0, firstPlayer.getSetPoints());
    }

    @Test
    public void gameScoreTest(){
        firstPlayer.winsPoint();
        assertEquals(1, firstPlayer.checkGamePoints());
        firstPlayer.winsPoint();
        assertEquals(2, firstPlayer.checkGamePoints());
        firstPlayer.winsPoint();
        assertEquals(3, firstPlayer.checkGamePoints());
        firstPlayer.winsPoint();
        assertEquals(4, firstPlayer.checkGamePoints());
    }

    @Test
    public void resetGameScoreTest(){
        firstPlayer.winsPoint();
        assertEquals(1, firstPlayer.checkGamePoints());
        firstPlayer.resetGamePoints(firstPlayer,secondPlayer);
        assertEquals(0, firstPlayer.checkGamePoints());
    }

    @Test
    public void displayGameScoreTest(){
        assertEquals("0", firstPlayer.displayPoints());
        firstPlayer.winsPoint();
        assertEquals("15", firstPlayer.displayPoints());
        firstPlayer.winsPoint();
        assertEquals("30", firstPlayer.displayPoints());
        firstPlayer.winsPoint();
        assertEquals("40", firstPlayer.displayPoints());
    }

    @Test
    public void winsPointTest(){
        firstPlayer.winsPoint();
        assertEquals(1, firstPlayer.checkGamePoints());
    }

}
