package starklabs.libraries.View;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Presenter.HomePresenter;
import starklabs.libraries.Presenter.HomePresenterImpl;
import starklabs.libraries.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeActivityInterface, TextToSpeech.OnInitListener {

    private static HomePresenter homePresenter;
    private PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        if(homePresenter==null) homePresenter = new HomePresenterImpl(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button voiceList= (Button) findViewById(R.id.buttonVoiceList);
        voiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.goToVoiceListActivity(v.getContext());
            }
        });

        final Button newVoice = (Button) findViewById(R.id.buttonNewVoice);
        newVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePresenter.goToNewVoiceActivity(view.getContext());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        Button test=(Button) findViewById(R.id.buttonT);
        if(test!=null){
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent homeIntent = new Intent(HomeActivity.this, TestActivity.class);
                    startActivity(homeIntent);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        // This method should shut down the application
        finish();
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toast toast;
        if (id == R.id.nav_guide) {

            Engine myEngine=new EngineImpl(getApplicationContext());
            myEngine.speak("Patrizia", "Ciao, sono la nuova voce di Mivoq");

            /*
            //try of synthesizeToFile method
            MivoqVoice Patrizia=myEngine.createVoice("Patrizia", "female", "it");
            File path=getFilesDir();
            File myFile=new File(path,"patrizia.wav");
            try {
                engine.synthesizeToFile(myFile.toString(), Patrizia ,"Ciao, sono Patrizia, la nuova voce femminile di Mivoq" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            */

            toast = Toast.makeText(getApplicationContext(), "Apre il manuale utente", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_info) {
            initiatePopupWindow();
            toast = Toast.makeText(getApplicationContext(), "Apre info su app e autori", Toast.LENGTH_SHORT);
            toast.show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) HomeActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popup_layout,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, ActionBar.LayoutParams.WRAP_CONTENT, 600, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            cancelButton.setOnClickListener(cancel_button_click_listener);
            // Closes the popup window when touch outside.
            pw.setOutsideTouchable(true);
            pw.setFocusable(true);
            // Removes default background.
            pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };

    @Override
    public void onInit(int status) {
        /*
        int status1= mtts.setEngineByPackageName("starklabs.libraries.Model.Mivoq.MivoqTTService");
        System.out.println(status1);
        mtts.speak("Sono android", TextToSpeech.QUEUE_FLUSH, null);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mtts.speak("Sono androidiano", TextToSpeech.QUEUE_FLUSH, null);

        try {
            System.out.println("-------------------------");
            System.out.println(getApplicationContext().getPackageManager().getPackageInfo("starklabs.libraries.Model.Mivoq.MivoqTTService",
                    PackageManager.COMPONENT_ENABLED_STATE_DEFAULT).toString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            System.out.println("------------NOT FOUND-------------");
        }
        */
    }

}
