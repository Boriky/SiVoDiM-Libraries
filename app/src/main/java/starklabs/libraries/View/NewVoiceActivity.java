package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class NewVoiceActivity extends AppCompatActivity implements NewVoiceActivityInterface{

    private VoicePresenter voicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_voice);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Crea nuova voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner gender=(Spinner)findViewById(R.id.character);
        String[] arrayGenders=new String[]{
                "Maschio","Femmina","Neutro","Sconosciuto"
        };
        ArrayAdapter<String> genderAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayGenders);
        gender.setAdapter(genderAdapter);

        Spinner language=(Spinner)findViewById(R.id.Emotion);
        String[] arrayLanguages=new String[]{
                "Italiano","Inglese","Francese","Tedesco"
        };
        ArrayAdapter<String> languageAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayLanguages);
        language.setAdapter(languageAdapter);

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
