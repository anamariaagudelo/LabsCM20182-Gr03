package co.ude.copumovil.gr03_20182.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {

    private Timer tiempo;
    private ProgressBar barraProgreso;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);
        barraProgreso.setProgress(0);
        final long intervalo = 45;
        tiempo = new Timer();
        tiempo.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i < 100){
                    barraProgreso.setProgress(i);
                    i++;
                }else{
                    tiempo.cancel();
                    Intent intent = new Intent().setClass(
                            Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },0,intervalo);

    }
}
