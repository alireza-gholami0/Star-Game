import javax.swing.*;

public class Config {
    public static int size=60;
    public static int row;
    public static int column;

    public static void setSize(){
        if(row<=7) size=60;
        else if(row<=13) size=45;
        else if(row<=16) size=35;
    }

}
