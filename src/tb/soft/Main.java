package tb.soft;

import java.util.HashMap;

public class Main {
    private static HashMap<String, char[]> usersInfo = new HashMap<String, char[]>();

    public static boolean addUser(String user, char[] password){
        if(userExists(user) == false)
        {
            usersInfo.put(user, password);
            return true;
        }
        else return false;
    }

    public static boolean userExists(String user){
        if(usersInfo.containsKey(user)){
            return true;
        }
        else return false;
    }

    public static boolean isCorrect(String user, char[] password){
        // porownanie wszystkich znakow
        if(usersInfo.get(user).length == password.length){
            for(int i = 0; i < password.length; i++){
                if(usersInfo.get(user)[i] != password[i]){
                    return false;
                }
            }
            return true;
        }
        else return false;
    }

    public static void main(String[] args)
    {
        // dodanie podstawowych danych
        String un = "Jakub";
        char[] ps = "Lazorko".toCharArray();
        usersInfo.put(un, ps);
        un = "user";
        ps = "test".toCharArray();
        usersInfo.put(un, ps);
        un = null;
        ps = null;

        MainWindow w = new MainWindow();
    }
}
