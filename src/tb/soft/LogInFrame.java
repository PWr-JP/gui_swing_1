package tb.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInFrame extends JFrame implements ActionListener {
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 350;

    private final JLabel logInLabel = new JLabel("Type in an username and a password");
    private final JLabel userNameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");
    private final JLabel isLoggedInLabel = new JLabel("");

    private final JButton clearButton = new JButton("Cancel");
    private final JButton logInButton = new JButton("Log in");
    private final JButton signUpButton = new JButton("Sing up");

    private final JTextField userNameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private static ArrayList<User> users = new ArrayList<User>();
    private RegisterFrame registerFrame;

    public LogInFrame() {
        JFrame logInWindow = new JFrame();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setTitle("Log in");
        //this.getContentPane().setBackground(new Color(0x554B49));

        this.setLayout(null);

        signUpButton.addActionListener(this);
        signUpButton.setBounds(240, 15, 85, 25);
        this.add(signUpButton);

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

        logInButton.addActionListener(this);
        logInButton.setBounds(75, 200, 90, 40);
        this.add(logInButton);

        clearButton.addActionListener(this);
        clearButton.setBounds(185, 200, 90, 40);
        this.add(clearButton);

        isLoggedInLabel.setBounds(WINDOW_WIDTH/2 - isLoggedInLabel.getText().length()*3-5,
                270,
                300, 20);
        isLoggedInLabel.setVisible(false);
        this.add(isLoggedInLabel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton)
            registerFrame = new RegisterFrame();

        if (e.getSource() == logInButton) {
            if (isUserInDataBase()){
                clearFields();
                displayInfo("You've just logged in", Color.green);
            }
            else {
                passwordField.setText("");
                displayInfo("Logging in failed.", Color.red);
            }
        }

        if (e.getSource() == clearButton)
            clearFields();
    }

    private boolean isUserInDataBase() {
        for (User user : users)
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

    private void clearFields() {
        userNameField.setText("");
        passwordField.setText("");
    }

    public void displayInfo(String info, Color textColor) {
        isLoggedInLabel.setText(info);
        isLoggedInLabel.setForeground(textColor);
        isLoggedInLabel.setBounds(WINDOW_WIDTH/2 - isLoggedInLabel.getText().length()*3-5,
                270,
                300, 20);
        isLoggedInLabel.setVisible(true);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
