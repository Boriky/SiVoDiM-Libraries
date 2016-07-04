package starklabs.sivodim.Drama.View;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

public class ListChapterActivity extends AppCompatActivity implements ListChapterInterface,
        Toolbar.OnMenuItemClickListener{

    private static ScreenplayPresenter screenplayPresenter;
    private ListView chapterListView;
    private ListAdapter chapterListAdapter;
    private LinearLayout player;
    private File screenplayMp3;
    private SeekBar seekBar;
    private TextView timingInfo;
    private Button playButton;
    private Button pauseButton;
    private MediaPlayer mediaPlayer=null;
    private Handler handler = new Handler();
    private LinearLayout moveButton;
    private FloatingActionButton upButton;
    private FloatingActionButton downButton;
    private FloatingActionButton doneButton;
    private FloatingActionButton editButton;
    private FloatingActionButton deleteButton;
    private FloatingActionButton fab;
    private String title;
    private PopupWindow pw;


    private Runnable updateBar = new Runnable() {
        public void run()
        {
            int startTime = mediaPlayer.getCurrentPosition();
            String timing=formatTime(startTime)+" - "+formatTime(mediaPlayer.getDuration());
            if(timingInfo!=null)timingInfo.setText(timing);
            seekBar.setProgress(startTime);
            handler.postDelayed(this, 100);
        }
    };

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
        title=screenplayPresenter.getScreenplayTitle();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        chapterListView=(ListView) findViewById(R.id.listChapterView);
        player=(LinearLayout)findViewById(R.id.player);
        playButton=(Button)findViewById(R.id.playButton);
        seekBar=(SeekBar)findViewById(R.id.seekbarMusic);
        pauseButton=(Button)findViewById(R.id.pauseButton);
        timingInfo=(TextView)findViewById(R.id.timingInfo);
        moveButton=(LinearLayout)findViewById(R.id.moveButtons);
        upButton=(FloatingActionButton) findViewById(R.id.upButton);
        downButton=(FloatingActionButton) findViewById(R.id.downButton);
        doneButton=(FloatingActionButton) findViewById(R.id.doneButton);
        editButton=(FloatingActionButton) findViewById(R.id.editButton);
        deleteButton=(FloatingActionButton) findViewById(R.id.deleteButton);
        fab= (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenplayPresenter.goToNewChapterActivity(view.getContext());
            }
        });

        //seekBar.setEnabled(false);
        chapterListAdapter=screenplayPresenter.getTitlesAdapter(this,title+".scrpl");
        chapterListView.setAdapter(chapterListAdapter);
        if(chapterListView.getCount()==0)
            Toast.makeText(this,"Premi sul + per aggiungere capitoli",Toast.LENGTH_LONG).show();

        loadSound();

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
                screenplayPresenter.setChapterSelected(position,selected);
                chapterListView.setAdapter(screenplayPresenter.getTitlesAdapter(view.getContext(),selected));
                chapterListView.setSelection(position);
                moveButton.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                deleteButton.setVisibility(View.VISIBLE);
                return true;
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenplayPresenter.setChapterSelected(-1,null);
                chapterListView.setAdapter(screenplayPresenter.getTitlesAdapter(v.getContext(),title+".scrpl"));
                chapterListView.setSelection(chapterListView.getCount()-1);
                moveButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=screenplayPresenter.getChapterSelected()-1;
                if(position<0)position=0;
                screenplayPresenter.moveUpChapter(screenplayPresenter.getChapterSelected());
                screenplayPresenter.setChapterSelected(position,
                        screenplayPresenter.getChapterSelectedName());
                chapterListView.setAdapter(screenplayPresenter.getTitlesAdapter(v.getContext(),title+".scrpl"));
                chapterListView.setSelection(position);
                moveButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=screenplayPresenter.getChapterSelected()+1;
                if(position>=chapterListView.getCount())position=chapterListView.getCount()-1;
                screenplayPresenter.moveDownChapter(screenplayPresenter.getChapterSelected());
                screenplayPresenter.setChapterSelected(position,
                        screenplayPresenter.getChapterSelectedName());
                chapterListView.setAdapter(screenplayPresenter.getTitlesAdapter(v.getContext(),title+".scrpl"));
                chapterListView.setSelection(position);
                moveButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenplayPresenter.removeChapter(screenplayPresenter.getChapterSelected());
                screenplayPresenter.setChapterSelected(-1,null);
                chapterListView.setAdapter(screenplayPresenter.getTitlesAdapter(v.getContext(),title+".scrpl"));
                chapterListView.setSelection(chapterListView.getCount()-1);
                moveButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenplayPresenter.goToEditChapterActivity(v.getContext(),
                        screenplayPresenter.getChapterSelectedName());
                screenplayPresenter.setChapterSelected(-1,null);
            }
        });

        toolbar.setOnMenuItemClickListener(this);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseSound();
            }
        });

        if(mediaPlayer!=null){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                handler.removeCallbacks(updateBar);
                pauseSound();
                seekBar.setProgress(0);
                loadSound();
            }
        });
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String timing=formatTime(seekBar.getProgress())+" - "+formatTime(mediaPlayer.getDuration());
                if(timingInfo!=null)timingInfo.setText(timing);
            }
        });

    }

    private void loadSound(){
        String name=screenplayPresenter.getScreenplayTitle().replace(" ","_");
        screenplayMp3=new File(getFilesDir(),name+".mp3");
        if(screenplayMp3.exists()){
            if (mediaPlayer==null){
                mediaPlayer = new MediaPlayer();
            }
            try {
                mediaPlayer.setDataSource(screenplayMp3.getAbsolutePath());
                mediaPlayer.prepare();
                seekBar.setMax((int) mediaPlayer.getDuration());
                seekBar.setProgress(0);
                String timing=formatTime(0)+" - "+formatTime(mediaPlayer.getDuration());
                if(timingInfo!=null)timingInfo.setText(timing);
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.setVisibility(View.VISIBLE);
        }
    }

    private void playSound(){
        mediaPlayer.seekTo(seekBar.getProgress());
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        handler.postDelayed(updateBar,100);
        mediaPlayer.start();
    }

    private void pauseSound(){
        mediaPlayer.pause();
        pauseButton.setVisibility(View.GONE);
        playButton.setVisibility(View.VISIBLE);
        handler.removeCallbacks(updateBar);
    }

    private String formatTime(Integer time){
        Integer absSec=time/1000;
        Integer min=absSec/60;
        Integer sec=absSec%60;
        return min.toString()+":"+sec.toString();
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
                TextView processFeedback=initiatePopupWindow();
                screenplayPresenter.getScreenplay().export("Audio",this,processFeedback);
                break;
            case R.id.videoExportMenu:
                TextView processFeedback2=initiatePopupWindow();
                screenplayPresenter.getScreenplay().export("Video",this,processFeedback2);
                break;
            case R.id.shareMenu:
                onShare();
                break;
            case R.id.videoShareMenu:
                onShareVideo();
                break;
            case R.id.editMenu:
                Toast.makeText(this,"Modifica",Toast.LENGTH_LONG).show();
                break;
            case R.id.newCharacterMenu:
                screenplayPresenter.goToNewCharacterActivity(this);
                break;
            case R.id.charactersListMenu:
                screenplayPresenter.goToListCharactersActivity(this);
                break;
        }
        return false;
    }

    public void onShareVideo(){
        String videoClipFileName=screenplayPresenter.getScreenplayTitle().replace(" ","_")+".mp4";
        File filePath=new File(getFilesDir(),videoClipFileName);
        // concatenate the internal cache folder with the document its path and filename
        final File file = new File(getFilesDir(), videoClipFileName);
// let the FileProvider generate an URI for this private file
        final Uri uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);
// create an intent, so the user can choose which application he/she wants to use to share this file
        final Intent intent = ShareCompat.IntentBuilder.from(this)
                .setType("video/mp4")
                .setSubject("Condividi sceneggiato video")
                .setStream(uri)
                .setChooserTitle("Condivisione di "+videoClipFileName)
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(intent);
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
    }

    private TextView initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) ListChapterActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popup_progress,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, ActionBar.LayoutParams.WRAP_CONTENT, 300, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            ProgressBar progress = (ProgressBar)layout.findViewById(R.id.statusBar);
            // Closes the popup window when touch outside.
            pw.setOutsideTouchable(false);
           // pw.setFocusable(false);
            // Removes default background.
            pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView textView=(TextView) layout.findViewById(R.id.processFeedback);
            System.out.println("TEXTVIEW: "+textView);
            return textView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            handler.removeCallbacks(updateBar);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            handler.removeCallbacks(updateBar);
        }
        // save current screenplay and show message
        screenplayPresenter.save(screenplayPresenter.getScreenplay(), getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            handler.removeCallbacks(updateBar);
        }
        // save current screenplay and show message
        screenplayPresenter.save(screenplayPresenter.getScreenplay(), getApplicationContext());
    }
}
