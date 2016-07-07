package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;
import java.util.regex.Pattern;

import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.R;

public class NewScreenplayActivity extends AppCompatActivity implements NewScreenplayInterface, View.OnClickListener {
    private Button buttonCreateProject;
    private EditText editTextProjectName;
    private Spinner spinnerImportCharacters;
    private static ScreenplayPresenter screenplayPresenter;

    public static void setScreenplayPresenter(ScreenplayPresenter screenplayPresenter){
        NewScreenplayActivity.screenplayPresenter=screenplayPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screenplay);
        if(screenplayPresenter==null)screenplayPresenter=new ScreenplayPresenterImpl(this);
        else
        screenplayPresenter.setActivity(this);

        getSupportActionBar().setTitle("Creazione progetto");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // GUI items initialization
        this.buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);
        this.editTextProjectName = (EditText) findViewById(R.id.editTextProjectName);
        this.spinnerImportCharacters = (Spinner) findViewById(R.id.spinnerImportCharacters);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.raw_spinner_import, screenplayPresenter.getStringArray());
        arrayAdapter.insert("<Non importare>", 0);
        this.spinnerImportCharacters.setAdapter(arrayAdapter);

        // onClick for Button
        this.buttonCreateProject.setOnClickListener(this);

    }

    private boolean correctName(String text){
        if(text.isEmpty())return false;
        if(Pattern.matches("(.*)(\\W+)(.*)",text.replace(" ","_")))return false;
        if(text.contains(":"))return false;
        if(text.contains("."))return false;
        if(text.contains(";"))return false;
        if(text.contains(","))return false;
        if(text.contains("?"))return false;
        if(text.contains("ù"))return false;
        if(text.contains("/"))return false;
        if(text.contains("("))return false;
        if(text.contains(")"))return false;
        if(text.contains("="))return false;
        if(text.contains("^"))return false;
        if(text.contains("è"))return false;
        if(text.contains("ì"))return false;
        if(text.contains("à"))return false;
        if(text.contains("ò"))return false;
        if(text.contains("!"))return false;
        if(text.contains("|"))return false;
        if(text.contains("\""))return false;
        if(text.contains("£"))return false;
        if(text.contains("$"))return false;
        if(text.contains("%"))return false;
        if(text.contains("&"))return false;
        if(text.contains("+"))return false;
        if(text.contains("*"))return false;
        return true;
    }

    public void onClick(View v) {
        String title = editTextProjectName.getText().toString();

        Vector<String> titles = screenplayPresenter.getStringArray();
        Boolean titleTaken = false;
        for(int i=0; i<titles.size(); i++) {
            String titleEx=titles.get(i).toLowerCase().replace(" ","_");
            if(titleEx.equals(title.toLowerCase().replace(" ","_"))) {
                titleTaken = true;
            }
        }

        if(!correctName(title)) {
            Toast.makeText(v.getContext(),"Il titolo dello sceneggiato non è valido",Toast.LENGTH_SHORT).show();
        }
        else if(titleTaken==true) {
            Toast.makeText(v.getContext(),"Titolo dello sceneggiato già esistente",Toast.LENGTH_SHORT).show();
        }
        else {
            screenplayPresenter.newScreenplay(title, this);
            String selected = (String) spinnerImportCharacters.getSelectedItem();
            // the first position of the spinner is a "not import any character" option
            if (spinnerImportCharacters.getSelectedItemPosition() != 0) {
                screenplayPresenter.importCharacter(selected + ".scrpl", this);
            }

            ListChapterActivity.setPresenter(screenplayPresenter);
            Intent listChapterIntent = new Intent(this, ListChapterActivity.class);
            startActivity(listChapterIntent);
        }
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
}
