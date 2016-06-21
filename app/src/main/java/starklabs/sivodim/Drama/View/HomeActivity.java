package starklabs.sivodim.Drama.View;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;
import starklabs.sivodim.Drama.Model.Screenplay.Mp3Converter;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.Presenter.HomePresenter;
import starklabs.sivodim.Drama.Presenter.HomePresenterImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.Drama.Presenter.StringArrayAdapter;
import starklabs.sivodim.R;

import static android.os.Environment.getExternalStorageDirectory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeInterface, AdapterView.OnItemClickListener {
    private static HomePresenter homePresenter;
    private ListView screenplayListView;
    private StringArrayAdapter screenplayListAdapter;
    private LinearLayout removeLayout;
    private FloatingActionButton doneButton;
    private FloatingActionButton deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(homePresenter==null)homePresenter=new HomePresenterImpl(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,NewScreenplayActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //show list of screenplays found in the application root directory
        screenplayListView= (ListView) findViewById(R.id.screenplayListView);
        screenplayListAdapter=homePresenter.getTitlesAdapter(this);
        screenplayListView.setAdapter(screenplayListAdapter);
        removeLayout=(LinearLayout)findViewById(R.id.removeLayout);
        doneButton=(FloatingActionButton)findViewById(R.id.doneButton);
        deleteButton=(FloatingActionButton)findViewById(R.id.deleteButton);

        if(screenplayListAdapter.getCount()==0)
            Toast.makeText(this,"Clicca sul + per creare un nuovo progetto",Toast.LENGTH_LONG).show();

        screenplayListView.setOnItemClickListener(this);
        screenplayListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTitle=(String)parent.getItemAtPosition(position);
                homePresenter.setScreenplaySelected(position,selectedTitle);
                screenplayListView.setAdapter(homePresenter.getTitlesAdapter(view.getContext()));
                screenplayListView.setSelection(position);
                removeLayout.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                deleteButton.setVisibility(View.VISIBLE);
                return true;
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.setScreenplaySelected(-1,null);
                screenplayListView.setAdapter(homePresenter.getTitlesAdapter(v.getContext()));
                screenplayListView.setSelection(screenplayListView.getCount()-1);
                removeLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.deleteScreenplaySelected(v.getContext());
                homePresenter.setScreenplaySelected(-1,null);
                screenplayListView.setAdapter(homePresenter.getTitlesAdapter(v.getContext()));
                screenplayListView.setSelection(screenplayListView.getCount()-1);
                removeLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });

    }

    /*public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        file.mkdirs();
        return file;
    }

    //Checks if external storage is available to at least read
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toast toast;
        if (id == R.id.nav_config) {
            toast = Toast.makeText(getApplicationContext(), "Apre l'applicazione di configurazione", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_guide) {
            Intent intent=new Intent(this,UserManualActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            toast = Toast.makeText(getApplicationContext(), "Apre info su app e autori", Toast.LENGTH_SHORT);
            toast.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selected=(String) parent.getItemAtPosition(position);
        homePresenter.goToListChapter(this,selected+".scrpl");
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
