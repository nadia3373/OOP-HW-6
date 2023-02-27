package utilities;

public abstract class SubApp extends App {
    protected MainApp app;
    protected SubApp(MainApp app, String menu) {
        super(menu);
        this.app = app;
    }

    protected abstract void option(int choice);

    protected abstract void read();

    protected abstract void save();
}
