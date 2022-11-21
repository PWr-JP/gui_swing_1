package tb.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 350;

    private final JLabel logInLabel = new JLabel("Type in an username and a password");
    private final JLabel userNameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");
    public final JLabel isRegisteredLabel = new JLabel("");

    private final JButton registerButton = new JButton("Register");

    private final JTextField userNameField = new JTextField("");
    private final JPasswordField passwordField = new JPasswordField("");

    public RegisterFrame() {
        JFrame logInWindow = new JFrame();
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setTitle("Sign up");
        //this.getContentPane().setBackground(new Color(0x554B49));

        this.setLayout(null);
        logInLabel.setBounds(WINDOW_WIDTH/2 - logInLabel.getText().length()*3-5,
                60,
                300, 20);
        this.add(logInLabel);

        userNameField.setBounds(75, 90, 200, 25);
        this.add(userNameField);

        userNameLabel.setBounds(WINDOW_WIDTH/2 - userNameLabel.getText().length()*3-5,
                110,
                300, 20);
        this.add(userNameLabel);

        passwordField.setBounds(75, 145, 200, 25);
        this.add(passwordField);

        passwordLabel.setBounds(WINDOW_WIDTH/2 - passwordLabel.getText().length()*3-5,
                165,
                300, 20);
        this.add(passwordLabel);

        registerButton.addActionListener(this);
        registerButton.setBounds(75, 200, 200, 40);
        this.add(registerButton);

        isRegisteredLabel.setBounds(WINDOW_WIDTH/2 - isRegisteredLabel.getText().length()*3-5,
                270,
                300, 20);
        isRegisteredLabel.setVisible(false);
        this.add(isRegisteredLabel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            if (areFieldsEmpty() || isUserInDataBase())
                displayInfo("Registration failed", Color.red);
            else {
                displayInfo("You've just registered", Color.green);
                LogInFrame.getUsers().add( new User(userNameField.getText(), passwordField.getPassword()) );
            }

        }
    }

    private boolean areFieldsEmpty() {
        return userNameField.getText().length() == 0 || passwordField.getPassword().length == 0;
    }

    private boolean isUserInDataBase() {
        for (User user : LogInFrame.getUsers())
            if (userNameField.getText().equals(user.getUsername())
                    && isPasswordCorrect(user.getPassword()))
                return true;

        return false;
    }

    private boolean isPasswordCorrect(char[] password) {
        if (passwordField.getPassword().length != password.length)
            return false;

        for (int i = 0; i < password.length; ++i)
            if (passwordField.getPassword()[i] != password[i])
                return false;
        return true;
    }

    public void displayInfo(String info, Color textColor) {
        isRegisteredLabel.setText(info);
        isRegisteredLabel.setForeground(textColor);
        isRegisteredLabel.setBounds(WINDOW_WIDTH/2 - isRegisteredLabel.getText().length()*3-5,
                270,
                300, 20);
        isRegisteredLabel.setVisible(true);
    }
}
