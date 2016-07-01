package starklabs.libraries.View;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.Toast;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Presenter.HomePresenter;
import starklabs.libraries.Presenter.HomePresenterImpl;
import starklabs.libraries.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeActivityInterface, TextToSpeech.OnInitListener {

    private static HomePresenter homePresenter;

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
/*
                Engine myEngine=new EngineImpl(getApplicationContext());
                MivoqVoice Fede=myEngine.createVoice("Fede", "male", "it");
                Effect e1=new EffectImpl("Rate");
                e1.setValue("0.8");
                Fede.setEffect(e1);
                myEngine.speak("Fede", "C'è un gatto, l'uomo, quell'aereo.");

                MivoqTTSSingleton engine = MivoqTTSSingleton.getInstance();
                engine.setContext(getApplicationContext());

                MivoqVoice Fede=engine.CreateVoice("Fede", "male", "it");
                engine.Speak(Fede, "Prova1");

                File path=getFilesDir();
                File myFile=new File(path,"nomeFile.wav");
                try {
                    engine.SynthesizeToFile(myFile.toString(),Fede, "Prova2");
                    System.out.println("File scritto");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String b= "data/data/starklabs.libraries/provafilede.wav";
                try {
                    engine.SynthesizeToFile(b,Fedede, "Provade");
                    System.out.println("File scritto");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        mtts = new TextToSpeech(this, this, "starklabs.libraries.Model.Mivoq.MivoqTTSService");
        */

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
            //MivoqVoice Patrizia=myEngine.createVoice("Patrizia", "female", "it");
           // MivoqVoice Roberto=myEngine.createVoice("Roberto", "male", "it");

            myEngine.speak("Patrizia", "Ciao, sono la nuova voce di Mivoq");

            //File path=getFilesDir();
            //File myFile=new File(path,"patrizia.wav");
            /*
            try {
                engine.synthesizeToFile(myFile.toString(), Patrizia ,"Ciao, sono Patrizia, la nuova voce femminile di Mivoq" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            File myFile1=new File(path,"roberto.wav");
            try {
                engine.synthesizeToFile(myFile1.toString(), Roberto ,"Ciao, sono Roberto, la nuova voce maschile di Mivoq" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

*/
            //myEngine.synthesizeToFile(myFile.toString(),"Patrizia", null, "Ciao, sono Patrizia, la nuova voce femminile di Mivoq");


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
