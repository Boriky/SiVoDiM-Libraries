package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.R;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;

public class EditSpeechActivity extends AppCompatActivity implements EditSpeechInterface{
    private static SpeechPresenter speechPresenter;
    private TextView speechText;
    private Spinner emotion;
    private Spinner character;
    private Button applyChanges;
    private Button play;

    public static void setPresenter(SpeechPresenter speechPresenter){
        EditSpeechActivity.speechPresenter=speechPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_speech);

        speechPresenter.setActivity(this);

        getSupportActionBar().setTitle("Gestione battuta");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        speechText=(TextView)findViewById(R.id.speechText);
        emotion=(Spinner)findViewById(R.id.Emotion);
        character=(Spinner)findViewById(R.id.character);
        applyChanges=(Button)findViewById(R.id.editSpeechApplyButton);
        play=(Button)findViewById(R.id.play);

        speechText.setText(speechPresenter.getSpeechText());
        //set emotion Spinner
        List<String> em = new ArrayList<String>();
        em.add("HAPPINESS");
        em.add("SADESS");
        em.add("ANGER");
        em.add("SURPRISE");
        em.add("DISGUST");
        em.add("FEAR");
        ArrayAdapter<String> emotionArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,em);
        emotion.setAdapter(emotionArrayAdapter);
        String emotionTag=speechPresenter.getSpeechEmotion();
        int position=0;
        for (int i=0;i<emotion.getCount();i++){
            if (emotion.getItemAtPosition(i).equals(emotionTag))
                position = i;
            }
        emotion.setSelection(position);

        //set character Spinner
        List<String> ch = new ArrayList<String>();
        Iterator<Character>characterIterator=speechPresenter.getScreenplayCharacters();
        while (characterIterator.hasNext()){
            ch.add(characterIterator.next().getName());
        }
        ArrayAdapter<String> characterArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ch);
        character.setAdapter(characterArrayAdapter);
        position=0;
        String characterTag=speechPresenter.getSpeechCharacter().getName();
        for (int i=0;i<character.getCount();i++){
            if (character.getItemAtPosition(i).equals(characterTag))
                position = i;
        }
        character.setSelection(position);


        // apply changes
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=speechText.getText().toString();
                speechPresenter.setSpeechText(text);
                Iterator<Character>characterIterator=speechPresenter.getScreenplayCharacters();
                Character selectedCharacter=null;
                boolean stop=false;
                while (characterIterator.hasNext() && !stop){
                    Character c=characterIterator.next();
                    if(character.getSelectedItem().equals(c.getName())){
                        selectedCharacter=c;
                        stop=true;
                    }
                }
                speechPresenter.setSpeechCharacter(selectedCharacter);
                speechPresenter.setSpeechEmotion(emotion.getSelectedItem().toString());
                Intent intent=new Intent(v.getContext(),ListSpeechesActivity.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Engine myEngine=new EngineImpl(getApplicationContext());
                MivoqVoice Fede = myEngine.createVoice("Fede", "female", "it");

                Fede.setEmotion(Emotion.Disgust);
                //Emotion
                myEngine.speak("Fede", speechText.getText().toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                //onBackPressed();
                Intent intent=new Intent(this,ListSpeechesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
