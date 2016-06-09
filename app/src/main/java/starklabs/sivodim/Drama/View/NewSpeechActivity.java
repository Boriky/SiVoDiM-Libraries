package starklabs.sivodim.Drama.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechPresenter;
import starklabs.sivodim.Drama.Presenter.SpeechPresenterImpl;
import starklabs.sivodim.R;

public class NewSpeechActivity extends AppCompatActivity implements NewSpeechInterface {
    private static SpeechPresenter speechPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_speech);

        if(speechPresenter==null)speechPresenter=new SpeechPresenterImpl(this);

        getSupportActionBar().setTitle("Inserisci nuova battuta");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
