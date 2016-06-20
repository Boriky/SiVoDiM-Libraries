package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class NewVoiceActivity extends AppCompatActivity implements NewVoiceActivityInterface{

    public abstract class seekListener implements SeekBar.OnSeekBarChangeListener{
        protected Effect effect;

        public seekListener(Effect e){
            effect=e;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private static VoicePresenter voicePresenter;
    private ArrayAdapter<String> genderAdapter;
    private ArrayAdapter<String> languageAdapter;

    //------------------------SET PRESENTER--------------------
    public static void setPresenter(VoicePresenter voicePresenter){
        NewVoiceActivity.voicePresenter=voicePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_voice);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        voicePresenter.setActivity(this);

        getSupportActionBar().setTitle("Crea nuova voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set gender spinner
        Spinner gender=(Spinner)findViewById(R.id.character);
        genderAdapter=voicePresenter.getGenderAdapter(this);
        gender.setAdapter(genderAdapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                voicePresenter.setGender(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set language spinner
        Spinner language=(Spinner)findViewById(R.id.Emotion);
        languageAdapter=voicePresenter.getLanguageAdapter(this);
        language.setAdapter(languageAdapter);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                voicePresenter.setLanguage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set rate (velocita) effect
        Effect rate=new EffectImpl("Rate");
        rate.setValue("1");
        voicePresenter.getVoice().setEffect(rate);
        SeekBar seekRate = (SeekBar) findViewById(R.id.seekBar3);
        if (seekRate != null) {
            seekRate.setOnSeekBarChangeListener(new seekListener(rate) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=1.5 - progress/100.0;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //set pitch(altezza) effect
        Effect pitch=new EffectImpl("F0Add");
        pitch.setValue("100");
        voicePresenter.getVoice().setEffect(pitch);
        SeekBar seekPitch = (SeekBar) findViewById(R.id.seekBar4);
        if (seekPitch != null) {
            seekPitch.setOnSeekBarChangeListener(new seekListener(pitch) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=progress*2 -50;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //set depth(profondita) effect
        Effect depth=new EffectImpl("HMMTractScaler");
        depth.setValue("1.15");
        voicePresenter.getVoice().setEffect(depth);
        SeekBar seekDepth = (SeekBar) findViewById(R.id.seekBar5);
        if (seekDepth != null) {
            seekDepth.setOnSeekBarChangeListener(new seekListener(depth) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=1+ (3*progress/51 +1)*(progress-50)/100.0;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }
/*
        //set vitality(energia) effect
        Effect vitality=new EffectImpl("Rate");
        vitality.setValue("1");
        voicePresenter.getVoice().setEffect(vitality);
        SeekBar seekVitality = (SeekBar) findViewById(R.id.seekBar);
        if (seekRate != null) {
            seekRate.setOnSeekBarChangeListener(new seekListener(rate) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=1.5 - progress/100.0;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }
*/
        //set accent(accento) effect
        Effect accent=new EffectImpl("F0Scale");
        accent.setValue("1.25");
        voicePresenter.getVoice().setEffect(accent);
        SeekBar seekAccent = (SeekBar) findViewById(R.id.seekBar2);
        if (seekAccent != null) {
            seekAccent.setOnSeekBarChangeListener(new seekListener(accent) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=1+ (progress/51 +1)*(progress-50)/100.0;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //preview of the text with effect
        ImageButton button = (ImageButton) findViewById(R.id.previewButton);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(voicePresenter.getVoice().getLanguage());
                System.out.println(voicePresenter.getVoice().getGender());

                voicePresenter.getEngine().speak(voicePresenter.getVoice().getName(), MivoqVoice.getSampleText(voicePresenter.getLanguage()));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
