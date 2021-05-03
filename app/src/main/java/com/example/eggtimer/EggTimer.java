package com.example.eggtimer;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import java.util.ArrayList;

public class EggTimer extends Thread {

    private CountDownTimer timer;

    private long remainingBoilingTime;

    private boolean boilingState;

    private ArrayList<EggTimerListener> listeners;

    public EggTimer(long timeToCook) {
        this.remainingBoilingTime = timeToCook;
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        if (!boilingState) {
            startTimer();
        }
    }

    public void startTimer() {
        timer = new CountDownTimer(remainingBoilingTime, 1000) {
            @Override
            //onTick(long) are used so that another call to onTick(long) won't ever occur before the previous callback is complete.
            public void onTick(long millisUntilFinished) {
                remainingBoilingTime = millisUntilFinished;

                for (EggTimerListener l : listeners) {
                    l.onCountDown(remainingBoilingTime);
                }
            }

            @Override
            //Callback fired when the time is up.
            public void onFinish() {

                //play airhorn
              // final MediaPlayer eggdone = MediaPlayer.create(this, R.raw.seal2);
              //  eggdone.start();
            }
        }.start();

        boilingState = true;
    }

    public void stopTimer() {
        timer.cancel(); // Cancels the countdown.
        boilingState = false;
    }


    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }


    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }
}

