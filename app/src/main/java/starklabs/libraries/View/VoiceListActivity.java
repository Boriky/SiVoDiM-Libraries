package starklabs.libraries.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import starklabs.libraries.Presenter.VoiceListPresenter;
import starklabs.libraries.Presenter.VoiceListPresenterImpl;
import starklabs.libraries.R;

public class VoiceListActivity extends AppCompatActivity implements VoiceListActivityInterface{
    private static VoiceListPresenter voiceListPresenter;
    private ListView voiceListView;
    private ListAdapter voiceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_list);

        if(voiceListPresenter==null) voiceListPresenter=new VoiceListPresenterImpl(this);

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
                Intent intent = new Intent(view.getContext(), EditVoiceActivity.class);
                intent.putExtra("VoiceSelected", selected);
                startActivity(intent);
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
