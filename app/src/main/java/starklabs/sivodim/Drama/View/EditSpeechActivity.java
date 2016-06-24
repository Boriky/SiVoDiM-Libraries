package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;
import starklabs.sivodim.R;

import starklabs.libraries.Model.EngineManager.Engine;

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
        emotion.setAdapter(SpeechImpl.getEmotions(this));
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
                final File path=new File(speechPresenter.getAudioPath());
                Engine engine=new EngineImpl(getApplicationContext());
                engine.synthesizeToFile(path.getAbsolutePath(),
                        speechPresenter.getSpeechCharacter().getVoiceID()
                        , speechPresenter.getSpeechEmotion(),
                        speechText.getText().toString(), new Engine.Listener() {
                            @Override
                            public void onCompleteSynthesis() {
                                System.out.println("Ho salvato il File");
                            }
                        });
                Intent intent=new Intent(v.getContext(),ListSpeechesActivity.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Engine engine=new EngineImpl(getApplicationContext());
                final File path=new File(speechPresenter.getAudioPath());
                final String effect=(String)emotion.getSelectedItem();
                String characterName=(String)character.getSelectedItem();
                Character selectedCharacter=speechPresenter.getSpeechCharacter();
                Iterator<Character>characterIterator1=speechPresenter.getScreenplayCharacters();
                boolean found=false;
                while (!found && characterIterator1.hasNext()){
                    Character characterSel=characterIterator1.next();
                    String name=characterSel.getName();
                    if(characterName.equals(name)){
                        found=true;
                        selectedCharacter=characterSel;
                    }
                }
                final String voice=selectedCharacter.getVoiceID();
                System.out.println(voice);
                if(!path.exists()){
                    try {
                        path.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                engine.synthesizeToFile(path.getAbsolutePath(), voice, effect,
                        speechText.getText().toString(), new Engine.Listener() {
                            @Override
                            public void onCompleteSynthesis() {
                                System.out.println("Ho salvato il File");
                                SpeechSound speechSound=new SpeechSound(path.getAbsolutePath());
                                speechSound.play();
                            }
                        });
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
