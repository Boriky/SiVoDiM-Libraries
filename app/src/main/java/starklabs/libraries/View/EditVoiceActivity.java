package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.Presenter.VoicePresenterImpl;
import starklabs.libraries.R;

public class EditVoiceActivity extends AppCompatActivity implements EditVoiceActivityInterface{

    private static VoicePresenter voicePresenter;
    private ArrayAdapter<String> genderAdapter;
    private ArrayAdapter<String> languageAdapter;

    public static void setPresenter(VoicePresenter voicePresenter){
        EditVoiceActivity.voicePresenter=voicePresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voice);

        if(voicePresenter==null)
            voicePresenter=new VoicePresenterImpl(this);
        else
            voicePresenter.setActivity(this);

        getSupportActionBar().setTitle("Modifica voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String voiceName = (String)getIntent().getSerializableExtra("VoiceSelected");
        EditText textViewNameVoice= (EditText) findViewById(R.id.editText2);
        textViewNameVoice.setText(voiceName);

        Spinner gender=(Spinner)findViewById(R.id.character);
        genderAdapter=voicePresenter.getGenderAdapter(this);
        gender.setAdapter(genderAdapter);

        Spinner language=(Spinner)findViewById(R.id.Emotion);
        languageAdapter=voicePresenter.getLanguageAdapter(this);
        language.setAdapter(languageAdapter);
/*
        ImageButton button = (ImageButton) findViewById(R.id.previewButton);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MivoqTTSSingleton engine = MivoqTTSSingleton.getInstance();
                engine.setContext(getApplicationContext());
                byte[] audio = engine.SynthesizeText("Testo di prova della voce creata.");
            }
        });

        */
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
