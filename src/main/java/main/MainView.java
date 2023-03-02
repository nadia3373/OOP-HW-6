package main;

import utilities.ConsoleIO;
import utilities.View;

public class MainView implements View {
    public int getInt(String menu) {
        return ConsoleIO.intInput(menu);
    }
}
