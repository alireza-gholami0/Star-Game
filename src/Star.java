import javax.swing.*;

public class Star extends Element {
    public static final Icon star = new ImageIcon("star.png");
    private static int starNum = 0;

    public static void setStarNum(int starNum) {
        Star.starNum = starNum;
    }

    public static int getStarNum() {
        return starNum;
    }
}
