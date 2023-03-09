import utils.userinterface.UserInterface;

public class Inka {
    private final UserInterface ui;
    public Inka() {
        ui = new UserInterface();
    }

    public void run() {
        ui.printGreeting();

        //while parser.execute is running code should be placed here,
        // String fullCommand = ui.getCommand();
        ui.printBye();
    }

    public static void main(String[] args) {
        new Inka().run();
    }
}
