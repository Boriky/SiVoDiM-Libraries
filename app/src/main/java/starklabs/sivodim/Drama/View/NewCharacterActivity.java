package starklabs.sivodim.Drama.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSService;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.Presenter.ChapterPresenterImpl;
import starklabs.sivodim.Drama.Presenter.CharacterArrayAdapter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenterImpl;
import starklabs.sivodim.R;

public class NewCharacterActivity extends AppCompatActivity implements NewCharacterInterface {
    private static CharacterPresenter characterPresenter;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=2;
    private EditText newCharacterName;
    private Spinner newCharacterVoice;
    private ImageView newCharacterAvatar;
    private Button newCharacterApply;
    private Button newChatacterTestVoice;
    private String avatarPath;

    public static void setPresenter(CharacterPresenter characterPresenter){
        NewCharacterActivity.characterPresenter=characterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        if(characterPresenter==null)
            characterPresenter=new CharacterPresenterImpl(this);
        else
            characterPresenter.setActivity(this);

        getSupportActionBar().setTitle("Creazione personaggio");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newCharacterName=(EditText)findViewById(R.id.newCharacterName);
        newCharacterAvatar=(ImageView)findViewById(R.id.newCharacterAvatar);
        newCharacterVoice=(Spinner)findViewById(R.id.newCharacterVoice);
        newChatacterTestVoice=(Button)findViewById(R.id.newCharacterTestVoice);
        newCharacterApply=(Button)findViewById(R.id.newCharacterApply);

        newCharacterVoice.setAdapter(SpeechImpl.getVoices(this));

        newCharacterAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadPicture();
            }
        });

        newChatacterTestVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play some speech with voice
                newChatacterTestVoice.setEnabled(false);
                final File path=new File(getFilesDir(), "testVoce.wav");
                Engine engine=new EngineImpl(v.getContext());
                //engine.speak((String) newCharacterVoice.getSelectedItem(),MivoqVoice.getSampleText("it"));
                MivoqVoice voice=engine.getVoiceByName((String) newCharacterVoice.getSelectedItem());
                engine.synthesizeToFile(path.getAbsolutePath(),
                        (String) newCharacterVoice.getSelectedItem(),
                        "NONE", MivoqVoice.getSampleText(voice.getLanguage().substring(0,2)), new Engine.Listener() {
                            @Override
                            public void onCompleteSynthesis() {
                                SpeechSound speechSound=new SpeechSound(path.getAbsolutePath());
                                speechSound.play(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        newChatacterTestVoice.setEnabled(true);
                                    }
                                });
                            }
                        });
            }
        });

        newCharacterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert check for not same names
                String name = newCharacterName.getText().toString();

                Boolean nameTaken = false;
                CharacterArrayAdapter names = characterPresenter.getCharacterArrayAdapter(v.getContext());
                for(int i=0; i<names.getCount(); i++) {
                    if(names.getItem(i).getName().equals(name)) {
                        nameTaken=true;
                    }
                }

                if(name.isEmpty()) {
                    Toast.makeText(v.getContext(), "Il nome del personaggio non può essere vuoto", Toast.LENGTH_SHORT).show();
                }
                else if(nameTaken==true) {
                    Toast.makeText(v.getContext(), "Nome del personaggio già esistente", Toast.LENGTH_SHORT).show();
                } else {
                    String voice = (String) newCharacterVoice.getSelectedItem();
                    Avatar avatar = null;
                    if (avatarPath != null) {
                        File avatarChoice = new File(avatarPath);
                        File dir = new File(getFilesDir(),
                                characterPresenter.getProjectName());
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File destination = new File(dir, name + ".png");
                        try {
                            copyFile(avatarChoice, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        avatar = new Avatar(destination.getAbsolutePath());
                        System.out.println("SAVE in: " + destination.getAbsolutePath());
                    }
                    characterPresenter.newCharacter(name, voice, avatar);
                    characterPresenter.goToListCharacterActivity(v.getContext());
                }
            }
        });
    }

    private void loadPicture(){
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
        getImage();

    }

    private void getImage(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);


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

            newCharacterAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            avatarPath=picturePath;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                characterPresenter.goToListCharacterActivity(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
