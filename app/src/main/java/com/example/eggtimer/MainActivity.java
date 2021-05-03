package com.example.eggtimer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements EggTimerPresenter.View{

    // implementation MVP pattern
    private EggTimerPresenter presenter;

    private TextView countDownText;
    private long timeToCook;
    private ImageButton softBoiledButton;
    private ImageButton mediumBoiledButton;
    private ImageButton hardBoiledButton;
    private Button startupButton;


    //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity.
    // super is a keyword in Java. It refers to the immediate parents property. (WHY SUPER JAVA......WHYYY THAT NAMING...you aint super-dupper awesome you know)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android.R.id.content =  gives you the root element of a view, without having to know its actual name/type/ID.
        //findViewById = Finds a view that was identified by the id attribute from the XML that was processed in OnCreate(Bundle).
        startupButton = findViewById(R.id.startupButton);
        softBoiledButton = findViewById(R.id.softBoiledButton);
        mediumBoiledButton = findViewById(R.id.mediumBoiledButton);
        hardBoiledButton = findViewById(R.id.hardBoiledButton);

        countDownText = findViewById(R.id.timerTxt);

        startupButton.setOnClickListener(startOnClickListener);

        presenter = new EggTimerPresenter(this);
    }


    private View.OnClickListener startOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startTimer();
        }
    };

    private View.OnClickListener stopOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stopTimer();
        }
    };

    public void onButtonEggSelectedClicked(View view) {
        switch (view.getId()) {
            case R.id.softBoiledButton:
                timeToCook = 300000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            case R.id.mediumBoiledButton:
                timeToCook = 420000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            case R.id.hardBoiledButton:
                timeToCook = 540000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            default:
                throw new RuntimeException("Unknow button ID");

        }
    }

    private void enableStartButton() {
        if (!startupButton.isEnabled()) {
            startupButton.setEnabled(true);
        }
    }

    private void startTimer() {
        startupButton.setOnClickListener(stopOnClickListener);
        startupButton.setText(R.string.stop);
        disableEggSelectButtons();
        presenter.start(timeToCook);
    }

    private void stopTimer() {
        startupButton.setOnClickListener(startOnClickListener);
        startupButton.setText(R.string.start);
        enableButtonEggSelected();
        presenter.stop();
    }

    private void disableEggSelectButtons() {
        softBoiledButton.setEnabled(true);
        mediumBoiledButton.setEnabled(true);
        hardBoiledButton.setEnabled(true);
    }


    private void enableButtonEggSelected() {
        softBoiledButton.setEnabled(true);
        mediumBoiledButton.setEnabled(true);
        hardBoiledButton.setEnabled(true);
    }



    //Display remaining int of time left in format minutes:seconds format.
    private void updateCountDownText(long time) {
        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countDownText.setText(timeLeftFormatted);
    }


    //The @ is Java Annotations. The @Override means that the method is intended to override a method declaration in a superclass.
    @Override
    public void onCountDown(long timeLeft) {updateCountDownText(timeLeft);}

    @Override
    public void onEggTimerStopped() {enableButtonEggSelected();}
}