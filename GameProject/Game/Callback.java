package GameProject.Game;

public interface Callback {
    void updateStone();

    void updateFailure();

    void enhanceProgressEnd();

    void alchemyProgressEnd();

    void updateBanana(int num);

    void updateApple();

    void updateOrange();

    void updateMelon();

}
