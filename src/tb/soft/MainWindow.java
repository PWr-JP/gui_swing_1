package tb.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
* okienko:
* tytuł
* nazwa użytkownika
* hasło - nie widoczne na ekranie
* przyciski - ok, cancel, register
*
* dobrze - zielony kolor
* źle - czerwony kolor
*
* event dispatching thread - EDP
* */
/**
 * klasa główna zawierająca metodę statyczną main
 */
public class MainWindow extends JFrame{
    //JFrame f;

    MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        JButton register = new JButton("Register");
        ok.setBounds(40, 80, 100,40);
        cancel.setBounds(40,130, 100,40);
        register.setBounds(40, 180, 100, 40);

        final JTextField userName = new JTextField();
        final JPasswordField password = new JPasswordField();
        userName.setBounds(160,80,80,40);
        password.setBounds(160,150,80,40);

        JLabel lUser,lPassword;
        lUser = new JLabel("User name");
        lUser.setBounds(160, 60, 80,20);
        lPassword = new JLabel("Password");
        lPassword.setBounds(160, 130, 80,20);

        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // sprawdzić pola userName i password
                if(Main.userExists(userName.getText())){
                    if(Main.isCorrect(userName.getText(), password.getPassword())) {
                        // wypisać na zielono
                        getContentPane().setBackground(Color.GREEN);
                        System.out.println("Dobre haslo");
                    }
                    else{
                        // wypisac ze zle/czerwony kolor
                        getContentPane().setBackground(Color.red);
                        System.out.println("Zle dane");
                    }
                }
                else{
                    // wypisac ze zle/czerwony kolor
                    getContentPane().setBackground(Color.red);
                    System.out.println("Zle dane");
                }
            }
        });
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // clear pola?
                userName.setText("");
                password.setText("");
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dodaj użytkownika
                String un = userName.getText();
                char[] ps = password.getPassword();
                userName.setText("");
                password.setText("");
                if(Main.addUser(un, ps)){
                    JFrame pop = new JFrame("Success");
                    pop.setBounds(getLocation().x + (getWidth()-300)/2, getLocation().y + (getHeight()-100)/2, 300, 100);

                    JLabel success = new JLabel("User created!");
                    success.setBounds((pop.getWidth() - 78)/2 , pop.getHeight()/2 - 40,78,40);

                    pop.getContentPane().setBackground(Color.green);
                    pop.add(success);
                    pop.setLayout(null);
                    pop.setVisible(true);
                }
                else{
                    JFrame pop = new JFrame("Failure");
                    pop.setBounds(getLocation().x + (getWidth()-300)/2, getLocation().y + (getHeight()-100)/2, 300, 100);

                    JLabel failure = new JLabel("User exists!");
                    failure.setBounds((pop.getWidth() - 68)/2, pop.getHeight()/2 - 40,68,40);

                    pop.getContentPane().setBackground(Color.red);
                    pop.add(failure);
                    pop.setLayout(null);
                    pop.setVisible(true);
                }
                un = null;
                ps = null;
            }
        });

        setBounds(660,240,400,400);
        add(ok);
        add(cancel);
        add(register);

        add(userName);
        add(password);

        add(lUser);
        add(lPassword);

        setLayout(null);
        setVisible(true);
        setTitle("Log in");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }
}
