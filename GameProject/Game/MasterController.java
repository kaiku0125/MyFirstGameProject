package GameProject.Game;

public class MasterController extends ControllerMain {

    public MasterController(ModelInterface model) {
        super(model);
    }

    @Override
    public void openStore() {
        getViewStoreInstance();
        this.viewStore = ViewStore.instance;
        plusCoin(50000);
        plusStone(5);
        plusExtremeStone(5);
        plusFailureTimes(10);
        plusProtectStone(10);
        plusElement(10, 10, 10, 10);
    }

    private void plusElement(int banana, int apple, int orange, int melon) {
        int e = model.getBanana();
        model.setBanana(e + banana);
        e = model.getApple();
        model.setApple(e + apple);
        e = model.getOrange();
        model.setOrange(e + orange);
        e = model.getMelon();
        model.setMelon(e + melon);
    }

    public void login() {
        viewMain = new ViewMain(this, model);
        viewMain.createMainView();
        viewMain.createControls();
        model.on();
        setGardenItem();
        setAllGdCb();
        setPlayerInformation();
        viewMain.showDialog("登入為Master@@");
    }
}
