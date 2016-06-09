package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.CharacterArrayAdapter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenterImpl;
import starklabs.sivodim.R;

public class ListCharacterActivity extends AppCompatActivity implements ListCharacterInterface {
    private static CharacterPresenter characterPresenter;
    private ListView characterListView;
    private CharacterArrayAdapter characterArrayAdapter;


    public static void setPresenter(CharacterPresenter characterPresenter){
        ListCharacterActivity.characterPresenter=characterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_character);

        if(characterPresenter==null)
            characterPresenter=new CharacterPresenterImpl(this);
        else
            characterPresenter.setActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Personaggi dello sceneggiato");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        characterListView=(ListView)findViewById(R.id.characterListView);
        characterArrayAdapter=characterPresenter.getCharacterArrayAdapter(this);
        characterListView.setAdapter(characterArrayAdapter);
        if(characterArrayAdapter.getCount()==0)
            Toast.makeText(this,"Non ci sono personaggi",Toast.LENGTH_LONG).show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCharacterIntent=new Intent(view.getContext(),NewCharacterActivity.class);
                startActivity(newCharacterIntent);
            }
        });


        characterListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Character selected=(Character) parent.getItemAtPosition(position);
                characterPresenter.goToEditCharacterActivity(view.getContext(),selected);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,ListChapterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
