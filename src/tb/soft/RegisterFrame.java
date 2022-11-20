package tb.soft;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame{
    public static final int WINDOW_WIDTH = 350;
    public static final int WINDOW_HEIGHT = 350;

    public RegisterFrame() {
        JFrame logInWindow = new JFrame();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setTitle("Okienko logowania");
        this.getContentPane().setBackground(new Color(0x554B49));

        this.setVisible(true);
    }
}
