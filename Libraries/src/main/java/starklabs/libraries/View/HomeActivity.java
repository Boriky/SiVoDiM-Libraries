package starklabs.libraries.View;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.HomePresenter;
import starklabs.libraries.Presenter.HomePresenterImpl;
import starklabs.libraries.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeActivityInterface, TextToSpeech.OnInitListener {

    private static HomePresenter homePresenter;

    private ArrayAdapter<String> voiceListAdapter;
    private ListView listView;

    private TextToSpeech mtts;

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

        Button button = (Button) findViewById(R.id.button1);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Engine myEngine=new EngineImpl(getApplicationContext());
                MivoqVoice Fede=myEngine.createVoice("Fede", "male", "it");
                Effect e1=new EffectImpl("Rate");
                e1.setValue("0.8");
                Fede.setEffect(e1);
                myEngine.speak("Fede", "C'è un gatto, l'uomo, quell'aereo.");

     /*
                MivoqTTSSingleton engine = MivoqTTSSingleton.getInstance();

                engine.setContext(getApplicationContext());

                MivoqVoice Fede=engine.CreateVoice("Fede", "male", "it");
                engine.Speak(Fede, "Cosa faremo stasera, Prof? " +
                        //"Quello che facciamo tutte le sere, Mignolo." +
                        "Tenteremo di conquistare il mondo!");

                MivoqVoice Fedede=engine.CreateVoice("Fedede", "female", "de");

         //       MivoqVoice Fedefr=engine.CreateVoice("Fedefr", "male", "fr");

                //String a= "data/data/starklabs.libraries/provafile.wav";
                File path=getFilesDir();
                File myFile=new File(path,"nomeFile.wav");
                try {
                    engine.SynthesizeToFile(myFile.toString(),Fede, "Cosa faremo stasera, Prof? " +
                            "Quello che facciamo tutte le sere, Mignolo." +
                            "Tenteremo di conquistare il mondo!");
                    System.out.println("File scritto");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                String b= "data/data/starklabs.libraries/provafilede.wav";
                try {
                    engine.SynthesizeToFile(b,Fedede, "Cosa faremo stasera, Prof? " +
                            "Quello che facciamo tutte le sere, Mignolo." +
                            "Tenteremo di conquistare il mondo!");
                    System.out.println("File scritto");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                engine.Speak(Fedede, "Cosa faremo stasera, Prof? " +
                        "Quello che facciamo tutte le sere, Mignolo." +
                        "Tenteremo di conquistare il mondo!");


                String c= "data/data/starklabs.libraries/provafilefr.wav";
                try {
                    engine.SynthesizeToFile(c,Fedefr, "Cosa faremo stasera, Prof? " +
                            "Quello che facciamo tutte le sere, Mignolo." +
                            "Tenteremo di conquistare il mondo!");
                    System.out.println("File scritto");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
*/
            }
        });

        mtts = new TextToSpeech(this, this, "starklabs.libraries.Model.Mivoq.MivoqTTSService");
    }

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
            toast = Toast.makeText(getApplicationContext(), "Apre il manuale utente", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_info) {
            toast = Toast.makeText(getApplicationContext(), "Apre info su app e autori", Toast.LENGTH_SHORT);
            toast.show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
