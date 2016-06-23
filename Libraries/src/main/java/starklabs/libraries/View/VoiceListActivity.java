package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoiceListPresenter;
import starklabs.libraries.R;

public class VoiceListActivity extends AppCompatActivity implements VoiceListActivityInterface{
    private static VoiceListPresenter voiceListPresenter;
    private ListView voiceListView;
    private ListAdapter voiceListAdapter;

    //------------------------SET PRESENTER--------------------
    public static void setPresenter(VoiceListPresenter voiceListPresenter){
        VoiceListActivity.voiceListPresenter=voiceListPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_list);

        voiceListPresenter.setActivity(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Lista voci");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //list of voices
        voiceListView = (ListView) findViewById(R.id.voiceListView);
        voiceListAdapter = voiceListPresenter.getVoicesAdapter(this);
        voiceListView.setAdapter(voiceListAdapter);

        voiceListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                Engine engine=new EngineImpl(view.getContext());
                MivoqVoice mV=engine.getVoiceByName(selected);
                voiceListPresenter.goToEditVoiceActivity(view.getContext(), mV);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
