import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Data extends JFrame {
    int row,column,star,wall,bump;
    JLabel[] label = new JLabel[5];
    JTextField[] text = new JTextField[5];
    JButton next = new JButton("Next");

    public Data() {
        setBounds(100, 100, 160, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label[0] = new JLabel("Border Row :");
        label[0].setBounds(10, 10, 150, 30);
        label[1] = new JLabel("Border Column :");
        label[1].setBounds(10, 45, 150, 30);
        label[2] = new JLabel("Star number :");
        label[2].setBounds(10, 80, 150, 30);
        label[3] = new JLabel("Wall number :");
        label[3].setBounds(10, 115, 150, 30);
        label[4] = new JLabel("Bump number :");
        label[4].setBounds(10, 150, 150, 30);
        for (int i = 0; i < label.length; i++) {
            add(label[i]);
        }
        next.setBounds(40, 220, 60, 30);
        add(next);
        text[0] = new JTextField();
        text[0].setBounds(100, 10, 40, 30);
        text[1] = new JTextField();
        text[1].setBounds(100, 45, 40, 30);
        text[2] = new JTextField();
        text[2].setBounds(100, 80, 40, 30);
        text[3] = new JTextField();
        text[3].setBounds(100, 115, 40, 30);
        text[4] = new JTextField();
        text[4].setBounds(100, 150, 40, 30);
        for (int i = 0; i < text.length; i++) {
            text[i].setText("0");
            add(text[i]);
        }
        next.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        if(checkInput()) {
            row = Integer.parseInt(text[0].getText());
            column = Integer.parseInt(text[1].getText());
            star = Integer.parseInt(text[2].getText());
            wall = Integer.parseInt(text[3].getText());
            bump = Integer.parseInt(text[4].getText());
            if(row>0&&column>0&& star>0 && wall>=0&& bump>=0 && star+bump+wall<=(column*row)-2){
                Config.row = row;
                Config.column= column;
                Star.setStarNum(star);
                Wall.setWallNum(wall);
                Bump.setBumpNum(bump);
                Config.setSize();
                Element.border = new Element[row][column];
                setVisible(false);
                Locate locate = new Locate();
            }
            else{
                JOptionPane.showMessageDialog(null,"Inputs are incorrect !!!","ERROR!",JOptionPane.ERROR_MESSAGE);
                setVisible(false);
                Data data = new Data();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Inputs are incorrect !!!","ERROR!",JOptionPane.ERROR_MESSAGE);
            setVisible(false);
            Data data = new Data();
        }
    }

    public boolean checkInput() {
    String pattern = "[0-9]{1,}";
        for (int i = 0; i < text.length; i++) {
            if(!text[i].getText().matches(pattern)) return false;
        }
        return true;
    }

}
