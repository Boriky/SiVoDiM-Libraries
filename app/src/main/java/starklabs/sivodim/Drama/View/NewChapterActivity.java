package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

public class NewChapterActivity extends AppCompatActivity implements NewChapterInterface {
    private static ScreenplayPresenter screenplayPresenter;
    private EditText newChapterName;
    private Button createNewChapter;

    public static void setPresenter(ScreenplayPresenter screenplayPresenter){
        NewChapterActivity.screenplayPresenter=screenplayPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chapter);

        if(screenplayPresenter==null)
            screenplayPresenter=new ScreenplayPresenterImpl(this);
        else
            screenplayPresenter.setActivity(this);

        getSupportActionBar().setTitle("Crea un nuovo capitolo");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newChapterName=(EditText)findViewById(R.id.newChapterName);
        createNewChapter=(Button)findViewById(R.id.createNewChapter);

        createNewChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenplayPresenter.newChapter(newChapterName.getText().toString());
                Intent intent=new Intent(v.getContext(),ListChapterActivity.class);
                startActivity(intent);
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
