package starklabs.libraries.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoiceListPresenter;
import starklabs.libraries.R;

public class VoiceListActivity extends AppCompatActivity implements VoiceListActivityInterface{
    private static VoiceListPresenter voiceListPresenter;
    private ListView voiceListView;
    private ListAdapter voiceListAdapter;
    private LinearLayout removeLayout;
    private FloatingActionButton doneButton;
    private FloatingActionButton deleteButton;

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
        removeLayout=(LinearLayout)findViewById(R.id.removeLayout);
        doneButton=(FloatingActionButton)findViewById(R.id.doneButton);
        deleteButton=(FloatingActionButton)findViewById(R.id.deleteButton);

        //Setting to default the list adapter (if any longclick item is selected
        if(voiceListView!= null) {
            voiceListView.setSelection(-1);
        }
        removeLayout.setVisibility(View.GONE);
        deleteButton.setVisibility(View.GONE);

        voiceListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=(String) parent.getItemAtPosition(position);
                Engine engine=new EngineImpl(view.getContext());
                MivoqVoice mV=engine.getVoiceByName(selected);
                voiceListPresenter.goToEditVoiceActivity(view.getContext(), mV);
                finish();
            }
        });

        voiceListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Toast.makeText(view.getContext(), "La prima voce non può essere eliminata", Toast.LENGTH_LONG).show();
                    return true;
                }
                else {
                    String selectedTitle = (String) parent.getItemAtPosition(position);
                    voiceListPresenter.setVoiceSelected(position, selectedTitle);
                    voiceListView.setAdapter(voiceListPresenter.getVoicesAdapter(view.getContext()));
                    voiceListView.setSelection(position);
                    removeLayout.setVisibility(View.VISIBLE);
                    deleteButton.setVisibility(View.VISIBLE);
                    return true;
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceListPresenter.setVoiceSelected(-1,null);
                voiceListView.setAdapter(voiceListPresenter.getVoicesAdapter(v.getContext()));
                voiceListView.setSelection(voiceListView.getCount()-1);
                removeLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceListPresenter.deleteVoiceSelected(v.getContext());
                voiceListPresenter.setVoiceSelected(-1,null);
                voiceListView.setAdapter(voiceListPresenter.getVoicesAdapter(v.getContext()));
                voiceListView.setSelection(voiceListView.getCount()-1);
                removeLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(VoiceListActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    public void onResume(){
        super.onResume();
        voiceListPresenter.setVoiceSelected(-1,null);
        voiceListView.setAdapter(voiceListPresenter.getVoicesAdapter(getApplicationContext()));
        voiceListView.setSelection(voiceListView.getCount()-1);
        removeLayout.setVisibility(View.GONE);
        deleteButton.setVisibility(View.GONE);    }
}
