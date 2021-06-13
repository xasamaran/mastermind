package main.java.fr.teemo.mastermind;

public class Main {

    public static void main(String[] args) {
        AppService appService = new AppService();
        try {
            appService.launchApp();
        } catch (Exception e) {
            System.out.println("Unhandled Exception" + e);
        }
    }
}
