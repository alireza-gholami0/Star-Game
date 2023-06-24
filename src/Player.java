import javax.swing.*;

public class Player extends Element {
    public static final int playerNum = 2;
    public static final Icon blue = new ImageIcon("blue.png");
    public static final Icon red = new ImageIcon("red.png");
    private static int scoreBlue = 0, scoreRed = 0;

    public static void setScoreBlue(int scoreBlue) {
        Player.scoreBlue = scoreBlue;
    }

    public static int getScoreBlue() {
        return scoreBlue;
    }

    public static void setScoreRed(int scoreRed) {
        Player.scoreRed = scoreRed;
    }

    public static int getScoreRed() {
        return scoreRed;
    }
}
