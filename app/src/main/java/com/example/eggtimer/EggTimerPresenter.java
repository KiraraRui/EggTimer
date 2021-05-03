package com.example.eggtimer;

public class EggTimerPresenter implements EggTimerListener {

    public interface View {
        void onCountDown(long time);

        void onEggTimerStopped();

    }

    private EggTimer eggTimer;

    private View view;

    public EggTimerPresenter(View view) {
        this.view = view;
    }


    public void start(long timeToCook) {
        eggTimer = new EggTimer(timeToCook);
        eggTimer.addListener(this);
        eggTimer.run();
    }

    public void stop() {
        eggTimer.removeListener(this);
        eggTimer.stopTimer();
    }

    //I have used override here as onCOuntDown and onEggTierstopped needs a behavior that is dif then originally defined
    @Override
    public void onCountDown(long timeLeft) {view.onCountDown(timeLeft); }

    @Override
    public void onEggTimerStopped() {
        eggTimer.removeListener(this);
        view.onEggTimerStopped();
    }
}
