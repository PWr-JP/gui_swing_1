package tb.soft;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SignUpWindow extends JFrame {
    private static final UserManager userManager = new UserManager();
    private static final Border textFieldDefaultBorder = UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border");
    private static final Border passwordFieldDefaultBorder = UIManager.getLookAndFeel().getDefaults().getBorder("PasswordField.border");

    public SignUpWindow() throws HeadlessException {
        this(Constants.SignUpWindowTitle);
    }

    public SignUpWindow(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(Constants.SignUpWindowWidth, Constants.SignUpWindowHeight);
        setResizable(false);

        JPanel content = new JPanel();
        content.setBounds(Constants.WindowPadding,
                Constants.WindowPadding,
                Constants.SignUpWindowWidth - 2 * Constants.WindowPadding,
                Constants.SignUpWindowHeight - 2 * Constants.WindowPadding);
        setContentPane(content);
        content.setLayout(null);

        JTextField name = new JTextField();
        JTextField surname = new JTextField();
        JTextField login = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField confirmPassword = new JPasswordField();

        JLabel nameLabel = new JLabel("Name");
        JLabel surnameLabel = new JLabel("Surname");
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        JLabel confirmPasswordLabel = new JLabel("Confirm password");

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        content.add(name);
        content.add(surname);
        content.add(login);
        content.add(password);
        content.add(confirmPassword);

        content.add(nameLabel);
        content.add(surnameLabel);
        content.add(loginLabel);
        content.add(passwordLabel);
        content.add(confirmPasswordLabel);

        content.add(signUpButton);
        content.add(backButton);

        backButton.setBounds(Constants.WindowPadding,
                Constants.SignUpWindowHeight - (Constants.WindowPadding + 2 * Constants.ButtonHeight),
                Constants.ButtonWidth,
                Constants.ButtonHeight);

        signUpButton.setBounds(Constants.WindowPadding + 2 * (Constants.ButtonWidth + Constants.WindowPadding),
                Constants.SignUpWindowHeight - (Constants.WindowPadding + 2 * Constants.ButtonHeight),
                Constants.ButtonWidth,
                Constants.ButtonHeight);

        nameLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        name.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                Constants.WindowPadding,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        surnameLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                2 * Constants.WindowPadding + Constants.ButtonHeight - Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        surname.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                2 * Constants.WindowPadding + Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        loginLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                3 * Constants.WindowPadding + 2 * Constants.ButtonHeight - Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        login.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                3 * Constants.WindowPadding + 2 * Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        passwordLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                4 * Constants.WindowPadding + 3 * Constants.ButtonHeight - Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        password.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                4 * Constants.WindowPadding + 3 * Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        confirmPasswordLabel.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                5 * Constants.WindowPadding + 4 * Constants.ButtonHeight - Constants.WindowPadding / 2,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        confirmPassword.setBounds(Constants.WindowPadding + Constants.ButtonWidth,
                5 * Constants.WindowPadding + 4 * Constants.ButtonHeight,
                Constants.ButtonWidth + 2 * Constants.WindowPadding,
                Constants.ButtonHeight);

        signUpButton.addActionListener(e -> {

            var isNameEmpty = isTextFieldEmpty(name);
            var isSurnameEmpty = isTextFieldEmpty(surname);
            var isLoginEmpty = isTextFieldEmpty(login);
            var isPasswordFieldsMatch = isPasswordFieldsMatch(password, confirmPassword);
            var isPasswordMatch = isPasswordFieldEmpty(password);
            var isConfirmPasswordMatch = isPasswordFieldEmpty(confirmPassword);

            if (isTextFieldEmpty(name) ||
                    isTextFieldEmpty(surname) ||
                    isTextFieldEmpty(login) ||
                    isPasswordFieldEmpty(password) ||
                    isPasswordFieldEmpty(confirmPassword) ||
                    !isPasswordFieldsMatch(password, confirmPassword))
                return;

            userManager.addUser(new AddUserDto(name.getText(),
                    surname.getText(),
                    login.getText(),
                    new String(password.getPassword()).hashCode()));

            this.setVisible(false);
        });

        backButton.addActionListener(e -> {
            name.setText("");
            surname.setText("");
            login.setText("");
            password.setText("");
            confirmPassword.setText("");

            this.setVisible(false);
        });
    }

    private static boolean isTextFieldEmpty(JTextField textField) {
        var isTextFieldEmpty = textField.getText().isBlank();

        if (isTextFieldEmpty)
            textField.setBorder(new LineBorder(Constants.ErrorColor, 1));
        else
            textField.setBorder(textFieldDefaultBorder);

        return isTextFieldEmpty;
    }

    private static boolean isPasswordFieldEmpty(JPasswordField passwordField) {
        var isPasswordFieldEmpty = new String(passwordField.getPassword()).isBlank();

        if (isPasswordFieldEmpty)
            passwordField.setBorder(new LineBorder(Constants.ErrorColor, 1));
        else
            passwordField.setBorder(passwordFieldDefaultBorder);

        return isPasswordFieldEmpty;
    }

    private static boolean isPasswordFieldsMatch(JPasswordField passwordField, JPasswordField confirmPasswordField) {
        var isPasswordFieldsMatch = new String(passwordField.getPassword()).hashCode() == new String(confirmPasswordField.getPassword()).hashCode();

        if (isPasswordFieldsMatch)
        {
            passwordField.setBorder(new LineBorder(Constants.ErrorColor, 1));
            confirmPasswordField.setBorder(new LineBorder(Constants.ErrorColor, 1));
        }
        else
        {
            passwordField.setBorder(passwordFieldDefaultBorder);
            confirmPasswordField.setBorder(passwordFieldDefaultBorder);
        }

        return isPasswordFieldsMatch;
    }
}
