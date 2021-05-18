package GameProject.Game;

public class GameDrive {
    public static void main(String[] args) {
        // boolean ismaster = true;
        boolean ismaster = false;
        ModelInterface model = new Model();
        if (ismaster) {
            ControllerMainInterface masterController = new MasterController(model);
        } else {
            ControllerMainInterface controller = new ControllerMain(model);
        }
    }
}
