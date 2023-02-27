package utilities;

public abstract class App {
    protected String menu;
    protected App(String menu) {
        this.menu = menu;
    }

    protected abstract void option(int choice);

    public void start() {
        int choice;
        do {
            choice = ConsoleIO.intInput(menu);
            option(choice);
        } while (choice != 0);
    }
}
