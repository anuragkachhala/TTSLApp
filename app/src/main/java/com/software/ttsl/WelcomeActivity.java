package com.software.ttsl;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.software.ttsl.Utils.MutedVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = WelcomeActivity.class.getName();

    @BindView(R.id.video_view)
    MutedVideoView mutedVideoView;

    @BindView(R.id.layout_track_cargo)
    LinearLayout layoutTrackCargo;

    @BindView(R.id.layout_sailing_schedule)
    LinearLayout layoutSailingSchedule;

    @BindView(R.id.layout_services)
    LinearLayout layoutServices;

    @BindView(R.id.layout_quotation)
    LinearLayout layoutQuotation;

    @BindView(R.id.layout_login_button)
    LinearLayout layoutLoginButton;

    private boolean ispaused = false;


    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.textheadingcolor));
        }
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);

        setClickListener();


    }

    private void setClickListener() {
        layoutQuotation.setOnClickListener(this);
        layoutSailingSchedule.setOnClickListener(this);
        layoutServices.setOnClickListener(this);
        layoutTrackCargo.setOnClickListener(this);
        layoutLoginButton.setOnClickListener(this);
    }

    private void jump() {

        // Jump to your Next Activity or MainActivity
        // Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        // startActivity(intent);

        //   SplashScreen.this.finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ispaused = true;
        mutedVideoView.pause();

    }


    @Override
    protected void onStop() {
        super.onStop();
        mutedVideoView.stopPlayback();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video_day);

        if (mutedVideoView != null) {
            mutedVideoView.setVideoURI(video);
            mutedVideoView.setZOrderOnTop(false);
            mutedVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mutedVideoView.start();
                }
            });


            mutedVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    jump();
                    return false;
                }
            });

            mutedVideoView.start();

        } else {

            jump();
        }




        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);


        Animation animation2 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide2);


        Animation animation3 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide3);


        Animation animation4 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide4);

        layoutTrackCargo.startAnimation(animation1);
        layoutServices.startAnimation(animation3);
        layoutSailingSchedule.startAnimation(animation2);
        layoutQuotation.startAnimation(animation4);
        if (ispaused) {
            mutedVideoView.resume();
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.layout_track_cargo:
              startNewActivity(TrackCargoActivity.class);
                break;

            case R.id.layout_sailing_schedule:
                startNewActivity(SailingScheduleActivity.class);
                break;
            case R.id.layout_services:
                startNewActivity(ServicesActivity.class);
                break;
            case R.id.layout_quotation:
                startNewActivity(GetQuotationActivity.class);
                break;
            case R.id.layout_login_button:
                startActivity(new Intent(WelcomeActivity.this, LoginActivityNew.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                break;


        }
    }


    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(WelcomeActivity.this, cls);
        startActivity(intent);

    }

}
