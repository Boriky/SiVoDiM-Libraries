package starklabs.libraries.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

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
    private EditText voiceName;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=2;

    //------------------------SET PRESENTER--------------------
    public static void setPresenter(VoicePresenter voicePresenter){
        NewVoiceActivity.voicePresenter=voicePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_voice);

        voicePresenter.setActivity(this);

        getSupportActionBar().setTitle("Crea nuova voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set gender spinner
        final Spinner gender=(Spinner)findViewById(R.id.gender);
        voiceName=(EditText)findViewById(R.id.voiceName);
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
        final Spinner language=(Spinner)findViewById(R.id.language);
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

        //set effect seekBar
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
        pitch.setValue("0");
        voicePresenter.getVoice().setEffect(pitch);
        SeekBar seekPitch = (SeekBar) findViewById(R.id.seekBar);
        if (seekPitch != null) {
            seekPitch.setOnSeekBarChangeListener(new seekListener(pitch) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=(3*(progress/51)+1)*(progress-50);
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //set depth(profondita) effect
        Effect depth=new EffectImpl("HMMTractScaler");
        depth.setValue("1.0");
        voicePresenter.getVoice().setEffect(depth);
        SeekBar seekDepth = (SeekBar) findViewById(R.id.seekBar5);
        if (seekDepth != null) {
            seekDepth.setOnSeekBarChangeListener(new seekListener(depth) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=1+ (3*(progress/51) +1)*(progress-50)/200.0;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //set distorsion(distorsione) effect
        Effect distortion=new EffectImpl("Whisper");
        distortion.setValue("0");
        voicePresenter.getVoice().setEffect(distortion);
        SeekBar seekDistortion = (SeekBar) findViewById(R.id.seekBar4);
        if (seekDistortion != null) {
            seekDistortion.setOnSeekBarChangeListener(new seekListener(distortion) {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    double value=progress;
                    effect.setValue(Double.toString(value));
                    System.out.println(effect.toString());
                }
            });
        }

        //set accent(accento) effect
        Effect accent=new EffectImpl("F0Scale");
        accent.setValue("1");
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
        final Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        assert buttonPlay != null;
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voicePresenter.getEngine().speak(voicePresenter.getVoice().getName(), MivoqVoice.getSampleText(voicePresenter.getLanguage()));

                boolean connected=voicePresenter.getEngine().getIsConnected();
                if(!connected)
                    Toast.makeText(v.getContext(), "Il sistema non è connesso. La sintesi avverrà con il TTS Android", Toast.LENGTH_LONG).show();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // create new voice
        Button button1 = (Button) findViewById(R.id.AddVoiceButton);
        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vN=voiceName.getText().toString();
                if(vN.equals("")){
                    Toast.makeText(v.getContext(),"Inserisci un nome",Toast.LENGTH_SHORT).show();
                }

                else {
                    voicePresenter.setGender(gender.getSelectedItemPosition());
                    String voiceGender= voicePresenter.getGender();
                    voicePresenter.setLanguage(language.getSelectedItemPosition());
                    String voiceLanguage= voicePresenter.getLanguage();
                    int index = voicePresenter.getEngine().getVoices().size()-1;
                    List<Effect> listEffects=voicePresenter.getVoice().getEffects();

                    voicePresenter.getEngine().removeVoice(index);
                    MivoqVoice myVoice=voicePresenter.getEngine().createVoice(vN,voiceGender,voiceLanguage);

                    for(int i=0; i<5; i++)
                        myVoice.setEffect(listEffects.get(i));

                    checkPermission();
                    voicePresenter.getEngine().save();
                    voicePresenter = null;
                    back();
                }
            }
        });
    }

    private void checkPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* — don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this,"Permesso negato",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
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
        Toast.makeText(getApplicationContext(),"La voce è stata cancellata",Toast.LENGTH_SHORT).show();
        int index = voicePresenter.getEngine().getVoices().size()-1;
        voicePresenter.getEngine().removeVoice(index);
        super.onBackPressed();
    }

    public void back(){
        super.onBackPressed();
    }
}
