package starklabs.sivodim.Drama.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenterImpl;
import starklabs.sivodim.R;

public class NewCharacterActivity extends AppCompatActivity implements NewCharacterInterface {
    private static CharacterPresenter characterPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        if(characterPresenter==null)characterPresenter=new CharacterPresenterImpl(this);
        getSupportActionBar().setTitle("Creazione personaggio");
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
