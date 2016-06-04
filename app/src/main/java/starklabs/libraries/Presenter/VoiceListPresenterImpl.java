package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.R;
import starklabs.libraries.View.VoiceListActivity;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class VoiceListPresenterImpl implements VoiceListPresenter{

    VoiceListActivityInterface voiceListActivityInterface;
    VoiceListPresenter voiceListPresenter;
    ArrayAdapter<String> voicesAdapterName;

    private Engine engine;

    //--------------------GO TO----------------------------
    @Override
    public void goToVoiceListActivity(Context context) {
        Intent voiceListIntent = new Intent(context, VoiceListActivity.class);
        VoiceListActivity.setPresenter(this);
        context.startActivity(voiceListIntent);
    }

    public VoiceListPresenterImpl(VoiceListActivityInterface voiceListActivityInterface) {
        this.voiceListActivityInterface=voiceListActivityInterface;}

    @Override
    public void loadVoiceNames(Context context) {
        voicesAdapterName=new ArrayAdapter<String>(context, R.layout.voice);
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
        voicesAdapterName.add("Fede");
        voicesAdapterName.add("Enri");
    }

    @Override
    public ArrayAdapter<String> getVoicesAdapter(Context context) {
        if(voicesAdapterName==null){
            loadVoiceNames(context);
        }
        return voicesAdapterName;
    }

    @Override
    public void setActivity(VoiceListActivity voiceListActivity) {
        this.voiceListActivityInterface=voiceListActivityInterface;
    }

    @Override
    public void createVoiceList() {

    }

}
