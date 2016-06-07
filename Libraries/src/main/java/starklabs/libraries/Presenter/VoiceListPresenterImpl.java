package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

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
    ArrayAdapter<String> voicesAdapterName;
    private Engine engine;

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
        VoicePresenter voicePresenter = new VoicePresenterImpl(mivoqVoice);
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
        voicesAdapterName=new ArrayAdapter<String>(context, R.layout.voice);
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

}
