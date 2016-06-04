package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.View.VoiceListActivity;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public interface VoiceListPresenter {

    //ArrayAdapter<String> getVoices(Context context);

    void createVoiceList();

    void loadVoiceNames(Context context);

    ArrayAdapter<String> getVoicesAdapter(Context context);

    void setActivity(VoiceListActivity voiceListActivity);
    void goToVoiceListActivity(Context context);
}
