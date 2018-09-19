package kata.tennis.entity;

import java.util.Arrays;
import java.util.List;

public class Player {

    public static final List<String> SCORE_TO_DISPLAY = Arrays.asList("0", "15", "30", "40");

    private String name;
    private int gameScore;
    private int setScore;

    public Player(String name) {
        this.name = name;
        this.setScore = 0;
        this.gameScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int checkGamePoints() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getSetPoints() {
        return setScore;
    }

    public void setSetScore(int setScore) {
        this.setScore = setScore;
    }

    public void winsPoint(){
        this.gameScore++;
    }

    public static Player getPlayerWinningMatch(Player firstPlayer, Player secondPlayer) {
        return firstPlayer.checkGamePoints() >= secondPlayer.checkGamePoints() ? firstPlayer : secondPlayer;
    }

    public static Player getPlayerWinningSet(Player firstPlayer, Player secondPlayer) {
        return firstPlayer.getSetPoints() >= secondPlayer.getSetPoints() ? firstPlayer : secondPlayer;
    }

    public static void resetGamePoints(Player firstPlayer, Player secondPlayer) {
        firstPlayer.setGameScore(0);
        secondPlayer.setGameScore(0);
    }

    public String displayPoints(){
        return SCORE_TO_DISPLAY.get(this.gameScore);
    }
}
