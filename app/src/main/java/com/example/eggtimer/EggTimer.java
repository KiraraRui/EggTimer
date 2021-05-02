package com.example.eggtimer;
import android.os.CountDownTimer;
import java.util.ArrayList;

public class EggTimer extends Thread {


    private CountDownTimer timer;

    private long remainingBoilingTime;

    private boolean isBoiling;

    private ArrayList<EggTimerListener> listeners;

    public EggTimer(long timeToCook) {
        this.remainingBoilingTime = timeToCook;
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        if (!isBoiling) {
            startTimer();
        }
    }

    public void startTimer() {
        timer = new CountDownTimer(remainingBoilingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingBoilingTime = millisUntilFinished;

                for (EggTimerListener l : listeners) {
                    l.onCountDown(remainingBoilingTime);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

        isBoiling = true;
    }

    public void stopTimer() {
        timer.cancel();
        isBoiling = false;
    }


    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }


    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }
}

