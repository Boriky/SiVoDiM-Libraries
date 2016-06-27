package starklabs.libraries.View;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import starklabs.libraries.R;

public class TestActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech mtts1, mtts2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mtts1 = new TextToSpeech(this, this, "starklabs.libraries");
        mtts2 = new TextToSpeech(this, this);

        Button b1 = (Button) findViewById(R.id.button1);

        if(b1!= null){
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                mtts1.speak("benvenuto nel mondo della sintesi vocale", TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }


        Button b2 = (Button) findViewById(R.id.button2);

        if(b2!= null){
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mtts2.speak("benvenuto nel mondo della sintesi vocale", TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }

    }

    @Override
    public void onInit(int status) {

    }
}
