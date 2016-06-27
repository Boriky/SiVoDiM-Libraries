package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.R;
import starklabs.libraries.View.EditVoiceActivity;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class VoiceListPresenterImpl implements VoiceListPresenter{

    VoiceListActivityInterface voiceListActivityInterface;
    StringArrayAdapter voicesAdapterName;
    private Engine engine;

    //For selection of voice in VoiceListActivity
    int voiceSelectedInt=-1;
    String voiceSelected=null;

    /** Constructor of VoiceListPresenterImpl
     *
     * @param engine from the HomeActivity
     */
    public VoiceListPresenterImpl(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void goToEditVoiceActivity(Context context, MivoqVoice mivoqVoice) {
        Intent editVoiceIntent = new Intent(context, EditVoiceActivity.class);
        VoicePresenter voicePresenter = new VoicePresenterImpl(mivoqVoice, engine);
        EditVoiceActivity.setPresenter(voicePresenter);
        context.startActivity(editVoiceIntent);
    }

    @Override
    public void setActivity(VoiceListActivityInterface voiceListActivityInterface) {
        this.voiceListActivityInterface=voiceListActivityInterface;
    }

    @Override
    public MivoqVoice createMivoqVoice(String s) {
        return engine.createVoice(s, "g", "mL");
    }

    @Override
    public void loadVoiceNames(Context context) {
        voicesAdapterName=new StringArrayAdapter(context, R.layout.voice);
        ArrayList<MivoqVoice> voices=engine.getVoices();
        for(int i=0; i<voices.size(); i++)
            voicesAdapterName.add(voices.get(i).getName());
    }

    @Override
    public StringArrayAdapter getVoicesAdapter(Context context) {
        loadVoiceNames(context);
        voicesAdapterName.setStringSelected(voiceSelectedInt);
        return voicesAdapterName;
    }

    public void setVoiceSelected(int index, String name){
        this.voiceSelectedInt = index;
        this.voiceSelected = name;
    }

    @Override
    public void deleteVoiceSelected(Context context) {
        engine.removeVoice(voiceSelectedInt);
        engine.save();
    }

}
