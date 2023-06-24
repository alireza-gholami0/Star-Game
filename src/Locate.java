import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Locate extends JFrame {
    private Element[][] border = new Element[Config.row][Config.column];
    private int playerNum = Player.playerNum, starNum = Star.getStarNum(), wallNum = Wall.getWallNum(), bumpNum = Bump.getBumpNum();
    JLabel label = new JLabel("Please Placed Player (2)");

    public Locate() {
        setVisible(true);
        setBounds(0, 0, (Config.column) * Config.size + 16, (Config.row + 1) * Config.size + 40);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label.setForeground(Color.red);
        label.setBounds(5, 0, 200, 30);
        add(label);
        for (int i = 0; i < Config.row; i++) {
            for (int j = 0; j < Config.column; j++) {
                border[i][j] = new Element();
                border[i][j].setBounds((Config.size * (j)), (Config.size * (i + 1)), Config.size, Config.size);
                add(border[i][j]);
            }
        }
        for (int i = 0; i < border.length; i++) {
            for (int j = 0; j < border[i].length; j++) {
                border[i][j].addActionListener(this::actionPerformed);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (playerNum > 0) {
            for (int i = 0; i < border.length; i++) {
                for (int j = 0; j < border[i].length; j++) {
                    if (e.getSource() == border[i][j]) {
                        if (Element.border[i][j] != null) break;
                        Element.border[i][j] = new Player();
                        Element.border[i][j].setBounds(border[i][j].getBounds());
                        if (playerNum == 1) {
                            border[i][j].setIcon(Player.blue);
                            Element.border[i][j].setIcon(Player.blue);
                        } else {
                            border[i][j].setIcon(Player.red);
                            Element.border[i][j].setIcon(Player.red);
                        }
                        playerNum--;
                        if (playerNum == 0) label.setText("Please Placed Star" + " (" + starNum + ")");
                        break;
                    }
                }
            }
        } else if (starNum > 0) {
            for (int i = 0; i < border.length; i++) {
                for (int j = 0; j < border[i].length; j++) {
                    if (e.getSource() == border[i][j]) {
                        if (Element.border[i][j] != null) break;
                        Element.border[i][j] = new Star();
                        Element.border[i][j].setBounds(border[i][j].getBounds());
                        border[i][j].setIcon(Star.star);
                        Element.border[i][j].setIcon(Star.star);
                        starNum--;
                        if (starNum == 0 && wallNum == 0 && bumpNum == 0) endLocate();
                        else if (starNum == 0 && wallNum == 0)
                            label.setText("Please Placed Bump" + " (" + bumpNum + ")");
                        else if (starNum == 0) label.setText("Please Placed Wall" + " (" + wallNum + ")");
                        break;
                    }
                }
            }
        } else if (wallNum > 0) {
            for (int i = 0; i < border.length; i++) {
                for (int j = 0; j < border[i].length; j++) {
                    if (e.getSource() == border[i][j]) {
                        if (Element.border[i][j] != null) break;
                        Element.border[i][j] = new Wall();
                        Element.border[i][j].setBounds(border[i][j].getBounds());
                        border[i][j].setIcon(Wall.wall);
                        Element.border[i][j].setIcon(Wall.wall);
                        wallNum--;
                        if (wallNum == 0 && bumpNum == 0) endLocate();
                        else if (wallNum == 0) label.setText("Please Placed Bump" + " (" + bumpNum + ")");
                        break;
                    }
                }
            }
        } else if (bumpNum > 0) {
            for (int i = 0; i < border.length; i++) {
                for (int j = 0; j < border[i].length; j++) {
                    if (e.getSource() == border[i][j]) {
                        if (Element.border[i][j] != null) break;
                        else {
                            String bumpLimit = JOptionPane.showInputDialog(null, "Please Enter Bump Limit :");
                            String pattern = "[0-9]{1,}";
                            while (!bumpLimit.matches(pattern) || bumpLimit.equalsIgnoreCase("0")) {
                                bumpLimit = JOptionPane.showInputDialog(null, "Please Enter Bump Limit :");
                            }
                            int limit = Integer.parseInt(bumpLimit);
                            Element.border[i][j] = new Bump(limit);
                            Element.border[i][j].setBounds(border[i][j].getBounds());
                            border[i][j].setBackground(Color.red);
                            Element.border[i][j].setBackground(Color.red);
                            border[i][j].setText(bumpLimit);
                            Element.border[i][j].setText(bumpLimit);
                            bumpNum--;
                            if (bumpNum == 0) endLocate();
                            break;
                        }

                    }
                }
            }
        }
        updateLable();
    }

    public void endLocate() {
        for (int i = 0; i < Element.border.length; i++) {
            for (int j = 0; j < Element.border[i].length; j++) {
                if (Element.border[i][j] == null) {
                    Element.border[i][j] = border[i][j];
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Are You Ready ?", "WARNING", JOptionPane.WARNING_MESSAGE);
        setVisible(false);
        Game game = new Game();
    }

    public void updateLable() {
        if (label.getText().indexOf("Player") != -1) {
            label.setText("Please Placed Player (1)");
        } else if (label.getText().indexOf("Star") != -1) {
            label.setText("Please Placed Star (" + starNum + ")");
        } else if (label.getText().indexOf("Wall") != -1) {
            label.setText("Please Placed Wall (" + wallNum + ")");
        } else if (label.getText().indexOf("Bump") != -1) {
            label.setText("Please Placed Bump (" + bumpNum + ")");
        }
    }

}
