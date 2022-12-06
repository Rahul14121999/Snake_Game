import javax.swing.*;
import java.awt.*;

public class Game {
    JFrame frame;
    Game()
    {
        frame = new JFrame("Snake Game");
        frame.setBounds(10,10,905,700);
        Gamepanel panel = new Gamepanel();
        panel.setBounds(10,10,905,700);
        panel.setBackground(Color.gray);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String args[])
    {
        Game game = new Game();
    }
}
