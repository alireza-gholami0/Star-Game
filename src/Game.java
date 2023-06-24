import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.Math;

import static java.lang.Math.*;

public class Game extends JFrame {
    private int count = 0;
    private int x1, y1, x2, y2;
    private int[] blueLimits = new int[Bump.getBumpNum()];
    private int[] redLimits = new int[Bump.getBumpNum()];
    JLabel turn = new JLabel("Turns Red");
    JLabel scoreRed = new JLabel("Score Red:" + Player.getScoreRed());
    JLabel scoreBlue = new JLabel("Score Blue:" + Player.getScoreBlue());
    JLabel bumpRed = new JLabel("Red Bumps: { }");
    JLabel bumpBlue = new JLabel("Blue Bumps: { }");

    public Game() {
        setBounds(100, 100, (Config.column) * Config.size + 16, (Config.row + 2) * Config.size + 40);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        turn.setBounds(0, 0, 100, 20);
        turn.setForeground(Color.red);
        add(turn);
        scoreRed.setBounds(0, 20, 100, 20);
        scoreRed.setForeground(Color.red);
        add(scoreRed);
        scoreBlue.setBounds(100, 20, 100, 20);
        scoreBlue.setForeground(Color.blue);
        add(scoreBlue);
        bumpRed.setBounds(0, (Config.row + 1) * Config.size, (Config.column) * Config.size, 20);
        bumpRed.setForeground(Color.red);
        add(bumpRed);
        bumpBlue.setBounds(0, (Config.row + 1) * Config.size + 20, (Config.column) * Config.size, 20);
        bumpBlue.setForeground(Color.blue);
        add(bumpBlue);
        for (int i = 0; i < Element.border.length; i++) {
            for (int j = 0; j < Element.border[i].length; j++) {
                add(Element.border[i][j]);
                Element.border[i][j].addActionListener(this::actionPerformed);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (count == 0) {
            for (int i = 0; i < Bump.getBumpNum(); i++) {
                redLimits[i] = 0;
                blueLimits[i] = 0;
            }
        }
        for (int i = 0; i < Element.border.length; i++) {
            for (int j = 0; j < Element.border[i].length; j++) {
                if (e.getSource() == Element.border[i][j]) {
                    x2 = i;
                    y2 = j;
                    break;
                }
            }
        }
        for (int i = 0; i < Element.border.length; i++) {
            for (int j = 0; j < Element.border[i].length; j++) {
                if (Element.border[i][j] instanceof Player) {
                    if (count % 2 == 0 && Element.border[i][j].getIcon() == Player.red) {
                        x1 = i;
                        y1 = j;
                        break;
                    } else if (count % 2 == 1 && Element.border[i][j].getIcon() == Player.blue) {
                        x1 = i;
                        y1 = j;
                        break;
                    }
                }
            }
        }

        if (!move()) JOptionPane.showMessageDialog(null, "The move is wrong !!!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        if (Star.getStarNum() == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (Player.getScoreBlue() > Player.getScoreRed()) {
                JOptionPane.showMessageDialog(null, "Win Blue Player !!!", "Finish", JOptionPane.WARNING_MESSAGE);
                setVisible(false);
                System.exit(1);
            } else if (Player.getScoreBlue() < Player.getScoreRed()) {
                JOptionPane.showMessageDialog(null, "Win Red Player !!!", "Finish", JOptionPane.WARNING_MESSAGE);
                setVisible(false);
                System.exit(1);
            } else {
                JOptionPane.showMessageDialog(null, "Equal Match !!!", "Finish", JOptionPane.WARNING_MESSAGE);
                setVisible(false);
                System.exit(1);
            }
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setElement(int x, int y) {
        Element element = new Element();
        element.setBounds(Element.border[x][y].getBounds());
        element.addActionListener(this::actionPerformed);
        remove(Element.border[x][y]);
        Element.border[x][y] = new Element();
        Element.border[x][y] = element;
        add(Element.border[x][y]);
    }

    public void updateLimits(int l) {
        if (count % 2 == 0) {
            for (int i = 0; i < blueLimits.length; i++) {
                if (blueLimits[i] == 0) {
                    blueLimits[i] = l;
                    break;
                }
            }
        } else {
            for (int i = 0; i < redLimits.length; i++) {
                if (redLimits[i] == 0) {
                    redLimits[i] = l;
                    break;
                }
            }
        }
    }

    public boolean checkBump() {
        if (count % 2 == 0 && redLimits[0] == 0) return true;
        else if (count % 2 == 1 && blueLimits[0] == 0) return true;
        else if (x1 == x2 && count % 2 == 0 && Math.abs(y1 - y2) <= redLimits[0]) {
            return true;
        } else if (y1 == y2 && count % 2 == 0 && Math.abs(x1 - x2) <= redLimits[0]) {
            return true;
        } else if (x1 == x2 && count % 2 == 1 && Math.abs(y1 - y2) <= blueLimits[0]) {
            return true;
        } else if (y1 == y2 && count % 2 == 1 && Math.abs(x1 - x2) <= blueLimits[0]) {
            return true;
        } else return false;
    }

    public void sortLimts() {
        if (count % 2 == 0 && redLimits[0] != 0) {
            for (int i = 0; i < (redLimits.length - 1); i++) {
                redLimits[i] = redLimits[i + 1];
            }
            redLimits[redLimits.length - 1] = 0;
        } else if (count % 2 == 1 && blueLimits[0] != 0) {
            for (int i = 0; i < (blueLimits.length - 1); i++) {
                blueLimits[i] = blueLimits[i + 1];
            }
            blueLimits[blueLimits.length - 1] = 0;
        }
    }

    public boolean move() {
        if (x1 != x2 && y1 != y2) {
            return false;
        } else {
            if (Element.border[x2][y2] instanceof Player || Element.border[x2][y2] instanceof Wall) {
                return false;
            } else if (Bump.getBumpNum() > 0) {
                if (!checkBump()) return false;
                else {
                    if (x1 == x2) {
                        if (y2 > y1) {
                            for (int i = y2; i >= y1; i--) {
                                if (Element.border[x1][i] instanceof Wall) return false;
                                else if (Element.border[x1][i] instanceof Bump) {
                                    updateLimits(Element.border[x1][i].getLimit());
                                    setElement(x1, i);
                                    UbdateLable();
                                } else if (Element.border[x1][i] instanceof Star) {
                                    setElement(x1, i);
                                    if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                    else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                    Star.setStarNum(Star.getStarNum() - 1);
                                    UbdateLable();
                                }
                            }
                        } else {
                            for (int i = y1; i >= y2; i--) {
                                if (Element.border[x1][i] instanceof Wall) return false;
                                else if (Element.border[x1][i] instanceof Bump) {
                                    updateLimits(Element.border[x1][i].getLimit());
                                    setElement(x1, i);
                                    UbdateLable();
                                } else if (Element.border[x1][i] instanceof Star) {
                                    setElement(x1, i);
                                    if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                    else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                    Star.setStarNum(Star.getStarNum() - 1);
                                    UbdateLable();
                                }
                            }
                        }
                    } else {
                        if (x2 > x1) {
                            for (int i = x2; i >= x1; i--) {
                                if (Element.border[i][y1] instanceof Wall) return false;
                                else if (Element.border[i][y1] instanceof Bump) {
                                    updateLimits(Element.border[i][y1].getLimit());
                                    setElement(i, y1);
                                    UbdateLable();
                                } else if (Element.border[i][y1] instanceof Star) {
                                    setElement(i, y1);
                                    if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                    else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                    Star.setStarNum(Star.getStarNum() - 1);
                                    UbdateLable();
                                }
                            }
                        } else {
                            for (int i = x1; i >= x2; i--) {
                                if (Element.border[i][y1] instanceof Wall) return false;
                                else if (Element.border[i][y1] instanceof Bump) {
                                    updateLimits(Element.border[i][y1].getLimit());
                                    setElement(i, y1);
                                    UbdateLable();
                                } else if (Element.border[i][y1] instanceof Star) {
                                    setElement(i, y1);
                                    if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                    else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                    Star.setStarNum(Star.getStarNum() - 1);
                                    UbdateLable();
                                }
                            }
                        }
                    }
                    sortLimts();
                    updateBorder();
                    UbdateLable();
                    return true;
                }
            } else {
                if (x1 == x2) {
                    if (y2 > y1) {
                        for (int i = y1; i <= y2; i++) {
                            if (Element.border[x1][i] instanceof Wall) return false;
                            else if (Element.border[x1][i] instanceof Star) {
                                setElement(x1, i);
                                if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                Star.setStarNum(Star.getStarNum() - 1);
                                UbdateLable();
                            }
                        }
                    } else {
                        for (int i = y2; i <= y1; i++) {
                            if (Element.border[x1][i] instanceof Wall) return false;
                            else if (Element.border[x1][i] instanceof Star) {
                                setElement(x1, i);
                                if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                Star.setStarNum(Star.getStarNum() - 1);
                                UbdateLable();
                            }
                        }
                    }
                } else {
                    if (x2 > x1) {
                        for (int i = x1; i <= x2; i++) {
                            if (Element.border[i][y1] instanceof Wall) return false;
                            else if (Element.border[i][y1] instanceof Star) {
                                setElement(i, y1);
                                if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                Star.setStarNum(Star.getStarNum() - 1);
                                UbdateLable();
                            }
                        }
                    } else {
                        for (int i = x2; i <= x1; i++) {
                            if (Element.border[i][y1] instanceof Wall) return false;
                            else if (Element.border[i][y1] instanceof Star) {
                                setElement(i, y1);
                                if (count % 2 == 0) Player.setScoreRed(Player.getScoreRed() + 1);
                                else Player.setScoreBlue(Player.getScoreBlue() + 1);
                                Star.setStarNum(Star.getStarNum() - 1);
                                UbdateLable();
                            }
                        }
                    }
                }
                updateBorder();
                UbdateLable();
                return true;
            }
        }
    }

    public void updateBorder() {
        Element e1 = new Element();
        Element e2 = new Player();
        e1.setBounds(Element.border[x1][y1].getBounds());
        e2.setBounds(Element.border[x2][y2].getBounds());
        e1.addActionListener(this::actionPerformed);
        e2.addActionListener(this::actionPerformed);
        if (count % 2 == 0) e2.setIcon(Player.red);
        else e2.setIcon(Player.blue);
        remove(Element.border[x1][y1]);
        remove(Element.border[x2][y2]);
        Element.border[x1][y1] = new Element();
        Element.border[x2][y2] = new Player();
        Element.border[x1][y1] = (Element) e1;
        Element.border[x2][y2] = (Player) e2;
        add(Element.border[x2][y2]);
        add(Element.border[x1][y1]);
        count++;
    }

    public void UbdateLable() {
        if (count % 2 == 0) {
            turn.setText("Turns Red");
            turn.setForeground(Color.red);
            scoreRed.setText("Score Red:" + Player.getScoreRed());
            bumpBlue.setText(bumpsToString());
        } else {
            turn.setText("Turns Blue");
            turn.setForeground(Color.blue);
            scoreBlue.setText("Score Blue:" + Player.getScoreBlue());
            bumpRed.setText(bumpsToString());
        }
    }

    public String bumpsToString() {
        if (count % 2 == 0) {
            String output = "Blue Bumps: { ";
            for (int i = 0; i < blueLimits.length; i++) {
                if (blueLimits[i] != 0) {
                    output += blueLimits[i];
                    output += " ";
                }
            }
            output += "}";
            return output;
        } else {
            String output = "Red Bumps: { ";
            for (int i = 0; i < redLimits.length; i++) {
                if (redLimits[i] != 0) {
                    output += redLimits[i];
                    output += " ";
                }
            }
            output += "}";
            return output;
        }
    }
}
