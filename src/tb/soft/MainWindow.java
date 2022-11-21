package tb.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame{
    private final UserManager userManager = new UserManager();
    private static MainWindow currentFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    currentFrame = frame;
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public MainWindow() throws HeadlessException {
        this(Constants.MainWindowTitle);
    }

    public MainWindow(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.MainWindowWidth, Constants.MainWindowHeight);
        setResizable(false);

        JPanel content = new JPanel();
        content.setBounds(Constants.WindowPadding,
                Constants.WindowPadding,
                Constants.MainWindowWidth - 2 * Constants.WindowPadding,
                Constants.MainWindowHeight - 2 * Constants.WindowPadding);
        setContentPane(content);
        content.setLayout(null);

        JButton signIn = new JButton("Sign In");
        JButton signUp = new JButton("Sign Up");
        JButton close = new JButton("Close");

        JTextField login = new JTextField();
        JPasswordField password = new JPasswordField();

        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        JLabel signInResult = new JLabel();

        content.add(signIn);
        content.add(signUp);
        content.add(close);

        content.add(login);
        content.add(password);

        content.add(loginLabel);
        content.add(passwordLabel);
        content.add(signInResult);

        close.addActionListener(e -> {
            currentFrame.dispatchEvent(new WindowEvent(currentFrame, WindowEvent.WINDOW_CLOSING));
        });

        signUp.addActionListener(e -> {
            var dialog = new SignUpWindow();
            dialog.setVisible(true);
        });

        signIn.addActionListener(e -> {
            var isSignInValid = userManager.isSignInValid(new CredentialsDto(login.getText(),
                    new String(password.getPassword()).hashCode()));

            signInResult.setVisible(true);

            if (!isSignInValid) {
                signInResult.setForeground(Constants.ErrorColor);
                signInResult.setText("Incorrect password or login.");
                return;
            }

            signInResult.setForeground(Constants.SuccessColor);
            signInResult.setText("Signed in successfully.");
        });

        close.setBounds(Constants.WindowPadding,
                Constants.MainWindowHeight - (Constants.WindowPadding + 2 * Constants.ButtonHeight),
                Constants.ButtonWidth,
                Constants.ButtonHeight);

        signUp.setBounds(Constants.WindowPadding + Constants.ButtonWidth + Constants.WindowPadding,
                Constants.MainWindowHeight - (Constants.WindowPadding + 2 * Constants.ButtonHeight),
                Constants.ButtonWidth,
                Constants.ButtonHeight);

        signIn.setBounds(Constants.WindowPadding + 2 * (Constants.ButtonWidth + Constants.WindowPadding),
                Constants.MainWindowHeight - (Constants.WindowPadding + 2 * Constants.ButtonHeight),
                Constants.ButtonWidth,
                Constants.ButtonHeight);

        loginLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        login.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                Constants.WindowPadding,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        passwordLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                2 * Constants.WindowPadding + Constants.ButtonHeight - Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        password.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                2 * Constants.WindowPadding + Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        signInResult.setVisible(false);
        signInResult.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                3 * Constants.WindowPadding + 2 * Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);
    }
}
