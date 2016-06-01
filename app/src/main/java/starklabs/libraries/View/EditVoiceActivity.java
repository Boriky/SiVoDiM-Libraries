package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class EditVoiceActivity extends AppCompatActivity implements EditVoiceActivityInterface{

    private VoicePresenter voicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voice);

        getSupportActionBar().setTitle("Modifica voce");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String voiceName = (String)getIntent().getSerializableExtra("VoiceSelected");
        EditText textViewNameVoice= (EditText) findViewById(R.id.editText2);
        textViewNameVoice.setText(voiceName);
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
