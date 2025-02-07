package roy.batterydata;

/*
 App class encapsulates the MainApp, so it can run as a standalone .jar file
 (per YouTube tutorial on how to package a javaFX app as a standalone
 --When packaging, newer versions of java don't like any "main" class that extends application)
 https://www.youtube.com/watch?v=F8ahBtXkQzU
*/

public class App {
    public static void main(String[] args){
        MainApp.main(args);
    }
}
