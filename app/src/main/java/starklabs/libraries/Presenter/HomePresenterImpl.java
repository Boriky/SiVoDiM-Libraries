package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.View.HomeActivityInterface;
import starklabs.libraries.View.NewVoiceActivity;
import starklabs.libraries.View.VoiceListActivity;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {

    private Engine engine;

    private VoicePresenter voicePresenter;

    private VoiceListPresenter voiceListPresenter;

    private HomeActivityInterface homeActivityInterface;
    VoiceListActivityInterface voiceListActivityInterface;

    public HomePresenterImpl(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setActivity(VoiceListActivityInterface voiceListActivityInterface) {
        this.voiceListActivityInterface=voiceListActivityInterface;
    }

    //--------------------GO TO----------------------------
    @Override
    public void goToVoiceListActivity(Context context) {
        Intent voiceListIntent = new Intent(context, VoiceListActivity.class);
        VoiceListActivity.setPresenter(voiceListPresenter);
        context.startActivity(voiceListIntent);
    }

    //--------------------GO TO----------------------------
    @Override
    public void goToNewVoiceActivity(Context context) {
        Intent newVoiceIntent = new Intent(context, NewVoiceActivity.class);
        NewVoiceActivity.setPresenter(voicePresenter);
        context.startActivity(newVoiceIntent);
    }
}
