package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements EggTimerPresenter.View{

    private static final String TAG = "MainActivity";
    // implementation MVP pattern
    private EggTimerPresenter presenter;

    private TextView countDownText;
    private long timeToCook;
    private ImageButton softBoiledButton;
    private ImageButton mediumBoiledButton;
    private ImageButton hardBoiledButton;
    private Button startupButton;
    private MediaPlayer mediaPlayer;

    //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity.
    // super is a keyword in Java. It refers to the immediate parents property. (WHY SUPER JAVA......WHYYY THAT NAMING...you aint super-dupper awesome you know)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android.R.id.content =  gives you the root element of a view, without having to know its actual name/type/ID.
        //findViewById = Finds a view that was identified by the id attribute from the XML that was processed in OnCreate(Bundle).
        this.startupButton = this.findViewById(R.id.startupButton);
        this.softBoiledButton = this.findViewById(R.id.softBoiledButton);
        this.mediumBoiledButton = this.findViewById(R.id.mediumBoiledButton);
        this.hardBoiledButton = this.findViewById(R.id.hardBoiledButton);
        this.countDownText = this.findViewById(R.id.timerTxt);

        startupButton.setOnClickListener(startOnClickListener);

        presenter = new EggTimerPresenter(this);
    }


    private final View.OnClickListener startOnClickListener = v -> startTimer();

    private final View.OnClickListener stopOnClickListener = v -> stopTimer();

    private void enableButtonEggSelected() {
        softBoiledButton.setEnabled(true);
        mediumBoiledButton.setEnabled(true);
        hardBoiledButton.setEnabled(true);
    }

    private void disableEggSelectButtons() {
        softBoiledButton.setEnabled(false);
        mediumBoiledButton.setEnabled(false);
        hardBoiledButton.setEnabled(false);
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
        countDownText.setText(R.string.TimerZero);
        presenter.stop();
        mediaPlayer.stop();
    }



    public void onButtonEggSelectedClicked(View view) {
        switch (view.getId()) {
            case R.id.softBoiledButton:
                Log.i(TAG, "onButtonEggSelectedClicked: ");
                timeToCook = 3000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            case R.id.mediumBoiledButton:
                Log.i(TAG, "onButtonEggSelectedClicked: ");
                timeToCook = 420000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            case R.id.hardBoiledButton:
                Log.i(TAG, "onButtonEggSelectedClicked: ");
                timeToCook = 540000;
                onCountDown(timeToCook);
                enableStartButton();
                break;
            default:
                throw new RuntimeException("Unknow button ID");

        }
    }

    //Display remaining int of time left in format minutes:seconds format.
        private void updateCountDownText(long time) {
            int minutes = (int) (time / 1000) / 60;
            int seconds = (int) (time / 1000) % 60;
            String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
            countDownText.setText(timeLeftFormatted);
        }


    //The @ is Java Annotations. The @Override means that the method is intended to override a method declaration in a superclass.
    @Override
    public void onCountDown(long timeLeft) {updateCountDownText(timeLeft);}

    @Override
    public void onEggTimerStopped() {
        enableButtonEggSelected();

        //play sound when egg is done
        mediaPlayer = MediaPlayer.create(this, R.raw.seal);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }
}