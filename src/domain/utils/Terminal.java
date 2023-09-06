package domain.utils;

public class Terminal {
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
