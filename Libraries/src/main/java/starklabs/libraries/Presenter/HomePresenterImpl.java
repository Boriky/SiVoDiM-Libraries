package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.View.NewVoiceActivity;
import starklabs.libraries.View.VoiceListActivity;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {

    private Engine engine;

    /** Constructor of HomePresenterImpl with instantiation of the
     *  engine
     * @param context
     */
    public HomePresenterImpl(Context context) {
        this.engine = new EngineImpl(context);
    }

    //--------------------GO TO----------------------------
    @Override
    public void goToNewVoiceActivity(Context context) {
        Intent newVoiceIntent = new Intent(context, NewVoiceActivity.class);
        VoicePresenter voicePresenter = new VoicePresenterImpl(engine);
        NewVoiceActivity.setPresenter(voicePresenter);
        context.startActivity(newVoiceIntent);
    }

    //--------------------GO TO----------------------------
    @Override
    public void goToVoiceListActivity(Context context) {
        Intent voiceListIntent = new Intent(context, VoiceListActivity.class);
        VoiceListPresenter voiceListPresenter = new VoiceListPresenterImpl(engine);
        VoiceListActivity.setPresenter(voiceListPresenter);
        context.startActivity(voiceListIntent);
    }

}
