package starklabs.sivodim.Drama.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.Serializable;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;
import starklabs.sivodim.Drama.Model.Screenplay.Mp3Converter;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

import static android.os.Environment.getExternalStorageDirectory;

public class ListChapterActivity extends AppCompatActivity implements ListChapterInterface,
        Toolbar.OnMenuItemClickListener{
    private static ScreenplayPresenter screenplayPresenter;

    private ListView chapterListView;
    private ListAdapter chapterListAdapter;

    public static void setPresenter(ScreenplayPresenter screenplayPresenter){
        ListChapterActivity.screenplayPresenter=screenplayPresenter;
    }

    // create the options menu: it's invoked just one time when the activity has been created
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_chapter_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);

        if(screenplayPresenter==null)
            screenplayPresenter=new ScreenplayPresenterImpl(this);
        else
            screenplayPresenter.setActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=screenplayPresenter.getScreenplayTitle();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenplayPresenter.goToNewChapterActivity(view.getContext());
            }
        });

        chapterListView=(ListView) findViewById(R.id.listChapterView);
        chapterListAdapter=screenplayPresenter.getTitlesAdapter(this,title+".scrpl");
        chapterListView.setAdapter(chapterListAdapter);
        if(chapterListView.getCount()==0)
            Toast.makeText(this,"Premi sul + per aggiungere capitoli",Toast.LENGTH_LONG).show();

        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                screenplayPresenter.goToListSpeechesActivity(view.getContext(),selected);
            }
        });

        chapterListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                screenplayPresenter.goToEditChapterActivity(view.getContext(),selected);
                return true;
            }
        });

        toolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveMenu:
                screenplayPresenter.save(screenplayPresenter.getScreenplay(), this.getApplicationContext());
                Toast.makeText(this,"Salvato",Toast.LENGTH_LONG).show();
                break;
            case R.id.exportMenu:
                screenplayPresenter.getScreenplay().export("Audio",this);
                String name=screenplayPresenter.getScreenplayTitle().replace(" ","_");
                File destination=new File(getFilesDir(),name+".mp3");
                SpeechSound speechSound=new SpeechSound(destination.getAbsolutePath());
                speechSound.play();
                Toast.makeText(this,"Esportazione riuscita",Toast.LENGTH_LONG).show();
                /*/---- test FFmpeg -----------------------------------------------------------
                File f=new File(getExternalStorageDirectory(),"pic004.png");
                System.out.println(f.getAbsolutePath());
                File file=new File(getFilesDir(),"track.mp3");
                File file2=new File(getFilesDir(),"provafile.wav");
                File file3=new File(getFilesDir(),"provafilefr.wav");
                File dest=new File(getFilesDir(),"parzial.wav");
                File dest2=new File(getFilesDir(),"mergedAudio.wav");
                AudioConcatenator am=new AudioConcatenator(this,dest);
                am.addFile(file2);
                am.addFile(file3);
                AudioMixer aam=new AudioMixer(this,dest,file,dest2);
                File dest3=new File(getFilesDir(),"export.mp3");
                Mp3Converter mp=new Mp3Converter(this,dest2,dest3);
                try {
                    am.exec();
                    aam.exec();
                    mp.exec();
                } catch (FFmpegCommandAlreadyRunningException e) {
                    e.printStackTrace();
                }
                SpeechSound soundtrack=new SpeechSound(dest3.getAbsolutePath());
                soundtrack.play();
                Toast.makeText(this,"Esportazione riuscita",Toast.LENGTH_LONG).show();
                //---- end of test FFmpeg -----------------------------------------------------*/
                break;
            case R.id.shareMenu:
                onShare();
                Toast.makeText(this,"Condividi",Toast.LENGTH_LONG).show();
                break;
            case R.id.editMenu:
                Toast.makeText(this,"Modifica",Toast.LENGTH_LONG).show();
                break;
            case R.id.newCharacterMenu:
                screenplayPresenter.goToNewCharacterActivity(this);
                break;
            case R.id.importCharactersMenu:
                Toast.makeText(this,"Importa personaggio",Toast.LENGTH_LONG).show();
                break;
            case R.id.charactersListMenu:
                screenplayPresenter.goToListCharactersActivity(this);
                break;
        }
        return false;
    }

    public void onShare() {
        String audioClipFileName=screenplayPresenter.getScreenplayTitle().replace(" ","_")+".mp3";
        File filePath=new File(getFilesDir(),audioClipFileName);
        /*final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("audio/mp3");
        shareIntent.putExtra(android.content.Intent.EXTRA_STREAM,
                Uri.parse("file://"+file.getAbsolutePath()));
        System.out.println("Share file: "+file.getAbsolutePath()+" <----");
        shareIntent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(shareIntent, "Share Audio Clip"));*/
        /*final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("audio/mp3");
        shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://"+file.getAbsolutePath()));
        startActivity(Intent.createChooser(shareIntent,"Titolo"));*/
        // concatenate the internal cache folder with the document its path and filename
        final File file = new File(getFilesDir(), audioClipFileName);
// let the FileProvider generate an URI for this private file
        final Uri uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);
// create an intent, so the user can choose which application he/she wants to use to share this file
        final Intent intent = ShareCompat.IntentBuilder.from(this)
                .setType("audio/mp3")
                .setSubject("Condividi sceneggiato")
                .setStream(uri)
                .setChooserTitle("Condivisione di "+audioClipFileName)
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(intent);

        System.out.println("Share file: "+file.getAbsolutePath()+" <----");
    }
}
