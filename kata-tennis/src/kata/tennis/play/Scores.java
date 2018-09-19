package kata.tennis.play;

import kata.tennis.entity.Player;

import java.util.Random;

public class Scores {

    private Player firstPlayer;
    private Player secondPlayer;

    public static final String ADVANTAGE = "ADVANTAGE";
    public static final String DEUCE = "DEUCE";

    public static String END_MATCH = "THE GAME IS OVER";

    public static String GAME_SCORE = "Game score :\n%s : %s\n%s : %s";
    public static String FULL_GAME_SCORE = "Set score :\n%s : %s\n%s : %s\n%s";

    public Scores(Player firstPlayer, Player secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void setRandomPoints() {
        int random = new Random().nextInt(2) + 1;
        switch (random){
            case 1 :
                firstPlayer.winsPoint();
                break;
            case 2 :
                secondPlayer.winsPoint();
                break;
            default: break;
        }
    }

    public String getSetPoints() {

        String gamePoint;

        if (firstPlayer.getSetPoints() == 6 && secondPlayer.getSetPoints() == 6) {
            gamePoint = checkTieBreak();
        }
        else {
            gamePoint = checkGamePoints();
        }

        Player playerWinningSet = Player.getPlayerWinningSet(firstPlayer,secondPlayer);

        if (playerWinningSet.getSetPoints() >= 6) {
            if (Math.abs(firstPlayer.getSetPoints() - secondPlayer.getSetPoints()) >= 2) {
                return displayMatchScore(playerWinningSet, firstPlayer, secondPlayer, gamePoint, true);
            }
        }
        return displayMatchScore(playerWinningSet, firstPlayer, secondPlayer, gamePoint, false);

    }

    public String checkTieBreak() {
        Player playerWinningMatch = Player.getPlayerWinningMatch(firstPlayer,secondPlayer);
        if (playerWinningMatch.checkGamePoints() >= 7 && Math.abs(firstPlayer.checkGamePoints() - secondPlayer.checkGamePoints()) >= 2) {
            int incrementSetScore = playerWinningMatch.getSetPoints();
            playerWinningMatch.setSetScore(incrementSetScore++);
            Player.resetGamePoints(firstPlayer,secondPlayer);
            return String.format(GAME_SCORE + "\n%s %s", firstPlayer.getName(), firstPlayer.checkGamePoints(), secondPlayer.getName(), secondPlayer.checkGamePoints(), playerWinningMatch.getName(), END_MATCH);
        }
        return String.format(GAME_SCORE, firstPlayer.getName(), firstPlayer.checkGamePoints(), secondPlayer.getName(), secondPlayer.checkGamePoints());
    }

    public String checkGamePoints() {

        Player playerWinningMatch;

        if (firstPlayer.checkGamePoints() >= 3 && secondPlayer.checkGamePoints() >= 3) {
            if (Math.abs(firstPlayer.checkGamePoints() - secondPlayer.checkGamePoints()) > 1) {
                playerWinningMatch = Player.getPlayerWinningMatch(firstPlayer,secondPlayer);
                int incrementSetScore = playerWinningMatch.getSetPoints();
                incrementSetScore++;
                playerWinningMatch.setSetScore(incrementSetScore);
                Player.resetGamePoints(firstPlayer,secondPlayer);
                return String.format(GAME_SCORE, firstPlayer.getName(), firstPlayer.displayPoints(), secondPlayer.getName(), secondPlayer.displayPoints());
            }
            else {
                if (firstPlayer.checkGamePoints() == secondPlayer.checkGamePoints()){
                    return DEUCE;
                }
                else {
                    playerWinningMatch = Player.getPlayerWinningMatch(firstPlayer,secondPlayer);
                    return String.format("%s %s", ADVANTAGE,playerWinningMatch.getName());
                }
            }
        }
        else {
            playerWinningMatch = Player.getPlayerWinningMatch(firstPlayer,secondPlayer);
            if (playerWinningMatch.checkGamePoints() >= 4) {
                playerWinningMatch = Player.getPlayerWinningMatch(firstPlayer,secondPlayer);
                int incrementSetScore = playerWinningMatch.getSetPoints();
                incrementSetScore++;
                playerWinningMatch.setSetScore(incrementSetScore);
                Player.resetGamePoints(firstPlayer,secondPlayer);
            }
            return String.format(GAME_SCORE, firstPlayer.getName(), firstPlayer.displayPoints(), secondPlayer.getName(), secondPlayer.displayPoints());
        }

    }

    private String displayMatchScore(Player playerWinningMatch, Player firstPlayer, Player secondPlayer, String gamePoint, boolean setEnded){
        String setEndMessage = setEnded ? String.format("\n%s %s", playerWinningMatch.getName(), END_MATCH) : "";
        return String.format(FULL_GAME_SCORE, firstPlayer.getName(), firstPlayer.getSetPoints(), secondPlayer.getName(), secondPlayer.getSetPoints(), gamePoint) + setEndMessage;
    }
}
