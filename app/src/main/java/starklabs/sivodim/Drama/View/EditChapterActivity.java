package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.R;

public class EditChapterActivity extends AppCompatActivity implements EditChapterInterface{
    private static ChapterPresenter chapterPresenter;
    private EditText chapterName;
    private Button editSoundtrack;
    private Button editWallpaper;
    private Button apply;

    public static void setPresenter(ChapterPresenter chapterPresenter){
        EditChapterActivity.chapterPresenter=chapterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chapter);

        if(chapterPresenter==null)
            chapterPresenter=new ChapterPresenterImpl(this);
        else
            chapterPresenter.setActivity(this);

        getSupportActionBar().setTitle("Modifica capitolo");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chapterName=(EditText)findViewById(R.id.chapterName);
        editSoundtrack=(Button)findViewById(R.id.editSoundtrack);
        editWallpaper=(Button)findViewById(R.id.editWallpaper);
        apply=(Button)findViewById(R.id.editChapterApplyButton);

        chapterName.setText(chapterPresenter.getChapterTitle());

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chapterName.getText().toString().isEmpty()) {
                    chapterPresenter.setChapterTitle(chapterName.getText().toString());
                    //set other properties..
                    Intent intent=new Intent(v.getContext(),ListChapterActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(v.getContext(),"Il titolo non può essere vuoto",Toast.LENGTH_SHORT).show();
                }
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
