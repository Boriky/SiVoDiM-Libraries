package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;
import starklabs.sivodim.R;

public class NewSpeechActivity extends AppCompatActivity implements NewSpeechInterface {
    private static ChapterPresenter chapterPresenter;
    private EditText newSpeechText;
    private Spinner newSpeechCharacter;
    private Spinner newSpeechEmotion;
    private Button newSpeechApply;

    public static void setPresenter(ChapterPresenter chapterPresenter){
        NewSpeechActivity.chapterPresenter=chapterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_speech);

        chapterPresenter.setActivity(this);

        getSupportActionBar().setTitle("Inserisci nuova battuta");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newSpeechText=(EditText)findViewById(R.id.newSpeechText);
        newSpeechCharacter=(Spinner)findViewById(R.id.newSpeechCharacter);
        newSpeechEmotion=(Spinner)findViewById(R.id.newSpeechEmotion);
        newSpeechApply=(Button)findViewById(R.id.newSpeechApply);

        newSpeechCharacter.setAdapter(chapterPresenter.getCharactersAdapter(this));
        //newSpeechEmotion.setAdapter(SpeechImpl.getEmotions(this));

        newSpeechApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=newSpeechText.getText().toString();
                String character=(String)newSpeechCharacter.getSelectedItem();
                String emotion=(String)newSpeechEmotion.getSelectedItem();
                //chapterPresenter.newSpeech(text,character,emotion,v.getContext());
                Intent intent=new Intent(v.getContext(),ListSpeechesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,ListSpeechesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
