package starklabs.libraries.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class EditVoiceActivity extends AppCompatActivity implements EditVoiceActivityInterface{

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
    private MivoqVoice mivoqVoice;

    public static void setPresenter(VoicePresenter voicePresenter){
        EditVoiceActivity.voicePresenter=voicePresenter;
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voice);

        hideSoftKeyboard();

        voicePresenter.setActivity(this);

        getSupportActionBar().setTitle("Modifica voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //save the MivoqVoice selected
        mivoqVoice=voicePresenter.getVoice();

        //set correct VoiceName of the MivoqVoice selected
        final String voiceName = mivoqVoice.getName();
        EditText textViewNameVoice= (EditText) findViewById(R.id.voiceName);
        textViewNameVoice.setText(voiceName);

        //set gender
        final Spinner gender=(Spinner)findViewById(R.id.gender);
        genderAdapter=voicePresenter.getGenderAdapter(this);
        gender.setAdapter(genderAdapter);

        //set correct gender of the MivoqVoice selected
        String genderTag=mivoqVoice.getGender();
        int genderPos=voicePresenter.getGenderPos(genderTag);
        gender.setSelection(genderPos);

        //set language
        final Spinner language=(Spinner)findViewById(R.id.language);
        languageAdapter=voicePresenter.getLanguageAdapter(this);
        language.setAdapter(languageAdapter);

        //set correct language of the MivoqVoice selected
        String languageTag=mivoqVoice.getLanguage();
        int langPos=voicePresenter.getLanguagePos(languageTag);
        language.setSelection(langPos);

        //set effect seekBar
        //set rate (velocita) effect
        SeekBar seekRate = (SeekBar) findViewById(R.id.seekBar3);
        double rateD;
        if(mivoqVoice.getEffects().get(0).getName().equals("Rate")) {
            rateD = Double.parseDouble(mivoqVoice.getEffects().get(0).getValue());
            double pD = 150-100*rateD;
            int p = (int) pD;
            seekRate.setProgress(p);

            Effect rate=mivoqVoice.getEffects().get(0);
            if (seekRate != null) {
                seekRate.setOnSeekBarChangeListener(new seekListener(rate) {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double value=1.5 - progress/100.0;
                        effect.setValue(Double.toString(value));
                    }
                });
            }
        }

        //set F0Add (altezza) effect
        SeekBar seekF0Add = (SeekBar) findViewById(R.id.seekBar);
        double f0AddD;
        if(mivoqVoice.getEffects().get(1).getName().equals("F0Add")) {
            f0AddD = Double.parseDouble(mivoqVoice.getEffects().get(1).getValue());
            double pD;
            if(f0AddD <= 0)
                pD = f0AddD + 50;
            else
                pD = f0AddD/4 + 50;
            int p = (int) pD;

            Effect f0Add=mivoqVoice.getEffects().get(1);
            if (seekF0Add != null) {
                seekF0Add.setProgress(p);
                seekF0Add.setOnSeekBarChangeListener(new seekListener(f0Add) {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double value=(3*(progress/51)+1)*(progress-50);
                        effect.setValue(Double.toString(value));
                    }
                });
            }
        }

        //set depth(profondita) effect
        SeekBar seekDepth = (SeekBar) findViewById(R.id.seekBar5);
        double depthD;
        if(mivoqVoice.getEffects().get(2).getName().equals("HMMTractScaler")) {
            depthD = Double.parseDouble(mivoqVoice.getEffects().get(2).getValue());
            double pD;
            if(depthD<=1)
                pD = 50*(4*depthD-3);
            else
                pD = 50*depthD;
            int p = (int) pD;
            seekDepth.setProgress(p);

            Effect depth=mivoqVoice.getEffects().get(2);
            if (seekDepth != null) {
                seekDepth.setOnSeekBarChangeListener(new seekListener(depth) {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double value=1+ (3*(progress/51) +1)*(progress-50)/200.0;
                        effect.setValue(Double.toString(value));
                    }
                });
            }
        }

        //set depth(profondita) effect
        SeekBar seekDistortion = (SeekBar) findViewById(R.id.seekBar4);
        double distortionD;
        if(mivoqVoice.getEffects().get(3).getName().equals("Whisper")) {
            distortionD = Double.parseDouble(mivoqVoice.getEffects().get(3).getValue());
            double pD;
            pD=distortionD;
            int p = (int) pD;
            seekDistortion.setProgress(p);

            Effect distortion=mivoqVoice.getEffects().get(3);
            if (seekDistortion != null) {
                seekDistortion.setOnSeekBarChangeListener(new seekListener(distortion) {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double value=progress;
                        effect.setValue(Double.toString(value));
                    }
                });
            }
        }

        //set accent(accento) effect
        SeekBar seekAccent = (SeekBar) findViewById(R.id.seekBar2);
        double accentD;
        if(mivoqVoice.getEffects().get(4).getName().equals("F0Scale")) {
            accentD = Double.parseDouble(mivoqVoice.getEffects().get(4).getValue());
            double pD;
            if(accentD<=1)
                pD=50*(2*accentD-1);
            else
                pD=50* accentD;
            int p = (int) pD;
            seekAccent.setProgress(p);

            Effect accent=mivoqVoice.getEffects().get(4);
            if (seekAccent != null) {
                seekAccent.setOnSeekBarChangeListener(new seekListener(accent) {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        double value=1+ (progress/51 +1)*(progress-50)/100.0;
                        effect.setValue(Double.toString(value));
                    }
                });
            }
        }
        // edit mivoqVoice
        Button button1 = (Button) findViewById(R.id.EditVoiceButton);
        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voicePresenter.setGender(gender.getSelectedItemPosition());
                voicePresenter.setLanguage(language.getSelectedItemPosition());

                mivoqVoice.setName(voiceName);
                voicePresenter.getEngine().save();

                voicePresenter = null;
                Intent homeIntent = new Intent(EditVoiceActivity.this, VoiceListActivity.class);
                startActivity(homeIntent);
            }
        });

        //preview of the text with effect
        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        assert buttonPlay != null;
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voicePresenter.getEngine().speak(voicePresenter.getVoice().getName(), MivoqVoice.getSampleText(voicePresenter.getLanguage()));

                boolean connected=voicePresenter.getEngine().getIsConnected();
                if(!connected)
                    Toast.makeText(v.getContext(), "Il sistema non è connesso. La sintesi avverrà con il TTS Android", Toast.LENGTH_LONG).show();
            }
        });

        final Button buttonDefault=(Button) findViewById(R.id.buttonDefault);
        if(voicePresenter.isDefaultVoice())
            buttonDefault.setEnabled(false);
        else{
            assert buttonDefault != null;
            buttonDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<MivoqVoice> list = voicePresenter.getEngine().getVoices();
                    int pos=-1;

                    for(int i = 0; (i < list.size()) && (pos == -1); i++)
                    {
                        if(voicePresenter.getVoice().getName().equals(list.get(i).getName()))
                            pos=i;
                    }
                    voicePresenter.getEngine().setDefaultVoice(pos);
                    voicePresenter.setDefaultVoice(true);
                    buttonDefault.setEnabled(false);
                }
            });
        }
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

    @Override
    public void onBackPressed() {
        voicePresenter.getEngine().load();
        super.onBackPressed();
    }
}
