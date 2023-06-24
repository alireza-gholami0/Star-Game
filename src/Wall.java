import javax.swing.*;

public class Wall extends Element {
    public static final Icon wall = new ImageIcon("wall.png");
    private static int wallNum = 0;

    public static void setWallNum(int wallNum) {
        Wall.wallNum = wallNum;
    }

    public static int getWallNum() {
        return wallNum;
    }
}
