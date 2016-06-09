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
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.R;

public class EditCharacterActivity extends AppCompatActivity implements EditCharacterInterface{
    private static CharacterPresenter characterPresenter;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=2;
    private ImageView editAvatar;
    private Spinner editVoice;
    private EditText editName;
    private Button apply;
    private Character character;

    public static void setPresenter(CharacterPresenter characterPresenter){
        EditCharacterActivity.characterPresenter=characterPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);

        characterPresenter.setActivity(this);

        getSupportActionBar().setTitle("Gestione personaggio");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editAvatar=(ImageView)findViewById(R.id.editAvatar);
        editVoice=(Spinner)findViewById(R.id.editVoice);
        editName=(EditText)findViewById(R.id.editName);
        apply=(Button)findViewById(R.id.applyChanges);
        character=characterPresenter.getCharacter();

        Avatar avatar=character.getAvatar();
        if(avatar!=null && avatar.getImage()!=null)
           editAvatar.setImageBitmap(avatar.getImage());

        editName.setText(character.getName());
        //add voices....

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString();
                character.setName(name);
                //set voice and avatar..
                Intent intent=new Intent(v.getContext(),ListCharacterActivity.class);
                startActivity(intent);
            }
        });

        editAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPicture();
            }
        });
    }


    public void loadPicture(){
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

            editAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            File avatar=new File(picturePath);
            File destination=new File(getFilesDir(),character.getName()+".png");
            try {
                copyFile(avatar,destination);
            } catch (IOException e) {
                e.printStackTrace();
            }

            characterPresenter.getCharacter()
                    .setAvatar(new Avatar(destination.getAbsolutePath()));


        }
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,ListCharacterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
