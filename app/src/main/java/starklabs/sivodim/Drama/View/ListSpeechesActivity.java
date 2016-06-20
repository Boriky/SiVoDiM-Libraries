package starklabs.sivodim.Drama.View;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.SpeechArrayAdapter;
import starklabs.sivodim.R;

public class ListSpeechesActivity extends AppCompatActivity implements ListSpeechesInterface,
        Toolbar.OnMenuItemClickListener{
    private static ChapterPresenter chapterPresenter;
    private ListView speechListView;
    private SpeechArrayAdapter speechListAdapter;
    private LinearLayout moveButtons;
    private FloatingActionButton upButton;
    private FloatingActionButton downButton;
    private FloatingActionButton doneButton;
    private FloatingActionButton deleteButton;
    private ImageButton newSpeechButton;
    private ImageButton selectCharacterButton;
    private ImageButton selectEmotionButton;
    private EditText speechEditText;
    private ListView newSpeechCharactersList;
    private ListView newSpeechEmotionsList;


    public static void setPresenter(ChapterPresenter chapterPresenter){
        ListSpeechesActivity.chapterPresenter=chapterPresenter;
    }

    // create the options menu: it's invoked just one time when the activity has been created
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_speeches_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_speeches);

        chapterPresenter.setSpeechSelected(-1);

        if(chapterPresenter==null)
            chapterPresenter=new ChapterPresenterImpl(this);
        else
            chapterPresenter.setActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=chapterPresenter.getChapterTitle();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        speechListView=(ListView) findViewById(R.id.speechesListView);
        speechListAdapter=chapterPresenter.getSpeeches(this);
        moveButtons=(LinearLayout)findViewById(R.id.moveButtons);
        upButton=(FloatingActionButton)findViewById(R.id.upButton);
        downButton=(FloatingActionButton)findViewById(R.id.downButton);
        doneButton=(FloatingActionButton)findViewById(R.id.doneButton);
        deleteButton=(FloatingActionButton)findViewById(R.id.deleteButton);
        newSpeechButton=(ImageButton)findViewById(R.id.newSpeechButton);
        selectCharacterButton=(ImageButton)findViewById(R.id.setSpeechCharacterButton);
        selectEmotionButton=(ImageButton)findViewById(R.id.setSpeechEmotionButton);
        speechEditText=(EditText)findViewById(R.id.speechEditText);
        newSpeechCharactersList=(ListView)findViewById(R.id.newSpeechCharactersList);
        newSpeechEmotionsList=(ListView)findViewById(R.id.newSpeechEmotionsList);

        newSpeechCharactersList.setAdapter(chapterPresenter.getCharactersAdapter(this));
        newSpeechEmotionsList.setAdapter(SpeechImpl.getEmotions(this));

        speechListView.setAdapter(speechListAdapter);
        if(speechListAdapter.getCount()==0)
            Toast.makeText(this,"Il capitolo è vuoto",Toast.LENGTH_LONG).show();

        speechListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        speechListView.setAdapter(speechListAdapter);

        //to scroll the list view to bottom on data change
        speechListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                speechListView.setSelection(speechListAdapter.getCount() - 1);
            }
        });

        speechListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Speech selected=(Speech) parent.getItemAtPosition(position);
                chapterPresenter.goToEditSpeechActivity(view.getContext(),selected);
            }
        });

        speechListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                chapterPresenter.setSpeechSelected(position);
                speechListView.setAdapter(chapterPresenter.getSpeeches(view.getContext()));
                speechListView.setSelection(position);
                moveButtons.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                return true;
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapterPresenter.setSpeechSelected(-1);
                speechListView.setAdapter(chapterPresenter.getSpeeches(v.getContext()));
                speechListView.setSelection(speechListView.getCount()-1);
                moveButtons.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"MoveUP",Toast.LENGTH_SHORT).show();
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"MoveDOWN",Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speech speechToDelete=(Speech) speechListView.getItemAtPosition(chapterPresenter.getSpeechSelected());
                chapterPresenter.deleteSpeech(speechToDelete);
                chapterPresenter.setSpeechSelected(-1);
                speechListView.setAdapter(chapterPresenter.getSpeeches(v.getContext()));
                speechListView.setSelection(speechListView.getCount()-1);
                moveButtons.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }
        });

        speechEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSpeechCharactersList.setVisibility(View.GONE);
                newSpeechEmotionsList.setVisibility(View.GONE);
            }
        });

        selectEmotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                newSpeechCharactersList.setVisibility(View.GONE);
                newSpeechEmotionsList.setVisibility(View.VISIBLE);
            }
        });

        selectCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                newSpeechEmotionsList.setVisibility(View.GONE);
                newSpeechCharactersList.setVisibility(View.VISIBLE);
            }
        });

        newSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=speechEditText.getText().toString();
                String character=(String)newSpeechCharactersList.getSelectedItem();
                String emotion=(String)newSpeechEmotionsList.getSelectedItem();
                chapterPresenter.newSpeech(text,character,emotion,v.getContext());
                Intent intent=new Intent(v.getContext(),ListSpeechesActivity.class);
                startActivity(intent);
            }
        });

        toolbar.setOnMenuItemClickListener(this);

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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editChapterMenu:
                chapterPresenter.goToEditChapterActivity(this);
                break;
            case R.id.listCharacterMenu:
                chapterPresenter.goToListCharactersActivity(this);
                break;
            case R.id.newCharacterMenu:
                chapterPresenter.goToNewCharacterActivity(this);
                break;
        }
        return false;
    }
}
