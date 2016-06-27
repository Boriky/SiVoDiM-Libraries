package starklabs.sivodim.Drama.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.Presenter.ChapterPresenter;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

public class NewChapterActivity extends AppCompatActivity implements NewChapterInterface {
    private static ScreenplayPresenter screenplayPresenter;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=2;
    private static final int RESULT_LOAD_AUDIO = 3;
    private EditText newChapterName;
    private Button createNewChapter;
    private Button newTrack;
    private TextView trackView;
    private ImageView newWallpaper;
    private String wallpaperPath;
    private String audioPath;

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
        newTrack=(Button)findViewById(R.id.newTrack);
        trackView=(TextView)findViewById(R.id.trackView);
        newWallpaper=(ImageView)findViewById(R.id.newWallpaper);

        createNewChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=newChapterName.getText().toString();
                if(!title.isEmpty()) {
                    Soundtrack soundtrack = null;
                    if (audioPath != null) {
                        File audioChoice = new File(audioPath);
                        File dir = new File(getFilesDir(), screenplayPresenter.getScreenplayTitle().replace(" ", "_"));
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File destination = new File(dir, "chpt_" + title + ".mp3");
                        try {
                            copyFile(audioChoice, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        soundtrack = new Soundtrack(destination.getAbsolutePath());
                        System.out.println("SAVE in: " + destination.getAbsolutePath());
                    }
                    Background background = null;
                    if (wallpaperPath != null) {
                        File wallpaperChoice = new File(wallpaperPath);
                        File dir = new File(getFilesDir(), screenplayPresenter.getScreenplayTitle().replace(" ", "_"));
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File destination = new File(dir, "chpt_" + title + ".png");
                        try {
                            copyFile(wallpaperChoice, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        background = new Background(destination.getAbsolutePath());
                        System.out.println("SAVE in: " + destination.getAbsolutePath());
                    }
                    screenplayPresenter.newChapter(title, soundtrack, background);
                    Intent intent = new Intent(v.getContext(), ListChapterActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(v.getContext(),"Il titolo del capitolo non può essere vuoto",Toast.LENGTH_SHORT).show();
                }
            }
        });

        newWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMedia("Image");
            }
        });

        newTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMedia("Audio");
            }
        });

    }

    private void loadMedia(String type){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if(type.equals("Audio"))
            getAudio();
        else
            getImage();

    }

    private void getImage(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    private void getAudio(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    getImage();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this,"Permesso negato",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private static void copyFile(File source, File dest)
            throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
//            grantUriPermission(null, selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            newWallpaper.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            wallpaperPath=picturePath;
        }

        if (requestCode == RESULT_LOAD_AUDIO && resultCode == RESULT_OK && null != data) {
            Uri selectedAudio = data.getData();
//            grantUriPermission(null, selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String[] filePathColumn = {MediaStore.Audio.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedAudio,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String audioPath = cursor.getString(columnIndex);
            cursor.close();

            File audioFile=new File(audioPath);
            trackView.setText(audioFile.getName());
            this.audioPath=audioPath;
        }
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
